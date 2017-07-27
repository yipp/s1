package org.yinet.s1.logic.banker.dto;

import org.yinet.s1.logic.login.dto.LoginDto;

import java.util.List;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/24******
 * 描述：
 */
public class BankerListDto {
    private List<LoginDto> bankers;

    public BankerListDto() {
    }

    public BankerListDto(List<LoginDto> bankers) {

        this.bankers = bankers;
    }

    public List<LoginDto> getBankers() {

        return bankers;
    }

    public void setBankers(List<LoginDto> bankers) {
        this.bankers = bankers;
    }
}
