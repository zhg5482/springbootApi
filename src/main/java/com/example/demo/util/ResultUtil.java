package com.example.demo.util;

import com.example.demo.constant.ResponseCode;

import java.io.Serializable;

public class ResultUtil implements Serializable {

    private static final long serialVersionUID = 7498483649536881777L;
    private Integer status;
    private String msg;
    private Object data;

    public ResultUtil() {
    }

    public ResultUtil(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtil success(ResponseCode code, Object data) {
        return new ResultUtil(code.getCode(), code.getMsg(), data);
    }

    public static ResultUtil error(ResponseCode code) {
        return new ResultUtil(code.getCode(), code.getMsg(), null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}