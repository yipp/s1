package org.yinet.s1.logic.scenes.manager.scenes02.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;
import org.yinet.s1.logic.scenes.manager.Scenes_01.data.CardData;
import org.yinet.s1.logic.scenes.manager.scenes02.data.CardData02;
import org.yinet.s1.logic.scenes.model.ScenesManager.ScenesAbstract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：泡泡大湿
 * 时间：Created by CL-PC202 on 2017/7/27.
 * 描述：
 */
@Service
public class Scenes_02 extends ScenesAbstract {
    /**场景内所有的玩家*/
    public static List<Channel> user = new ArrayList<>();
    @Autowired
    private CardComparisonScenes02 cardComparisonScenes02;
    @Autowired
    private Scenes_02Banker scenes_02Banker;
    @Override
    protected void insert(int[] result) {
        for (int i : result) {
            CardData02.scene02Card.add(i);
            CardData02.scene02CardSet.add(ComparisonObj.cardMap.get(i));//添加无重复的牌
        }
    }

    @Override
    public void clear() {
        if(!scenes_02Banker.baner.isEmpty()) {
            int i = scenes_02Banker.baner.get(0).getBankerNumber();
            if(i >= 10){
                scenes_02Banker.bankerDown(false);
            }else {
                i++;
                scenes_02Banker.baner.get(0).setBankerNumber(i);
            }
        }

        CardData02.scene02CardSet.clear();
        CardData02.scene02Card.clear();
        cardComparisonScenes02.sceneResult.clear();
        CardData02.resetMoney();
        scenes_02Banker.bankerMoney = 0;
        CardData02.cards1.clear();
        CardData02.cards2.clear();
        timer = time;
        this.end = true;
    }

    @Override
    public void sendResult() {

    }

    @Override
    public void doExecutor() {
        //1，发牌 场景1有5堆牌所以发15张牌
        this.randomCommon(1,53,6);
        //2，比牌
        cardComparisonScenes02.playerCard();
        //得到结果 其中发给玩家的5副牌的数据在CardData的scene01CardSet里
        //比完之后玩家是得到的是散还是对子还是顺子的数据在CardComparisonScenes01的sceneResult里
        //发往客户端的牌面CardData.scene04Card
        //3，用户结算
        if(cardComparisonScenes02.sceneResult.get(2)>0)
            settle(CardData.cards1, cardComparisonScenes02.sceneResult.get(0));
        if(cardComparisonScenes02.sceneResult.get(3)>0)
            settle(CardData.cards2, cardComparisonScenes02.sceneResult.get(1));
        sendResult();
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
            scenes_02Banker.bankerMoney += money;
            System.out.println(money+"这个加了这么多钱啊");
            //settleAccounts(userId,money);
        }
        //庄家结算
        if(!scenes_02Banker.baner.isEmpty()){
            LoginDto dto = UserCache.playerId.get(scenes_02Banker.baner.get(0).getId());
            long money = CardData.allMoney - scenes_02Banker.bankerMoney;
            dto.getResource().addGold(money);
        }
    }
}
