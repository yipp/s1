package org.yinet.s1.logic.scenes.model.ScenesManager;

import io.netty.channel.Channel;
import org.yinet.s1.logic.scenes.dto.SettleAccountsDao;

import java.util.Map;

/**
 * Created by ppdashi on 2017/7/16.
 */
public abstract class ScenesAbstract{
    /**一场游戏多长时间*/
    protected int time;
    /**计时器*/
    protected int timer;
    /**本场是否结束*/
    protected boolean end;
    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */

    protected void randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        insert(result);
    }
    /**添加牌面*/
    protected abstract void insert(int[] result);
    /**清除本场的下注数据*/
    public abstract void clear();
    /**向客户端发送本场游戏结果和金币的加减*/
    public abstract void sendResult(Map<Channel,Long> map);
    /**发牌-->比牌--->得到结果*/
    public abstract void doExecutor();
}
