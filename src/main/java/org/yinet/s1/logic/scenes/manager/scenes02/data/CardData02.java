package org.yinet.s1.logic.scenes.manager.scenes02.data;

import io.netty.channel.Channel;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：泡泡大湿
 * 时间：Created by CL-PC202 on 2017/7/27.
 * 描述：
 */
public class CardData02 {
    public static List<ComparisonObj> scene02CardSet = new ArrayList<>();
    /**要发客户端的牌面*/
    public static List<Integer> scene02Card = new ArrayList<>();
    /**本剧四个玩家开了什么牌着或者赢*/

    public static Map<Channel, Long> cards1 = new HashMap<>();//第一推牌的玩家和钱
    public static Map<Channel, Long> cards2 = new HashMap<>();//第2推牌的玩家
    public static Map<Channel, Long> cards3 = new HashMap<>();//第3推牌的玩家(和)
    /**四堆牌的下注总钱*/
    public static long allMoney;

    public static long a;
    public static long b;
    public static long c;
    static Lock lock = new ReentrantLock();
    public static void addMoney(long money,int index){
        System.err.println(money);
        lock.lock();
        try {
                allMoney += money;

                switch (index){
                    case 1:
                        a+=money;
                    case 2:
                        b+=money;
                        break;
                    case 3:
                        c+=money;
                        break;
                }
        } finally {
            lock.unlock();
        }
    }
    public static void resetMoney(){
        lock.lock();
        try {
            allMoney = 0;
            a=0;
            b=0;
            c=0;
        } finally {
            lock.unlock();
        }
    }
}
