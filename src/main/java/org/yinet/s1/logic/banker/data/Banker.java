package org.yinet.s1.logic.banker.data;


/**
 * 作者：泡泡大湿
 * 时间：*********
 */
public class Banker {
    /**庄家id*/
    private int id;
    /**连续在庄次数*/
    private int bankerNumber;

    public Banker() {
    }

    public Banker(int id, int bankerNumber) {
        this.id = id;
        this.bankerNumber = bankerNumber;
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
}
