package org.yinet.s1.logic.scenes.manager.scenes02.manager;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.Card.CardComparisonAbstract;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;

/**
 * 作者：泡泡大湿
 * 时间：Created by CL-PC202 on 2017/7/27.
 * 描述：
 */
@Service
public class CardComparisonScenes02  extends CardComparisonAbstract {
    @Override
    public void playerCard() {
        ComparisonObj[] objs1 = getCard(0,3);
        ComparisonObj[] objs2 = getCard(3,6);
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
        this.com(objs1,objs2,1,num2,nums2);
    }
}
