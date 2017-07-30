package org.yinet.s1.logic.scenes.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.net.tcp.model.Response;

/**
 * 从main场景请求其他的场景的处理
 * Created by ppdashi on 2017/7/16.
 */
@Repository("action_3")
public class Action_3 extends ScenesLogicTask{
    short resultCode;
    @Autowired
    private Response response;
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(buf);
        resultCode = buffer.readShort();
        switch (resultCode) {
            case 1:
                Scenes_01.user.add(channel);//添加到扎金花场景
                break;
            case 2:
                Scenes_02.user.add(channel);
            default:
                break;
        }
        response.setId(3);
        buffer.writeShort(resultCode);
        response.setDATA(buffer.array());
        this.channel.writeAndFlush(response);
    }
}
