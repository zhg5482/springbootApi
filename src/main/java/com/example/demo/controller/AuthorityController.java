package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.ResponseCode;
import com.example.demo.handler.LoginRequired;
import com.example.demo.service.AuthorityService;
import com.example.demo.util.ResultUtil;
import com.example.demo.util.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(description = "接口认证")
@RestController
@RequestMapping(value = "/auth")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "获取登录认证信息",notes = "通过用户名/密码获取认证信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名",  required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",  required = true, dataType = "String")
    })
    @GetMapping(value = "/login")
    public String Login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        String ua = request.getHeader("User-Agent");
        JSONObject obj = (JSONObject) authorityService.Login(username,password,ua);
        if (obj.isEmpty()) {
            return ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.USER_LOGIN_ERROR)));
        }
        return ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.success(ResponseCode.USER_LOGIN_SUCCESS,obj)));
    }

    @ApiOperation(value = "注销登录",notes = "通过token认证信息")
    @LoginRequired
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String LogOut(HttpServletRequest request,HttpServletResponse response) {
        Boolean status = authorityService.LogOut(request);
        if (!status) {
            return ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.USER_LOGINOUT_ERROR)));
        }
        return ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.success(ResponseCode.USER_LOGINOUT_SUCCESS,null)));
    }
}
