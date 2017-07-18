package org.yinet.s1.logic.login.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.excel.CoreBasic;
import org.yinet.s1.dao.manager.SerializerPlayerModel;
import org.yinet.s1.dao.mapper.PlayerMapper;
import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Player;
import org.yinet.s1.dao.po.basic.Resources;
import org.yinet.s1.error.AppGeneralError;
import org.yinet.s1.error.coder.AppErrorCodes;
import org.yinet.s1.logic.login.dao.RegisterDao;
import org.yinet.s1.serializer.excel.ExcelUtils;

import java.util.ArrayList;

/**
 * Created by ppdashi on 2017/7/15.
 */
@Service
public class Register {
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private BasicInfo basicInfo;
    @Autowired
    private Resources resources;
    @Autowired
    private Player player;
    @Autowired
    private SerializerPlayerModel model;
    public void process(Channel channel, RegisterDao register){
        //1，到缓存中查找有没有这个玩家
        if(UserCache.accountMap.containsValue(register.getAccount()))
            throw new AppGeneralError(channel, AppErrorCodes.ACCOUNT_OCCUPATION);
        //2,到数据中查找有没有这个玩家
        if(playerMapper.selectUserForAccount(register.getAccount()) != null)//少转换一次json格式
            throw new AppGeneralError(channel, AppErrorCodes.ACCOUNT_OCCUPATION);
        //3，从excel表中取值初始化玩家基本数据
        CoreBasic coreBasic = (CoreBasic) ExcelUtils.getExcelBean("CoreBasic1");
        basicInfo.setAge(coreBasic.getAge());
        basicInfo.setHead(coreBasic.getHead());
        basicInfo.setHeadHint(coreBasic.getHeadHint());

        resources.setVip(coreBasic.getVip());
        resources.setRanking(coreBasic.getRanking());
        resources.setGold(coreBasic.getGold());
        resources.setDiamond(coreBasic.getDiamond());

        player.setFrients(new ArrayList<>());
        player.setAccount(register.getAccount());
        player.setBasicInfo(basicInfo);
        player.setResources(resources);
        //添加到数据库中
        model.insertPlayer(player);
        //4，返回结果（通知注册成功）
    }
}
