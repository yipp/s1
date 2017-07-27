package org.yinet.s1.logic.scenes.command;

import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.logic.scenes.dto.BottomPourDao;
import org.yinet.s1.logic.scenes.dto.ResponseBottomPourDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.data.CardData;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/18******
 */
@Repository("action_5")
public class Action_5 extends ScenesLogicTask {
    @Override
    public void executor() {
        //1，将id添加到对应的牌堆中去
        byte[] buf = (byte[]) this.msg;
        BottomPourDao bottomPourDao = ProtostuffUtils.deserializer(buf,BottomPourDao.class);
        Resource resources = UserCache.playerMap.get(this.channel).getResource();
        resources.reduceGold(bottomPourDao.getMoney());
        switch (bottomPourDao.getPosition()){
            case 1:
                CardData.cards1.put(this.channel,bottomPourDao.getMoney());
                CardData.addMoney(bottomPourDao.getMoney(),1);
                break;
            case 2:
                CardData.cards2.put(this.channel,bottomPourDao.getMoney());
                CardData.addMoney(bottomPourDao.getMoney(),2);
                break;
            case 3:
                CardData.cards3.put(this.channel,bottomPourDao.getMoney());
                CardData.addMoney(bottomPourDao.getMoney(),3);
                break;
            case 4:
                CardData.cards4.put(this.channel,bottomPourDao.getMoney());
                CardData.addMoney(bottomPourDao.getMoney(),4);
                break;
        }
        //返回玩家金币
        List<Long> cardPile = new ArrayList<>();
        cardPile.add(CardData.a);
        cardPile.add(CardData.b);
        cardPile.add(CardData.c);
        cardPile.add(CardData.d);
        ResponseBottomPourDto dto = new ResponseBottomPourDto(resources.getGold(),cardPile);
        this.msg = dto;
        this.response();
    }
}
