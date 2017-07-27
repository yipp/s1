package org.yinet.s1.logic.login.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.login.dto.RequestLoginDto;
import org.yinet.s1.logic.login.manager.Login;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.Arrays;

/**
 * 登陆
 * Created by ppdashi on 2017/7/15.
 */
@Repository("action_2")
public class Action_2 extends LoginLogicTask {
    @Autowired
    private  Login login;
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        RequestLoginDto requestLoginDto = ProtostuffUtils.deserializer(buf,RequestLoginDto.class);
        this.msg = login.process(this.channel,requestLoginDto);
        this.response();
    }
}
