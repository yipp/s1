package org.yinet.s1.logic.login.command;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.login.dao.RequestLoginDao;
import org.yinet.s1.logic.login.manager.Login;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 登陆
 * Created by ppdashi on 2017/7/15.
 */
@Service("action_2")
public class Action_2 extends LoginLogicTask {
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        RequestLoginDao requestLoginDao = ProtostuffUtils.deserializer(buf,RequestLoginDao.class);
        Login login = new Login();
        this.msg = login.process(this.channel,requestLoginDao);
        this.response();
    }
}
