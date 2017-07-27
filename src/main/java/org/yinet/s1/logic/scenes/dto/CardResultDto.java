package org.yinet.s1.logic.scenes.dto;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
public class CardResultDto {
    private List<Integer> cardFace;
    private List<Integer> result;

    public CardResultDto() {
    }

    public CardResultDto(List<Integer> cardFace, List<Integer> result) {

        this.cardFace = cardFace;
        this.result = result;
    }

    public List<Integer> getCardFace() {

        return cardFace;
    }

    public void setCardFace(List<Integer> cardFace) {
        this.cardFace = cardFace;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }
}
