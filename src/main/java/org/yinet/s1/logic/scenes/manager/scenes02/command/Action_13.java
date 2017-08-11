package org.yinet.s1.logic.scenes.manager.scenes02.command;

import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.logic.scenes.dto.BottomPourDao;
import org.yinet.s1.logic.scenes.dto.ResponseBottomPourDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.data.CardData;
import org.yinet.s1.logic.scenes.manager.scenes02.data.CardData02;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/30******
 * 描述：场景3下注
 */
@Repository("action_13")
public class Action_13 extends ScenesLogicTask {
    @Override
    public void executor() {
        //1，将id添加到对应的牌堆中去
        byte[] buf = (byte[]) this.msg;
        BottomPourDao bottomPourDao = ProtostuffUtils.deserializer(buf,BottomPourDao.class);
        Resource resources = UserCache.playerMap.get(this.channel).getResource();
        resources.reduceGold(bottomPourDao.getMoney());
        System.out.println(bottomPourDao.getPosition()+":"+bottomPourDao.getMoney());
        switch (bottomPourDao.getPosition()){
            case 1:
                CardData02.cards1.put(this.channel,bottomPourDao.getMoney());
                CardData02.addMoney(bottomPourDao.getMoney(),1);
                break;
            case 2:
                CardData02.cards2.put(this.channel,bottomPourDao.getMoney());
                CardData02.addMoney(bottomPourDao.getMoney(),2);
                break;
            case 3:
                CardData02.cards3.put(this.channel,bottomPourDao.getMoney());
                CardData02.addMoney(bottomPourDao.getMoney(),3);
                break;
        }
        //返回玩家金币
        List<Long> cardPile = new ArrayList<>();
        cardPile.add(CardData02.a);
        cardPile.add(CardData02.b);
        cardPile.add(CardData02.c);
        ResponseBottomPourDto dto = new ResponseBottomPourDto(resources.getGold(),cardPile);
        this.msg = dto;
        this.response();
    }
}
