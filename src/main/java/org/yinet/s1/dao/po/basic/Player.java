package org.yinet.s1.dao.po.basic;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 存储玩家所有信息
 */
@Service
public class Player{
    private int id;
    private BasicInfo basicInfo;
    private Resource resources;
    /**只存好友id*/
    private List<Integer> frients;
    private String account;
    public Player() {
    }

    public Player(int id, BasicInfo basicInfo, Resource resources, List<Integer> frients, String account) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.resources = resources;
        this.frients = frients;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Resource getResources() {
        return resources;
    }

    public void setResources(Resource resources) {
        this.resources = resources;
    }

    public List<Integer> getFrients() {
        return frients;
    }

    public void setFrients(List<Integer> frients) {
        this.frients = frients;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", resources=" + resources +
                ", frients=" + frients +
                ", account='" + account + '\'' +
                '}';
    }
}
