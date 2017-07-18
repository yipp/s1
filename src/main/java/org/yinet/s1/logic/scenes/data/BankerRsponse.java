package org.yinet.s1.logic.scenes.data;

import org.springframework.stereotype.Service;
import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Resources;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/17******
 */
@Service
public class BankerRsponse {
    private BasicInfo basicInfo;
    private Resources resources;

    public BankerRsponse(BasicInfo basicInfo, Resources resources) {
        this.basicInfo = basicInfo;
        this.resources = resources;
    }

    public BankerRsponse() {
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
}
