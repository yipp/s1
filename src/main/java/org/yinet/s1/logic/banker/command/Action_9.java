package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.banker.manager.BankerUpScenes_02;
import org.yinet.s1.logic.login.model.LoginLogicTask;

/**
 * 作者：泡泡大湿
 * 时间： 2017/7/27.
 * 描述：
 */
@Repository("action_9")
public class Action_9 extends LoginLogicTask {
    @Autowired
    private BankerUpScenes_02 bankerUp;
    @Override
    public void executor() {
        bankerUp.process(this.channel);
    }
}
