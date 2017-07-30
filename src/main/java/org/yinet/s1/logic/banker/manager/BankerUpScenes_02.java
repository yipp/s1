package org.yinet.s1.logic.banker.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.error.coder.AppErrorCodes;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02Banker;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
@Service
public class BankerUpScenes_02 extends Scenes_02Banker {
    @Autowired
    private Scenes_02Banker scenes_02Banker;
    public void process(Channel channel){
        scenes_02Banker.upId = 9;
        scenes_02Banker.downId = 10;
        scenes_02Banker.user = Scenes_02.user;
        if(scenes_02Banker.bankerList.contains(UserCache.idMap.get(channel)))
            throw new AppGeneralError(channel, AppErrorCodes.INSERT_BANKER_LIST);
        if(!scenes_02Banker.baner.isEmpty())
            if(scenes_02Banker.baner.get(0).getId() == UserCache.idMap.get(channel))
                throw new AppGeneralError(channel, AppErrorCodes.YOU_ARE_BANKER);
        scenes_02Banker.bankerList.add(UserCache.idMap.get(channel));
        scenes_02Banker.bankerUp();
    }
}
