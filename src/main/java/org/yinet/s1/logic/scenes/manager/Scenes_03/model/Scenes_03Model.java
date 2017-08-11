package org.yinet.s1.logic.scenes.manager.Scenes_03.model;

import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/2******
 * 描述：
 */
@Service
public class Scenes_03Model {
    /**九个点数堆*/
   private List<Map<Channel,Long>> moneyHeap = new ArrayList<>(9);
   private List<Channel> users = new ArrayList<>();
   public static final int[] multiple = {16,10,8,5,5,5,5,10,16};
    private List<Long> allMoney = new ArrayList<>(9);
    public List<Long> getAllMoney() {
        if(allMoney.isEmpty())
            for (int i = 0;i<9;i++)
                allMoney.add(0L);
        return allMoney;
    }

    public void setAllMoney(List<Long> allMoney) {
        this.allMoney = allMoney;
    }

    public Scenes_03Model() {
    }

    public List<Channel> getUsers() {
        return users;
    }

    public void setUsers(List<Channel> users) {
        this.users = users;
    }

    public List<Map<Channel, Long>> getMoneyHeap() {
        if(moneyHeap.isEmpty())
            for (int i = 0;i<9;i++)
                moneyHeap.add(new HashMap<>());
        return moneyHeap;
    }

    public void setMoneyHeap(List<Map<Channel, Long>> moneyHeap) {
        this.moneyHeap = moneyHeap;
    }

    /**
     * 下注
     * @param position 下注的牌堆位置
     * @param channel 下注的人的标记
     */
    public void bottomPour(Channel channel,int position,long money){
        long all = getAllMoney().get(position);
        all += money;
        getAllMoney().set(position,all);
        if(!getMoneyHeap().get(position).containsKey(channel))
            getMoneyHeap().get(position).put(channel,money);
        else {
            long moneys = getMoneyHeap().get(position).get(channel);
            moneys += money;
            getMoneyHeap().get(position).put(channel,moneys);
        }
    }

    /**
     * 清空本场所有记录
     */
    public void clear(){
        for (Map map:getMoneyHeap())
            map.clear();
        for (int i=0;i<9;i++)
            getAllMoney().set(i,0L);
    }
}
