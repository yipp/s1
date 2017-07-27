package org.yinet.s1.logic.login.dto;


import org.yinet.s1.net.tcp.model.RequestSerializer;

/**
 * 注册请求
 * Created by ppdashi on 2017/7/15.
 */
public class RegisterDto implements RequestSerializer {
    private String account;
    private String password;
    private String name;
    private int age;
    private String gender;

    public RegisterDto() {
    }

    public RegisterDto(String account, String password, String name, int age, String gender) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
