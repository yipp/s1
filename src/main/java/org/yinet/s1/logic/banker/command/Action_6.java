package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.banker.manager.BankerUpScenes_01;
import org.yinet.s1.logic.login.model.LoginLogicTask;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 * 庄家系统(上庄)
 */
@Repository("action_6")
public class Action_6  extends LoginLogicTask {
    @Autowired
    private BankerUpScenes_01 bankerUp;
    @Override
    public void executor() {
        bankerUp.process(this.channel);
    }
}
