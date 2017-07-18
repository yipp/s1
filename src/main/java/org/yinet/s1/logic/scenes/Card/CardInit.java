package org.yinet.s1.logic.scenes.Card;

import org.springframework.stereotype.Service;

@Service
public class CardInit {
    public void addCar(){
		for (int i = 1; i <= 4; i++) {
			for(int j = 1;j <= 13;j++){
				if(i == 1){
					if(j%13 == 1) {
						ComparisonObj cObj = new ComparisonObj();
						cObj.setCardNumber(14);
						cObj.setCardType(i);
						cObj.cardMap.put(j, cObj);
						}else{
							ComparisonObj cObj = new ComparisonObj();
							cObj.setCardNumber(j);
							cObj.setCardType(i);
							cObj.cardMap.put(j, cObj);
						}
					}else {
						if(j%13 == 1){
							ComparisonObj cObj = new ComparisonObj();
							cObj.setCardNumber(14);
							cObj.setCardType(i);
							cObj.cardMap.put(j+(13*(i-1)), cObj);
						}else {
							ComparisonObj cObj = new ComparisonObj();
							cObj.setCardNumber(j);
							cObj.setCardType(i);
							cObj.cardMap.put(j+(13*(i-1)), cObj);
						}
					}
				}
			}
		//test();
	}
//	public void test(){
//		Set set = ComparisonObj.cardMap.entrySet();
//		for(Iterator iterator= set.iterator() ; iterator.hasNext();){
//			Map.Entry entry = (Map.Entry)iterator.next();
//			int key = (Integer)entry.getKey();
//			ComparisonObj cObj = (ComparisonObj)entry.getValue();
//			System.out.print("++++key:"+key+"cobj--->number:"+cObj.getCardNumber()+"cobj--->type:"+cObj.getCardType());
//		}
//	}
}