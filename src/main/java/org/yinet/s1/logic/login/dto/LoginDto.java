package org.yinet.s1.logic.login.dto;

import org.yinet.s1.dao.po.basic.BasicInfo;
import org.yinet.s1.dao.po.basic.Resource;
import org.yinet.s1.net.tcp.model.ResponseSerializer;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/22******
 */
public class LoginDto implements ResponseSerializer {
    private int id;
    private BasicInfo basicInfo;
    private Resource resource;

    public LoginDto() {
    }

    public LoginDto(int id, BasicInfo basicInfo, Resource resource) {

        this.id = id;
        this.basicInfo = basicInfo;
        this.resource = resource;
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", resource=" + resource +
                '}';
    }
}
