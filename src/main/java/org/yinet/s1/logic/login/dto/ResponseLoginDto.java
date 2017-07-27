package org.yinet.s1.logic.login.dto;

import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.net.tcp.model.ResponseSerializer;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class ResponseLoginDto implements ResponseSerializer {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String qq;
    private String phone;
    private int age;
    private int head;//头像
    private int headHint;//头像框
    private int vip;
    private long diamond;//钻石
    private long gold;
    private int ranking;

    public ResponseLoginDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
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

    public ResponseLoginDto(int id, String name, String userName, String password, String qq, String phone, int age, int head, int headHint, int vip, long diamond, long gold, int ranking) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.qq = qq;
        this.phone = phone;
        this.age = age;
        this.head = head;
        this.headHint = headHint;
        this.vip = vip;
        this.diamond = diamond;
        this.gold = gold;
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "ResponseLoginDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
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
