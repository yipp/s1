package org.yinet.s1.logic.scenes.data;

import org.springframework.stereotype.Service;

/**
 * 作者：泡泡大湿
 * 时间：*********
 */
public class Banker {
    /**庄家id*/
    private int id;
    /**连续在庄次数*/
    private int bankerNumber;
    /**庄家金币*/
    private long money;

    public Banker(int id, int bankerNumber, long money) {
        this.id = id;
        this.bankerNumber = bankerNumber;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankerNumber() {
        return bankerNumber;
    }

    public void setBankerNumber(int bankerNumber) {
        this.bankerNumber = bankerNumber;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
