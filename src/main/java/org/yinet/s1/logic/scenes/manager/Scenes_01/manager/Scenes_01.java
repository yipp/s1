package org.yinet.s1.logic.scenes.manager.Scenes_01.manager;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.logic.banker.manager.BankerUpScenes_01;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;
import org.yinet.s1.logic.scenes.dto.CardResultDto;
import org.yinet.s1.logic.scenes.dto.SettleAccountsDao;
import org.yinet.s1.logic.scenes.manager.Scenes_01.data.CardData;
import org.yinet.s1.logic.scenes.model.ScenesManager.ScenesAbstract;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ppdashi on 2017/7/16.
 */
@Service
public class Scenes_01 extends ScenesAbstract{
    /**场景内所有的玩家*/
    public static List<Channel> user = new ArrayList<>();
    @Autowired
    private CardComparisonScenes01 cardComparisonScenes01;
    @Autowired
    Scenes_01Banker scenes_01Banker;
    @Override
    public void clear() {
        if(!scenes_01Banker.baner.isEmpty()) {
            int i = scenes_01Banker.baner.get(0).getBankerNumber();
            if(i >= 20){
                scenes_01Banker.bankerDown(false);
            }else {
                i++;
                scenes_01Banker.baner.get(0).setBankerNumber(i);
            }
        }

        CardData.scene01CardSet.clear();
        CardData.scene04Card.clear();
        cardComparisonScenes01.sceneResult.clear();
        CardData.resetMoney();
        scenes_01Banker.bankerMoney = 0;
        CardData.cards1.clear();
        CardData.cards2.clear();
        CardData.cards3.clear();
        CardData.cards4.clear();
        timer = time;
        this.end = true;
    }
    @Override
    public void sendResult() {
        //庄家结算
        if(!scenes_01Banker.baner.isEmpty()){
            LoginDto dto = UserCache.playerId.get(scenes_01Banker.baner.get(0).getId());
            long money = CardData.allMoney - scenes_01Banker.bankerMoney;
            dto.getResource().addGold(money);
        }
        long money = 0;
        if(!scenes_01Banker.baner.isEmpty()){
            money = UserCache.playerId.get(scenes_01Banker.baner.get(0).getId()).getResource().getGold();
        }
        for (Channel channel:Scenes_01.user){
            Response response = new Response();
            SettleAccountsDao dto = new SettleAccountsDao(money,UserCache.playerMap.
                    get(channel).getResource().getGold() );
            byte[] buf = ProtostuffUtils.serializer(dto);
            response.setId(500);
            response.setDATA(buf);
            channel.writeAndFlush(response);
        }
    }

    /**
     * 牌面和结果
     */
    private void sendCardResult(){

       Response response = new Response();
        CardResultDto resultDto = new CardResultDto(CardData.scene04Card,cardComparisonScenes01.sceneResult);
        byte[] buf = ProtostuffUtils.serializer(resultDto);
        response.setDATA(buf);
        for (Channel channel:Scenes_01.user) {
            response.setId(505);
            channel.writeAndFlush(response);
        }
    }
    @Override
    public void doExecutor() {
        //1，发牌 场景1有5堆牌所以发15张牌
        this.randomCommon(1,53,15);
        //2，比牌
        cardComparisonScenes01.playerCard();
        //得到结果 其中发给玩家的5副牌的数据在CardData的scene01CardSet里
        //比完之后玩家是得到的是散还是对子还是顺子的数据在CardComparisonScenes01的sceneResult里
        //发往客户端的牌面CardData.scene04Card
        sendCardResult();
        //3，用户结算
        if(cardComparisonScenes01.sceneResult.get(5)>0)
            settle(CardData.cards1, cardComparisonScenes01.sceneResult.get(1));
        if(cardComparisonScenes01.sceneResult.get(6)>0)
            settle(CardData.cards2, cardComparisonScenes01.sceneResult.get(2));
        if(cardComparisonScenes01.sceneResult.get(7)>0)
            settle(CardData.cards3, cardComparisonScenes01.sceneResult.get(3));
        if(cardComparisonScenes01.sceneResult.get(8)>0)
            settle(CardData.cards4, cardComparisonScenes01.sceneResult.get(4));
        sendResult();
    }
    @Override
    protected void insert(int[] result) {
        for (int i : result) {
            CardData.scene04Card.add(i);
            CardData.scene01CardSet.add(ComparisonObj.cardMap.get(i));//添加无重复的牌
        }
    }

    /**
     * 结账
     */
    private void settleAccounts(int userId,long money){
        Resource resources = UserCache.playerId.get(userId).getResource();
        resources.addGold(money);
    }

    /**
     *
     * @param map:哪堆钱赢了
     * @param multiple：多少倍
     */
    private void settle(Map<Channel,Long> map,int multiple){
        for (Map.Entry<Channel,Long> e:map.entrySet()) {
            int userId = UserCache.idMap.get(e.getKey());
            long money = e.getValue()*multiple+e.getValue();
            scenes_01Banker.bankerMoney += money;
            System.out.println(money+"这个加了这么多钱啊");
            settleAccounts(userId,money);
        }

    }
}
