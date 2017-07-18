package org.yinet.s1.logic.scenes.dao;

import org.yinet.s1.logic.scenes.data.BankerRsponse;
import org.yinet.s1.net.tcp.model.RequestSerializer;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/17******
 */
public class BankerDao implements RequestSerializer {
    BankerRsponse banker;
    List<BankerRsponse> bankerList;

    public BankerDao(BankerRsponse banker, List<BankerRsponse> bankerList) {
        this.banker = banker;
        this.bankerList = bankerList;
    }

    public BankerDao() {
    }

    public BankerRsponse getBanker() {
        return banker;
    }

    public void setBanker(BankerRsponse banker) {
        this.banker = banker;
    }

    public List<BankerRsponse> getBankerList() {
        return bankerList;
    }

    public void setBankerList(List<BankerRsponse> bankerList) {
        this.bankerList = bankerList;
    }
}
