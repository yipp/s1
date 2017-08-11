package org.yinet.s1.logic.scenes.manager.scenes_04.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.logic.scenes.manager.scenes_04.manager.Manager04;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/11******
 * 描述：场景04的结算请求
 */
@Repository("action_16")
public class Action_16  extends LoginLogicTask{
    @Autowired
    private Manager04 manager04;
    @Override
    public void executor() {
        byte[] bytes = (byte[]) this.msg;
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(bytes);
        long money = buf.readLong();
        manager04.process(this.channel,money);
    }
}
