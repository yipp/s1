package org.yinet.s1.logic.scenes.manager.Scenes_01;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resources;
import org.yinet.s1.logic.scenes.Card.CardInit;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;
import org.yinet.s1.logic.scenes.dao.SettleAccountsDao;
import org.yinet.s1.logic.scenes.model.ScenesManager.ScenesAbstract;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;
import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ppdashi on 2017/7/16.
 */
@Service
public class Scenes_01 extends ScenesAbstract{
    public static ApplicationContext ctx = null;
    /**场景内所有的玩家*/
    public static List<Channel> user = new ArrayList<>();
    @Autowired
    private CardComparisonScenes01 cardComparisonScenes01;
    @Autowired
    Scenes_01Banker scenes_01Banker;
    @Override
    protected void clear() {
        int i = scenes_01Banker.baner.get(0).getBankerNumber();
        i++;
        scenes_01Banker.baner.get(0).setBankerNumber(i);

        CardData.scene01CardSet.clear();
        CardData.scene01Result.clear();
        CardData.scene04Card.clear();

        CardData.cards1.clear();
        CardData.cards2.clear();
        CardData.cards3.clear();
        CardData.cards4.clear();
        timer = time;
        this.end = true;
    }

    @Override
    protected void sendResult(Channel channel, SettleAccountsDao dao) {
        byte[] buf = ProtostuffUtils.serializer(dao);
        Response response = new Response();
        response.setId(11);
        response.setDATA(buf);
        channel.writeAndFlush(response);
    }

    @Override
    protected void doExecutor() {
        //1，发牌 场景1有5堆牌所以发15张牌
        this.randomCommon(1,53,15);
        //2，比牌
        cardComparisonScenes01.playerCard();
        //得到结果 其中发给玩家的5副牌的数据在CardData的scene01CardSet里
        //比完之后玩家是得到的是散还是对子还是顺子的数据在CardComparisonScenes01的sceneResult里
        //发往客户端的牌面CardData.scene04Card
        //3，用户结算
        if(cardComparisonScenes01.sceneResult.get(5)>0)
            settle(CardData.cards1,cardComparisonScenes01.sceneResult.get(1));
        if(cardComparisonScenes01.sceneResult.get(6)>0)
            settle(CardData.cards2,cardComparisonScenes01.sceneResult.get(2));
        if(cardComparisonScenes01.sceneResult.get(7)>0)
            settle(CardData.cards3,cardComparisonScenes01.sceneResult.get(3));
        if(cardComparisonScenes01.sceneResult.get(8)>0)
            settle(CardData.cards4,cardComparisonScenes01.sceneResult.get(4));
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CardInit cardAdd = ctx.getBean(CardInit.class);
        Scenes_01 scenes_01 = ctx.getBean(Scenes_01.class);
        cardAdd.addCar();
        scenes_01.doExecutor();
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
    private void settleAccounts(int userId,long money,Channel channel,long bankerMoney){
        Resources resources = UserCache.playerId.get(userId).getResources();
        resources.addGold(money);

        sendResult(channel,new SettleAccountsDao(bankerMoney,resources.getGold()));
    }

    /**
     *
     * @param map:哪堆钱赢了
     * @param multiple：多少倍
     */
    private void settle(Map<Channel,Long> map,int multiple){
        for (Map.Entry<Channel,Long> e:map.entrySet()) {
            int userId = UserCache.idMap.get(e.getKey());
            long money = e.getValue()*multiple;
            long bankerMoney = 88997766L;
            if(!scenes_01Banker.baner.isEmpty()) {
                int bankerId = scenes_01Banker.baner.get(0).getId();
                Resources resources = UserCache.playerId.get(bankerId).getResources();
                bankerMoney = resources.getGold();
            }
            settleAccounts(userId,money,e.getKey(),bankerMoney);
        }
    }
}
