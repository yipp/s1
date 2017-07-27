package org.yinet.s1.logic.login.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.manager.SerializerPlayerModel;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.error.coder.AppErrorCodes;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.login.dto.RequestLoginDto;
import org.yinet.s1.logic.login.dto.ResponseLoginDto;
/**
 * Created by ppdashi on 2017/7/15.
 */
@Service
public class Login {
    @Autowired
    private SerializerPlayerModel maodel ;
    public LoginDto process(Channel channel, RequestLoginDto loginDao){
//        if(UserCache.accountMap.containsValue(loginDao.getAccount()))
//            throw new AppGeneralError(channel, AppErrorCodes.ACCOUNT_OCCUPATION);
        Player player = maodel.getPlayer(loginDao.getAccount());
        if(player == null)
            throw new AppGeneralError(channel, AppErrorCodes.NOT_ACCOUNT);
        if(!player.getBasicInfo().getPassword().equals(loginDao.getPassword()))
            throw new AppGeneralError(channel,AppErrorCodes.PASSWORD_MISTAKE);

        ResponseLoginDto responseLoginDto = new ResponseLoginDto();

        responseLoginDto.setId(player.getId());
        responseLoginDto.setName(player.getBasicInfo().getName());
        responseLoginDto.setUserName(player.getBasicInfo().getUserName());
        responseLoginDto.setPassword(player.getBasicInfo().getPassword());
        responseLoginDto.setQq(player.getBasicInfo().getQq());
        responseLoginDto.setPhone(player.getBasicInfo().getPhone());
        responseLoginDto.setAge(player.getBasicInfo().getAge());
        responseLoginDto.setHead(player.getBasicInfo().getHead());
        responseLoginDto.setHeadHint(player.getBasicInfo().getHeadHint());
        responseLoginDto.setVip(player.getResources().getVip());
        responseLoginDto.setDiamond(player.getResources().getDiamond());
        responseLoginDto.setGold(player.getResources().getGold());
        responseLoginDto.setRanking(player.getResources().getRanking());

        LoginDto loginDto = new LoginDto(player.getId(),player.getBasicInfo(),player.getResources());
        System.err.println(loginDto);       //登陆成功添加缓存
        UserCache.accountMap.put(channel,loginDao.getAccount());
        UserCache.idMap.put(channel,player.getId());
        UserCache.mapId.put(player.getId(),channel);
        UserCache.playerMap.put(channel,loginDto);
        UserCache.playerId.put(player.getId(),loginDto);

        return loginDto;
    }
}
