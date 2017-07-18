package org.yinet.s1.logic.login.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.manager.SerializerPlayerModel;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.dao.po.impl.User;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.error.coder.AppErrorCodes;
import org.yinet.s1.logic.login.dao.RequestLoginDao;
import org.yinet.s1.logic.login.dao.ResponseLoginDao;

/**
 * Created by ppdashi on 2017/7/15.
 */
@Service
public class Login {
    @Autowired
    private SerializerPlayerModel playerMaodel;
    public ResponseLoginDao process(Channel channel, RequestLoginDao loginDao){
        if(UserCache.accountMap.containsValue(loginDao.getAccount()))
            throw new AppGeneralError(channel, AppErrorCodes.ACCOUNT_OCCUPATION);
        Player player = playerMaodel.getPlayer(loginDao.getAccount());
        if(player == null)
            throw new AppGeneralError(channel, AppErrorCodes.NOT_ACCOUNT);
        if(player.getBasicInfo().getPassword() != loginDao.getPassword())
            throw new AppGeneralError(channel,AppErrorCodes.PASSWORD_MISTAKE);
        //登陆成功添加缓存
        UserCache.accountMap.put(channel,loginDao.getAccount());
        UserCache.idMap.put(channel,player.getId());
        UserCache.mapId.put(player.getId(),channel);
        UserCache.playerMap.put(channel,player);
        UserCache.playerId.put(player.getId(),player);

        return new ResponseLoginDao(player.getBasicInfo(),player.getResources());
    }
}
