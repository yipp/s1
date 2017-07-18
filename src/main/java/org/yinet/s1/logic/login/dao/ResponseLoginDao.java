package org.yinet.s1.logic.login.dao;

import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Resources;
import org.yinet.s1.net.tcp.model.ResponseSerializer;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class ResponseLoginDao implements ResponseSerializer {
    private BasicInfo basicInfo;
    private Resources resources;

    public ResponseLoginDao() {
    }

    public ResponseLoginDao(BasicInfo basicInfo, Resources resources) {
        this.basicInfo = basicInfo;
        this.resources = resources;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "ResponseLoginDao{" +
                "basicInfo=" + basicInfo +
                ", resources=" + resources +
                '}';
    }
}
