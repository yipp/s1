package org.yinet.s1.logic.banker.manager;

import io.netty.channel.Channel;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.error.coder.AppErrorCodes;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01Banker;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
@Service
public class BankerUpScenes_01 extends Scenes_01Banker{
    public void process(Channel channel){
        if(bankerList.contains(UserCache.idMap.get(channel)))
            throw new AppGeneralError(channel, AppErrorCodes.INSERT_BANKER_LIST);
        if(!this.baner.isEmpty())
            if(this.baner.get(0).getId() == UserCache.idMap.get(channel))
                throw new AppGeneralError(channel, AppErrorCodes.YOU_ARE_BANKER);
        this.bankerList.add(UserCache.idMap.get(channel));
        this.bankerUp();
    }
}
