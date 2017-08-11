package org.yinet.s1.logic.scenes.manager.Scenes_03.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.logic.scenes.dto.BottomPourDao;
import org.yinet.s1.logic.scenes.manager.Scenes_03.dto.PlugDto;
import org.yinet.s1.logic.scenes.manager.Scenes_03.model.Scenes_03Model;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/2******
 * 描述：
 */
@Service
public class Scenes_03Manager {
    @Autowired
    private Scenes_03Model model;
    @Autowired
    private Resource resource;
    public List<Channel> getUser(){
        return model.getUsers();
    }
    public void setUser(Channel channel){model.getUsers().add(channel);}
    /**
     * 下注
     * @param channel
     * @param bottomPourDao
     */
    public void process(Channel channel, BottomPourDao bottomPourDao){
        model.bottomPour(channel,bottomPourDao.getPosition(),bottomPourDao.getMoney());
    }
    public void clear(){
        model.clear();
    }
    public void doExecutor(){
        int count1 = random();
        int count2 = random();
        int result = count1 + count2;
        if(result != 2 && result != 12){
            //结算
            closeAnAccount(result-3,Scenes_03Model.multiple[result-3]);
        }
        sendResult(count1,count2);
    }
    private int random(){
        return (int) (1+Math.random()*6);
    }

    public static void main(String[] args) {
        Scenes_03Manager manager = new Scenes_03Manager();
        for (int i = 0;i<50;i++)
       manager.doExecutor();
    }

    /**
     * 开奖咯
     */
    private void sendResult(int count1,int count2){
        Response response = new Response();
        response.setId(500);
        for (Channel channel:model.getUsers()) {
            PlugDto dto = new PlugDto(count1,count2,UserCache.playerMap.get(channel).getResource().getGold());
            byte[] bytes = ProtostuffUtils.serializer(dto);
            response.setDATA(bytes);
            channel.writeAndFlush(response);
        }
    }

    /**
     * 赢得人结算
     * @param index 赢得位置
     * @param multiple 赢的倍数
     */
    private void closeAnAccount(int index,int multiple){
        Map<Channel,Long> map = model.getMoneyHeap().get(index);
        for (Map.Entry<Channel,Long> e:map.entrySet() ) {
            long money = e.getValue() + e.getValue()*multiple;
            UserCache.playerMap.get(e.getKey()).getResource().addGold(money);
        }
    }
}
