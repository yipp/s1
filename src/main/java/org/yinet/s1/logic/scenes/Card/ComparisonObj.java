package org.yinet.s1.logic.scenes.Card;

import java.util.HashMap;
import java.util.Map;

public class ComparisonObj {
    public static Map<Integer, ComparisonObj> cardMap = new HashMap<>();
	private int cardNumber;
	private int cardType;
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	
}