package org.yinet.s1.logic.scenes.manager.scenes02.manager;

import org.springframework.stereotype.Service;
import org.yinet.s1.logic.scenes.Card.CardComparisonAbstract;
import org.yinet.s1.logic.scenes.Card.ComparisonObj;
import org.yinet.s1.logic.scenes.manager.scenes02.data.CardData02;

/**
 * 作者：泡泡大湿
 * 时间：Created by CL-PC202 on 2017/7/27.
 * 描述：
 */
@Service
public class CardComparisonScenes02  extends CardComparisonAbstract {
    @Override
    public void playerCard() {
//        System.out.println(CardData02.scene02CardSet.get(0).getCardNumber());
//        System.out.println(CardData02.scene02CardSet.get(1).getCardNumber());
        //龙
        if(CardData02.scene02CardSet.get(0).getCardNumber()>CardData02.scene02CardSet.get(1).getCardNumber()){
            this.sceneResult.add(10);
            this.sceneResult.add(-10);
        }
        //虎
        if(CardData02.scene02CardSet.get(0).getCardNumber()<CardData02.scene02CardSet.get(1).getCardNumber()){
            this.sceneResult.add(-10);
            this.sceneResult.add(10);
        }
        //和
        if(CardData02.scene02CardSet.get(0).getCardNumber()==CardData02.scene02CardSet.get(1).getCardNumber()){
            this.sceneResult.add(0);
            this.sceneResult.add(0);
        }
    }
}
