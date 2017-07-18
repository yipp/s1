package org.yinet.s1.dao.mapper;

/**
 * Created by ppdashi on 2017/7/14.
 */
public class PlayerModel {
    private int id;
    private String basicInfo;
    private String resources;
    private String frients;
    private String account;
    public PlayerModel() {
    }

    public PlayerModel(int id, String basicInfo, String resources,String frients,String account) {
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

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getFrients() {
        return frients;
    }

    public void setFrients(String frients) {
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
        return "PlayerModel{" +
                "id=" + id +
                ", basicInfo='" + basicInfo + '\'' +
                ", resources='" + resources + '\'' +
                ", frients='" + frients + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
