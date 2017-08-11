package org.yinet.s1.logic.scenes.manager.Scenes_03.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.logic.scenes.dto.BottomPourDao;
import org.yinet.s1.logic.scenes.dto.ResponseBottomPourDto;
import org.yinet.s1.logic.scenes.manager.Scenes_03.manager.Scenes_03Manager;
import org.yinet.s1.logic.scenes.manager.Scenes_03.model.Scenes_03Model;
import org.yinet.s1.logic.scenes.model.ScenesLogicTask;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/3******
 * 描述：场景4下注
 */
@Repository("action_14")
public class Action_14  extends ScenesLogicTask {
    @Autowired
    private Scenes_03Manager manager;
    @Autowired
    private Response response;
    @Autowired
    private Scenes_03Model model;
    @Autowired
    private Resource resources;
    @Override
    public void executor() {
        byte[] buf = (byte[]) this.msg;
        BottomPourDao dto = ProtostuffUtils.deserializer(buf,BottomPourDao.class);
        resources = UserCache.playerMap.get(this.channel).getResource();
        resources.reduceGold(dto.getMoney());
        System.out.println(dto.getPosition()+":"+dto.getMoney());
        manager.process(this.channel,dto);
        response.setId(14);
        ResponseBottomPourDto pourDto = new ResponseBottomPourDto(resources.getGold(),model.getAllMoney());
        this.msg = pourDto;
        this.response();
    }
}
