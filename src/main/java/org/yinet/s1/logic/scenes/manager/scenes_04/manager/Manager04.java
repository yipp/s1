package org.yinet.s1.logic.scenes.manager.scenes_04.manager;

import io.netty.channel.Channel;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/11******
 * 描述：
 */
@Service
public class Manager04 {
    public void process(Channel channel,long money){
        LoginDto dto = UserCache.playerMap.get(channel);
        dto.getResource().setGold(money);
        System.out.println(dto.getResource().getGold());
    }
}
