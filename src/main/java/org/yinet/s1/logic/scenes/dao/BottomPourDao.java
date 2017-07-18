package org.yinet.s1.logic.scenes.dao;

import org.yinet.s1.net.tcp.model.RequestSerializer;

/**
 * 作者：泡泡大湿
 * 时间：*********
 */
public class BottomPourDao  implements RequestSerializer{
    /**下注的位置（那个钱堆）*/
    private int position;
    /**下注的额度*/
    private long money;

    public BottomPourDao() {
    }

    public BottomPourDao(int position, int money) {
        this.position = position;
        this.money = money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BottomPourDao{" +
                "position=" + position +
                ", money=" + money +
                '}';
    }
}
