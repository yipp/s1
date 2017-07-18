package org.yinet.s1.logic.login.command;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.login.dao.RegisterDao;
import org.yinet.s1.logic.login.manager.Register;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * Created by ppdashi on 2017/7/15.
 */
@Service("action_1")
public class Action_1 extends LoginLogicTask {
    @Override
    public void executor() {
        byte[] buf = (byte[])this.msg;
        RegisterDao registerDao = ProtostuffUtils.deserializer(buf,RegisterDao.class);
        Register register = new Register();
        register.process(this.channel,registerDao);
        this.msg = null;
        this.response();
    }
}
