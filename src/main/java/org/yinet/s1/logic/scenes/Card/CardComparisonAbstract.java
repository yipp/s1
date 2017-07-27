package org.yinet.s1.logic.scenes.Card;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.manager.Scenes_01.data.CardData;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CardComparisonAbstract {
    protected ComparisonObj[] cardArr;
    public List<Integer> sceneResult = new ArrayList<>();
	protected int num;//哪个散牌
	protected int nums;//对己
	protected int num1 = 0;
	protected int num2 = 0;
	protected int num3 = 0;
	protected int num4 = 0;
	protected int num5 = 0;
	protected int nums1 = 0;
	protected int nums2 = 0;
	protected int nums3 = 0;
	protected int nums4 = 0;
	protected int nums5 = 0;
	private void addCardArr(ComparisonObj[] card){
		for (int i = 1; i < card.length; i++) {
			for(int j = 0;j<card.length-i;j++){
				if(card[j].getCardNumber()>card[j+1].getCardNumber()){
					ComparisonObj temp = card[j];
					card[j] = card[j+1];
					card[j+1] = temp;
				}
			}
		}
		cardArr = card;
	}
	/**
	 * 炸
	 */
	private boolean explode(){
		if(cardArr[0].getCardNumber() == cardArr[1].getCardNumber() && cardArr[1].getCardNumber() == cardArr[2].getCardNumber()) return true;
		return false;
	}
	/**
	 * 对子
	 */
	protected boolean both(){
		if(cardArr[0].getCardNumber() == cardArr[1].getCardNumber()){
			num = (cardArr[2].getCardNumber() == 0 ? 13:cardArr[2].getCardNumber());
			nums = (cardArr[0].getCardNumber() == 0 ? 13:cardArr[0].getCardNumber());
			return true;
		} 
		else if(cardArr[1].getCardNumber() == cardArr[2].getCardNumber()){
			num = (cardArr[0].getCardNumber() == 0 ? 13:cardArr[0].getCardNumber());
			nums = (cardArr[1].getCardNumber() == 0 ? 13:cardArr[1].getCardNumber());		
			return true;
		}
		else if(cardArr[0].getCardNumber() == cardArr[2].getCardNumber()) {
			num = (cardArr[1].getCardNumber() == 0 ? 13:cardArr[1].getCardNumber());
			nums = (cardArr[0].getCardNumber() == 0 ? 13:cardArr[0].getCardNumber());
			return true;
		}
		
		return false;
	}
	/**
	 * 同花
	 */
	private boolean same(){
		if(cardArr[0].getCardType()== cardArr[1].getCardType() && cardArr[1].getCardType() == cardArr[2].getCardType()) return true;
		return false;
	}
	/**
	 * 顺子
	 */
	private boolean obey(){
		if(cardArr[2].getCardNumber() != 14){
			if(cardArr[0].getCardNumber() == (cardArr[1].getCardNumber()-1) && cardArr[1].getCardNumber() == (cardArr[2].getCardNumber()-1)) return true;
		}else{
			if(cardArr[0].getCardNumber() == 2 && cardArr[0].getCardNumber() == 3) return true;
		}

		return false;
	}
	/**
	 * 同花顺
	 */
	private boolean sameAndOby(){
		if(same() && obey()) return true;
		return false;
	}
	/**
	 * 扎金花比输赢
	 */
	protected void winOrLose(ComparisonObj[] card){
		addCardArr(card);
		if(explode()) sceneResult.add(CardResult.CardType.Bomb.Id());//炸
		else if(sameAndOby()) sceneResult.add(CardResult.CardType.SameAndObey.Id());//同花顺
		else if(same()) sceneResult.add(CardResult.CardType.Same.Id());//同花
		else if(obey()) sceneResult.add(CardResult.CardType.Obey.Id());//顺子
		else if(both()) sceneResult.add(CardResult.CardType.Both.Id());//对子
		else sceneResult.add(CardResult.CardType.HighCard.Id());//散牌
	}
	/**开始比较牌面*/
	public abstract void playerCard();
	/**比较之后的输赢*/
	protected void com(ComparisonObj[] card0,ComparisonObj[] card1,int index,int comeLoose,int botn){
		
		//System.out.println(CardData.scene01Result.get(0)+"和"+CardData.scene01Result.get(index));
		if(sceneResult.get(0) == sceneResult.get(index)){
			
			if (sceneResult.get(0) == CardResult.CardType.Bomb.Id()) {//炸1或者普通炸
				if(card0[2].getCardNumber() > card1[2].getCardNumber()){
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}
				else {
					sceneResult.add(CardResult.ResultType.Win.Id());
				}
			}
			if(sceneResult.get(0) == CardResult.CardType.SameAndObey.Id()||sceneResult.get(0) == CardResult.CardType.Obey.Id()) {//同花顺或者普通的顺子
				if(card1[2].getCardNumber() == 14) sceneResult.add(CardResult.ResultType.Lose.Id());//1，2，3的顺子
				else if(card0[2].getCardNumber() == 14 && card0[2].getCardNumber() != 14) {
					sceneResult.add(CardResult.ResultType.Win.Id());//庄家是1，2，3玩家不是
				}
				else if(card0[2].getCardNumber() >= card1[2].getCardNumber()){
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}
				else{
					sceneResult.add(CardResult.ResultType.Win.Id());
				}
			}
			if(sceneResult.get(0) == CardResult.CardType.Same.Id()||sceneResult.get(0) == CardResult.CardType.HighCard.Id()) {//同花或者散牌
				if(card0[2].getCardNumber() == card1[2].getCardNumber()&&card0[1].getCardNumber() == card1[2].getCardNumber()&&card0[0].getCardNumber() == card1[0].getCardNumber()){
					sceneResult.add(CardResult.ResultType.Lose.Id());//牌面一样
				}else if(card0[0].getCardNumber() > card1[2].getCardNumber()){//庄最小的比玩家最大的大
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}else if(card0[1].getCardNumber() > card1[2].getCardNumber()){//庄第二大的比玩家最大的大
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}else if(card0[2].getCardNumber() > card1[2].getCardNumber()){//庄第3大的比玩家最大的大
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}else if(card0[2].getCardNumber() == card1[2].getCardNumber() && card0[1].getCardNumber() > card1[1].getCardNumber()){
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}else if(card0[2].getCardNumber() == card1[2].getCardNumber() && card0[1].getCardNumber() == card1[1].getCardNumber() &&  card0[0].getCardNumber() > card1[0].getCardNumber()){
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}else {
					sceneResult.add(CardResult.ResultType.Win.Id());
				}
			}
			if(sceneResult.get(0) == CardResult.CardType.Both.Id()){//对子
				if(nums1 == botn){
					if(num1>comeLoose){
						sceneResult.add(CardResult.ResultType.Lose.Id());
					}
					else {
						sceneResult.add(CardResult.ResultType.Win.Id());
					}
				}else if(nums1 > botn){
					sceneResult.add(CardResult.ResultType.Lose.Id());
				}
				else{
					sceneResult.add(CardResult.ResultType.Win.Id());
				}
			}
		}else if(sceneResult.get(0) > sceneResult.get(index)){
			sceneResult.add(CardResult.ResultType.Lose.Id());
		}else {
			sceneResult.add(CardResult.ResultType.Win.Id());
		}
	}

	protected ComparisonObj[] getCard(int start,int end){
		ComparisonObj[] objs = new ComparisonObj[3];
		for(int i = start;i<end;i++) {
			objs[i - start] = CardData.scene01CardSet.get(i);
		}
		return objs;
	}
}