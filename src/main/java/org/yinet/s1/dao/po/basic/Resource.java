package org.yinet.s1.dao.po.basic;

import org.springframework.stereotype.Service;
import org.yinet.s1.dao.po.impl.AbstractUserImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 角色资源
 */
@Service
public class Resource extends AbstractUserImpl {
    private int id;
    private int vip;
    private long diamond;//钻石
    private long gold;
    private int ranking;

    public Resource() {
    }

    public Resource(int id, int vip, long diamond, long gold, int ranking) {
        this.vip = vip;
        this.diamond = diamond;
        this.gold = gold;
        this.ranking = ranking;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Resources{" +
                "vip=" + vip +
                ", diamond=" + diamond +
                ", gold=" + gold +
                ", ranking=" + ranking +
                ", id=" + id +
                '}';
    }

    /**
     * 减金币
     */
    public void reduceGold(long gold){
        lock.lock();
        try {
            long money = this.getGold();
            money -= gold;
            this.setGold(money);
            this.update(this);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 增加金币
     */
    private Lock lock = new ReentrantLock();
    public void addGold(long gold){
        lock.lock();
        try {
            long money = this.getGold();
            money += gold;
            this.setGold(money);
            this.update(this);
        }finally {
            lock.unlock();
        }
    }
}
