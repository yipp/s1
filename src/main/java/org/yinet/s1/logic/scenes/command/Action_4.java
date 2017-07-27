package org.yinet.s1.logic.scenes.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.logic.banker.dto.BankerDto;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01Banker;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;

/**
 * 场景1的响应
 *
 */
@Repository("action_4")
public class Action_4 extends ScenesLogicTask{
    @Autowired
    private Scenes_01Banker scenes_01Banker;
    @Override
    public void executor() {

        //回发信息
        //1，根据id取到庄家的数据
        LoginDto bankerUser = null;
        if(!scenes_01Banker.baner.isEmpty())
          bankerUser = UserCache.playerId.get(scenes_01Banker.baner.get(0).getId());
        BankerDto bankerDto = new BankerDto(scenes_01Banker.jackpot,bankerUser);
        this.msg = bankerDto;
        this.response();
    }
}
