package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.banker.dto.BankerListDto;
import org.yinet.s1.logic.banker.manager.BankerListScenes_01;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01Banker;
import org.yinet.s1.net.tcp.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/24******
 * 描述：上庄列表
 */
@Repository("action_8")
public class Action_8  extends LoginLogicTask {
    @Autowired
    private Scenes_01Banker scenes_01Banker;
    @Override
    public void executor() {
        System.out.println(scenes_01Banker.bankerList.size()+"xxxxxxxxxxxxxxxxxxx");
        if(!scenes_01Banker.bankerList.isEmpty()){
            List<LoginDto> arr = new ArrayList<>();
            for (int i:scenes_01Banker.bankerList ) {
                LoginDto dto = UserCache.playerId.get(i);
                arr.add(dto);
            }
            BankerListDto dto = new BankerListDto(arr);
            this.msg = dto;
        }else
            this.msg = null;
        this.response();
    }
}
