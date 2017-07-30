package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.banker.manager.BankerDownScenes_2;
import org.yinet.s1.logic.login.model.LoginLogicTask;

/**
 * 作者：泡泡大湿
 * 时间： 2017/7/27.
 * 描述：
 */
@Repository("action_10")
public class Action_10 extends LoginLogicTask {
    @Autowired
    private BankerDownScenes_2 bankerDown;
    @Override
    public void executor() {
        bankerDown.prossec(this.channel);
    }
}
