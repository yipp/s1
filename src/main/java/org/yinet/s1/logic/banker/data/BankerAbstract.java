package org.yinet.s1.logic.banker.data;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.cache.core.UserCache;
import org.yinet.s1.logic.login.dto.LoginDto;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppdashi on 2017/7/16.
 */
@Service
public abstract class BankerAbstract {
   public Banker bankers = null;
   public List<Banker> baner = new ArrayList<>();
   public static long jackpot = 100000;//奖池
    public long bankerMoney = 0;
    public int upId;
    public int downId;
    public List<Channel> user;
    /**
     * 上装列表
     */
    public List<Integer> bankerList = new ArrayList<>();
    public final static int SYSTEM_BANKER_ID = -77888;//系统庄家id

    /**
     * 上庄
     */
    public void bankerUp() {
        //如果当前没有庄家
        if (baner.isEmpty()) {
            if (!bankerList.isEmpty()) {
                int bankerId = bankerList.get(0);//添加庄家
                LoginDto player = UserCache.playerId.get(bankerId);
                bankers = new Banker(player.getId(),0);
                baner.add(bankers);
                bankerList.remove(0);//从上庄列表中移除
            }
            send();
        }
    }
    private void send(){
        Response response = new Response();
        LoginDto loginDto = null;
        if(!this.baner.isEmpty()){
            loginDto = UserCache.playerId.get(this.baner.get(0).getId());
        }
        response.setId(upId);
        if(loginDto != null) {
            byte[] buf = ProtostuffUtils.serializer(loginDto);
            response.setDATA(buf);
        }else{
            response.setDATA(null);
        }
        for (Channel channel1: user) {
                channel1.writeAndFlush(response);
        }
    }
    /**
     * 下庄
     * @param bankerDown：是否自愿下庄
     */
    public void bankerDown(boolean bankerDown) {
        //同一个玩家连续在庄10把或者玩家主动下庄
        System.out.println("我要下庄了");
        if (baner.get(0).getBankerNumber() >= 20 || bankerDown) {
            int id = baner.get(0).getId();
            Response response = new Response();
            response.setId(downId);
            response.setDATA(null);
            System.out.println(UserCache.mapId.get(id));
            UserCache.mapId.get(id).writeAndFlush(response);
           baner.clear();
        }
        bankerUp();
    }
}
