package org.yinet.s1.logic.scenes.manager.Scenes_03.dto;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/8/5******
 * 描述：
 */
public class PlugDto {
    private int count1;
    private int count2;
    private long money;

    public PlugDto() {
    }

    public PlugDto(int count1, int count2, long money) {

        this.count1 = count1;
        this.count2 = count2;
        this.money = money;
    }

    public int getCount1() {

        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
