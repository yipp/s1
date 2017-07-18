package org.yinet.s1.logic.scenes.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.manager.Scenes_01.Scenes_01;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;

/**
 * 从main场景请求其他的场景的处理
 * Created by ppdashi on 2017/7/16.
 */
@Service("action_3")
public class Action_3 extends ScenesLogicTask{
    short resultCode;
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

            default:
                break;
        }
        this.msg = resultCode;
        this.response();
    }
}
