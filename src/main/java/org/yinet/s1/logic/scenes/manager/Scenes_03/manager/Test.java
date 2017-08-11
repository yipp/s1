package org.yinet.s1.logic.scenes.manager.Scenes_03.manager;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/2******
 * 描述：
 */
public class Test {
    public static void main(String[] args) {
        List<Map<Integer,Long>> moneyHeap = new ArrayList<>(9);
        //moneyHeap.get(5).put(1,5L);
        Map<Integer,Long> n = new HashMap<>();
        for (int i = 0;i < 12 ; i++) {
            moneyHeap.add(n);
        }
        System.out.println(moneyHeap);
    }
}
