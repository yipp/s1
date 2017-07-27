package org.yinet.s1.logic.banker.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.banker.dto.BankerListDto;
import org.yinet.s1.logic.banker.manager.BankerListScenes_02;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.login.model.LoginLogicTask;
import org.yinet.s1.net.tcp.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：泡泡大湿
 * 时间： 2017/7/27.
 * 描述：
 */
@Repository("action_11")
public class Action_11 extends LoginLogicTask {
    @Autowired
    private BankerListScenes_02 banker;
    @Autowired
    private Response response;
    @Override
    public void executor() {
        if(!banker.bankerList.isEmpty()){
            List<LoginDto> arr = new ArrayList<>();
            for (int i:banker.bankerList ) {
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
