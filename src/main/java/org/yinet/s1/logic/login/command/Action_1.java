package org.yinet.s1.logic.login.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.login.dto.RegisterDto;
import org.yinet.s1.logic.login.manager.Register;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.Arrays;

/**
 * Created by ppdashi on 2017/7/15.
 */
@Repository("action_1")
public class Action_1 extends LoginLogicTask {
    @Autowired
    private Register register;
    @Override
    public void executor() {
        byte[] buf = (byte[])this.msg;
        RegisterDto registerDao = ProtostuffUtils.deserializer(buf,RegisterDto.class);
        register.process(this.channel,registerDao);
        this.msg = null;
        this.response();
    }
}
