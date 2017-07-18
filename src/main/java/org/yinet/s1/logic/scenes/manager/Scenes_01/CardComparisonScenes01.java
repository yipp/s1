package org.yinet.s1.logic.scenes.manager.Scenes_01;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.Card.CardComparisonAbstract;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;

/**
 * Created by ppdashi on 2017/7/16.
 */
@Service
public class CardComparisonScenes01 extends CardComparisonAbstract {
    /**
     * 比牌
     */
    @Override
    public void playerCard() {
        ComparisonObj[] objs1 = getCard(0,3);
        ComparisonObj[] objs2 = getCard(3,6);
        ComparisonObj[] objs3 = getCard(6,9);
        ComparisonObj[] objs4 = getCard(9,12);
        ComparisonObj[] objs5 = getCard(12,15);
        winOrLose(objs1);
        if(both()){
            num1 = num;
            nums1 = nums;
        }
        objs1 = cardArr;

        winOrLose(objs2);
        if(both()){
            num2 = num;
            nums2 = nums;
        }
        objs2 = cardArr;

        winOrLose(objs3);
        if(both()){
            num3 = num;
            nums3 = nums;
        }
        objs3 = cardArr;

        winOrLose(objs4);
        if(both()){
            num4 = num;
            nums4 = nums;
        }
        objs4 = cardArr;

        winOrLose(objs5);
        if(both()){
            num5 = num;
            nums5 = nums;
        }
        objs5 = cardArr;

        this.com(objs1,objs2,1,num2,nums2);
        this.com(objs1,objs3,2,num3,nums3);
        this.com(objs1,objs4,3,num4,nums4);
        this.com(objs1,objs5,4,num5,nums5);
    }
}
