package org.yinet.s1.logic.scenes.command;

import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resources;
import org.yinet.s1.logic.scenes.dao.BottomPourDao;
import org.yinet.s1.logic.scenes.manager.Scenes_01.CardData;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/18******
 * 场景1下注
 */
@Service("action_5")
public class Action_5 extends ScenesLogicTask {
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        BottomPourDao bottomPourDao = ProtostuffUtils.deserializer(buf,BottomPourDao.class);
        Resources resources = UserCache.playerMap.get(this.channel).getResources();
        resources.reduceGold(bottomPourDao.getMoney());
        switch (bottomPourDao.getPosition()){
            case 1:
                CardData.cards1.put(this.channel,bottomPourDao.getMoney());
                break;
            case 2:
                CardData.cards2.put(this.channel,bottomPourDao.getMoney());
                break;
            case 3:
                CardData.cards3.put(this.channel,bottomPourDao.getMoney());
                break;
            case 4:
                CardData.cards4.put(this.channel,bottomPourDao.getMoney());
                break;
        }
    }
}
