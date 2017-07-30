package org.yinet.s1.logic.scenes.manager.scenes02.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.banker.dto.BankerDto;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02Banker;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/30******
 * 描述：响应场景2信息
 */
@Repository("action_12")
public class Action_12 extends ScenesLogicTask {
    @Autowired
    private Scenes_02Banker scenes_02Banker;
    @Override
    public void executor() {
        System.out.println("呵呵大");
        //回发信息
        //1，根据id取到庄家的数据
        LoginDto bankerUser = null;
        if(!scenes_02Banker.baner.isEmpty())
            bankerUser = UserCache.playerId.get(scenes_02Banker.baner.get(0).getId());
        BankerDto bankerDto = new BankerDto(scenes_02Banker.jackpot,bankerUser);
        this.msg = bankerDto;
        this.response();
    }
}
