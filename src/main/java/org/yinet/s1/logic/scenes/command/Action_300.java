package org.yinet.s1.logic.scenes.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.Scenes_03.manager.Scenes_03Manager;
import org.yinet.s1.logic.scenes.manager.Scenes_05.manager.Scenes_05Manager;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/8******
 * 描述：退出房间
 */
@Repository("action_300")
public class Action_300  extends ScenesLogicTask {

    @Autowired
    private Scenes_03Manager scenes_03;
    @Autowired
    private Scenes_05Manager scenes_05;
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(buf);
        short resultCode = buffer.readShort();
        System.out.println(resultCode);
        switch (resultCode){
            case 0://退出游戏
                break;
            case 1:
                Scenes_01.user.remove(this.channel);
                break;
            case 2:
                Scenes_02.user.remove(this.channel);
                break;
            case 3:
                scenes_03.getUser().remove(this.channel);
                break;
            case 4:
                break;
            case 5:
                scenes_05.getUser().remove(this.channel);
                break;
        }
        this.msg = null;
        this.response();
    }
}
