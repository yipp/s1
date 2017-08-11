package org.yinet.s1.logic.banker.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02Banker;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
@Service
public class BankerDownScenes_2 extends Scenes_02Banker {
    @Autowired
    private Response response;
    @Autowired
    private Scenes_02Banker scenes_02Banker;
    public void prossec(Channel channel){
        scenes_02Banker.bankerDown(true);
        scenes_02Banker.bankerUp();
        response.setId(10);
        response.setDATA(null);
        channel.writeAndFlush(response);
    }
}
