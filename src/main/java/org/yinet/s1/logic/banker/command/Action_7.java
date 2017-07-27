package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.banker.manager.BankerDownScenes_1;
import org.yinet.s1.logic.login.model.LoginLogicTask;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 * 下庄
 */
@Repository("action_7")
public class Action_7 extends LoginLogicTask {
    @Autowired
    private BankerDownScenes_1 bankerDown;
    @Override
    public void executor() {
        this.msg =  bankerDown.prossec(this.channel);
        this.response();
    }
}
