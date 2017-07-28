package org.yinet.s1.logic.banker.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01Banker;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
@Service
public class BankerDownScenes_1 extends Scenes_01Banker {
    @Autowired
    private Response response;
    @Autowired
    private Scenes_01Banker scenes_01Banker;
    public void prossec(Channel channel){
        scenes_01Banker.bankerDown(true);
        scenes_01Banker.bankerUp();
        response.setId(7);
        response.setDATA(null);
        channel.writeAndFlush(response);
    }
}
