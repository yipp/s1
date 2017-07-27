package org.yinet.s1.logic.scenes.manager.Scenes_01.data;

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

	public static Map<Channel, Long> cards1 = new HashMap<>();//第一推牌的玩家和钱
	public static Map<Channel, Long> cards2 = new HashMap<>();//第2推牌的玩家
	public static Map<Channel, Long> cards3 = new HashMap<>();//第3推牌的玩家
	public static Map<Channel, Long> cards4 = new HashMap<>();//第4推牌的玩家
	/**四堆牌的下注总钱*/
	public static long allMoney;

	public static long a;
	public static long b;
	public static long c;
	public static long d;

	public static long jackpotScenes01 = 0L;
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
				case 4:
					d+=money;
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
			d=0;
		} finally {
			lock.unlock();
		}
	}
}