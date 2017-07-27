package org.yinet.s1.error.coder;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class AppErrorCodes {
    /**注册*/
    public static final String ACCOUNT_OCCUPATION = "账号已被占用，请重新注册";
    public static final String REGISTER_SUCCEED = "注册成功，正在跳转登录界面登陆";
    /**登陆*/
    public static final String NOT_ACCOUNT = "账号不存在，请先注册再登陆";
    public static final String PASSWORD_MISTAKE = "密码错误";

    /**下庄成功*/
    public static final String BANKER_DOWN = "下庄成功";
    public static final String INSERT_BANKER_LIST = "已添加到上庄列表";
    public static final String YOU_ARE_BANKER = "你已经是庄家";
}
