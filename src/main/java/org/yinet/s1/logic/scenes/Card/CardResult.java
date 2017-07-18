package org.yinet.s1.logic.scenes.Card;

/**
 * Created by ppdashi on 2017/7/16.
 */
public class CardResult {

    public enum CardType {
        /**
         * 散牌
         */
        HighCard(1),
        /**
         * 对子
         */
        Both(2),
        /**
         * 顺子
         */
        Obey(3),
        /**
         * 同化
         */
        Same(4),
        /**
         * 同化顺
         */
        SameAndObey(5),
        /**
         * 炸
         */
        Bomb(6);
        private final int id;

        CardType(int id) {
            this.id = id;
        }

        public int Id() {
            return id;
        }
    }
    public enum ResultType{
        Lose(-10),
        Win(10),
        Peace(5);
        private final int id;
        ResultType(int id){
            this.id = id;
        }
        public int Id(){
            return id;
        }
    }
}
