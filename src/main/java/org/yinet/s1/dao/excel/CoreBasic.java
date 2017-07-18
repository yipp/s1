package org.yinet.s1.dao.excel;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class CoreBasic {
    private int id;
    private int age;
    private int head;
    private int headHint;
    private int vip;
    private long diamond;
    private long gold;
    private int ranking;

    public CoreBasic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getHeadHint() {
        return headHint;
    }

    public void setHeadHint(int headHint) {
        this.headHint = headHint;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "CoreBasic{" +
                "id=" + id +
                ", age=" + age +
                ", head=" + head +
                ", headHint=" + headHint +
                ", vip=" + vip +
                ", diamond=" + diamond +
                ", gold=" + gold +
                ", ranking=" + ranking +
                '}';
    }
}
