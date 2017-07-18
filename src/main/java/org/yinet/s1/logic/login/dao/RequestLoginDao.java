package org.yinet.s1.logic.login.dao;

import org.yinet.s1.net.tcp.model.RequestSerializer;

/**
 * 登陆请求
 * Created by ppdashi on 2017/7/15.
 */
public class RequestLoginDao implements RequestSerializer {
    private String account;
    private String password;

    public RequestLoginDao() {
    }

    public RequestLoginDao(String account, String password) {
        this.account = account;
        this.password = password;
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

    @Override
    public String toString() {
        return "RequestLoginDao{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
