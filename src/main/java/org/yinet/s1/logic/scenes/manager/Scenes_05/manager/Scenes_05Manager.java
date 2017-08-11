package org.yinet.s1.logic.scenes.manager.Scenes_05.manager;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.dto.BottomPourDao;
import org.yinet.s1.logic.scenes.manager.Scenes_05.model.Scenes_05Model;
import org.yinet.s1.logic.scenes.model.ScenesManager.ScenesAbstract;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/6******
 * 描述：
 */
@Service
public class Scenes_05Manager extends ScenesAbstract {
    @Autowired
    private Scenes_05Model model;
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

    @Override
    protected void insert(int[] result) {

    }
    @Override
    public void clear(){
        model.clear();
    }

    @Override
    public void sendResult() {

    }

    @Override
    public void doExecutor() {

    }

}
