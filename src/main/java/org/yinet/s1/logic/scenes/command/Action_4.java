package org.yinet.s1.logic.scenes.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.logic.scenes.dao.BankerDao;
import org.yinet.s1.logic.scenes.dao.BottomPourDao;
import org.yinet.s1.logic.scenes.data.Banker;
import org.yinet.s1.logic.scenes.data.BankerRsponse;
import org.yinet.s1.logic.scenes.manager.Scenes_01.CardData;
import org.yinet.s1.logic.scenes.manager.Scenes_01.Scenes_01Banker;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景1的请求
 *
 */
@Service("action_4")
public class Action_4 extends ScenesLogicTask{
    @Autowired
    private Scenes_01Banker scenes_01Banker;
    @Override
    public void executor() {
        //1，将id添加到对应的牌堆中去
        byte[] buf = (byte[]) this.msg;
        BottomPourDao bottomPourDao = ProtostuffUtils.deserializer(buf,BottomPourDao.class);
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
        //回发信息
        //1，根据id取到庄家的数据
        Player bankerUser = null;
        if(!scenes_01Banker.baner.isEmpty())
            bankerUser = UserCache.playerId.get(scenes_01Banker.baner.get(0).getId());

        BankerRsponse bankerRsponse = new BankerRsponse(bankerUser.getBasicInfo(),bankerUser.getResources());
        List<Player> playerList = new ArrayList<>();
        List<BankerRsponse> bankerRsponses = new ArrayList<>();
        for (int i = 0;i<scenes_01Banker.bankerList.size();i++){
            playerList.add(UserCache.playerId.get(scenes_01Banker.bankerList.get(i)));
        }
        for (Player p:playerList) {
            bankerRsponses.add(new BankerRsponse(p.getBasicInfo(),p.getResources()));
        }
        //2，根据庄家列表的id取到列表对应的数据
        //3，赋值给BankerDao
        BankerDao bankerDao = new BankerDao(bankerRsponse,bankerRsponses);
        //4，调用this.response方法回发数据
        byte[] bytes = ProtostuffUtils.serializer(bankerDao);
        this.msg = bytes;
        this.response();
    }
}
