package org.yinet.s1.logic.banker.dto;

import org.yinet.s1.logic.login.dto.LoginDto;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
public class BankerDto {
    private long jackpot;
    private LoginDto loginDto;

    public BankerDto() {
    }

    public BankerDto(long jackpot, LoginDto loginDto) {
        this.jackpot = jackpot;
        this.loginDto = loginDto;
    }

    public long getJackpot() {

        return jackpot;
    }

    public void setJackpot(long jackpot) {
        this.jackpot = jackpot;
    }

    public LoginDto getLoginDto() {
        return loginDto;
    }

    public void setLoginDto(LoginDto loginDto) {
        this.loginDto = loginDto;
    }
}
