package com.example.demo.constant;

public class Constants {
    //APP_SECRET
    public static final String SIGN_KEY = "apisign_";
    public static final String APP_SECRET = "30c722c6acc64306a88dd93a814c9f0a";
    public static final String APP_API_VERSION = "1.0";
    public static final String SALT = "springboot_demo1";

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String FIALL = "FIALL";
    /**********************对象和个体****************************/
    public static final String SESSION_USER = "loginedAgent"; // 用户对象
    public static final String SESSION_LOGINID = "sessionLoginID"; // 登录ID
    public static final String SESSION_USERID = "sessionUserID"; // 当前用户对象ID编号

    public static final String SESSION_USERNAME = "sessionUserName"; // 当前用户对象ID编号
    public static final Integer PAGE = 10; // 默认分页数
    public static final String SESSION_URL = "sessionUrl"; // 被记录的url
    public static final String SESSION_SECURITY_CODE = "sessionVerifyCode"; // 登录页验证码
    // 时间 缓存时间
    public static final int TIMEOUT = 1800;// 秒
    public static final int TOKEN_TIMEOUT = 600;// 秒

    public static final String ON_LOGIN = "/logout.htm";
    public static final String LOGIN_OUT = "/toLogout";
    // 不验证URL anon：不验证/authc：受控制的
    public static final String NO_INTERCEPTOR_PATH =".*/((.css)|(.js)|(images)|(login)|(anon)).*";
}
