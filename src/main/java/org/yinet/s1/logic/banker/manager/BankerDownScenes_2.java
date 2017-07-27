package org.yinet.s1.logic.banker.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
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
    public LoginDto prossec(Channel channel){
        this.bankerDown(true);
        this.bankerUp();
        LoginDto loginDto = null;
        if(!this.baner.isEmpty()){
            loginDto = UserCache.playerId.get(this.baner.get(0).getId());
        }
        response.setId(7);
        if(loginDto != null) {
            byte[] buf = ProtostuffUtils.serializer(loginDto);
            response.setDATA(buf);
        }else{
            response.setDATA(null);
        }
        for (Channel channel1: Scenes_01.user) {
            if(channel1 != channel)
                channel1.writeAndFlush(response);
        }
        return loginDto;
    }
}
