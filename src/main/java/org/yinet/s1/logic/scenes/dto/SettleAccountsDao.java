package org.yinet.s1.logic.scenes.dto;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/18******
 */
public class SettleAccountsDao {
    private long bankerMoney;
    private long userMoney;

    public SettleAccountsDao() {
    }

    public SettleAccountsDao(long bankerMoney, long userMoney) {
        this.bankerMoney = bankerMoney;
        this.userMoney = userMoney;
    }

    public long getBankerMoney() {

        return bankerMoney;
    }

    public void setBankerMoney(long bankerMoney) {
        this.bankerMoney = bankerMoney;
    }

    public long getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(long userMoney) {
        this.userMoney = userMoney;
    }
}
