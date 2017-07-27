package org.yinet.s1.error;

import org.yinet.s1.net.tcp.model.ResponseSerializer;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/22******
 */
public class ResponesErrorMsg implements ResponseSerializer {
    private String msg;

    public ResponesErrorMsg() {
    }

    public ResponesErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponesErrorMsg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
