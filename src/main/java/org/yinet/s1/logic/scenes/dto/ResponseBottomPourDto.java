package org.yinet.s1.logic.scenes.dto;

import org.yinet.s1.net.tcp.model.RequestSerializer;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 * 下注成功返回客户端信息
 */
public class ResponseBottomPourDto implements RequestSerializer {
    private long money;
    //牌堆的钱
    private List<Long> cardPile;

    public ResponseBottomPourDto() {
    }

    public ResponseBottomPourDto(long money, List<Long> cardPile) {
        this.money = money;
        this.cardPile = cardPile;
    }

    public long getMoney() {

        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public List<Long> getCardPile() {
        return cardPile;
    }

    public void setCardPile(List<Long> cardPile) {
        this.cardPile = cardPile;
    }
}
