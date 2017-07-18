package org.yinet.s1.logic.scenes.manager.Scenes_01;

import io.netty.channel.Channel;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class CardData {
    /**本剧四个玩家的牌*/
	public static List<ComparisonObj> scene01CardSet = new ArrayList<>();
	/**要发客户端的牌面*/
	public static List<Integer> scene04Card = new ArrayList<>();
	/**本剧四个玩家开了什么牌着或者赢*/
	public static List<Integer> scene01Result = new ArrayList<>();//本剧四个玩家开了什么牌

	public static Map<Channel, Long> cards1 = new HashMap<>();//第一推牌的玩家和钱
	public static Map<Channel, Long> cards2 = new HashMap<>();//第2推牌的玩家
	public static Map<Channel, Long> cards3 = new HashMap<>();//第3推牌的玩家
	public static Map<Channel, Long> cards4 = new HashMap<>();//第4推牌的玩家
	/**四堆牌的钱*/
	public static long money01;
	public static long money02;
	public static long money03;
	public static long money04;
	public static long jackpotScenes01 = 0L;
	static Lock lock = new ReentrantLock();
	public static void addMoney(long money,int index){
		System.err.println(money);
		lock.lock();
		 try {
			if(index == 1) money01 += money;
			if(index == 2) money02 += money;
			if(index == 3) money03 += money;
			if(index == 4) money04 += money;
		} finally {
			lock.unlock();
		}
	}
	public static void resetMoney(){
		System.err.println(money01+"---------");
		lock.lock();
		try {
			money01 = 0;
			money02 = 0;
			money03 = 0;
			money04 = 0;
		} finally {
			lock.unlock();
		}
	}
}