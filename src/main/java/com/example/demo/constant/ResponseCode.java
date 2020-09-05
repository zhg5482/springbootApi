package com.example.demo.constant;

public enum ResponseCode {
    // 系统模块
    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    NO_AUTHORITY(401,"无权限"),
    //sign error
    SIGN_NO_APPID(10001, "appId不能为空"),
    SIGN_NO_TIMESTAMP(10002, "timestamp不能为空"),
    SIGN_NO_SIGN(10003, "sign不能为空"),
    SIGN_NO_NONCE(10004, "nonce不能为空"),
    SIGN_TIMESTAMP_INVALID(10005, "timestamp无效"),
    SIGN_DUPLICATION(10006, "重复的请求"),
    SIGN_VERIFY_FAIL(10007, "sign签名校验失败"),
    SIGN_NO_TOKEN(10008, "token为空或失效"),

    //user模块
    USER_LOGIN_SUCCESS(20001,"登录成功"),
    USER_LOGIN_ERROR(20002,"登录失败"),
    USER_LOGINOUT_SUCCESS(20003,"注销成功"),
    USER_LOGINOUT_ERROR(20004,"注销失败"),

    USER_GET_INFO_SUCCESS(20005,"获取数据成功"),
    USER_GET_INFO_ERROR(20006,"获取数据失败"),

    USER_ADD_SUCCESS(20007,"用户添加成功"),
    USER_ADD_ERROR(20008,"用户添加失败"),
            ;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    private String msg;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
