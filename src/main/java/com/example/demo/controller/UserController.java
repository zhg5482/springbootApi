package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import com.example.demo.handler.LoginRequired;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制类
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController{

    @Autowired
    private UserService userService;

    @LoginRequired
    @GetMapping("getUser/{id}")
    public String GetUser(@PathVariable int id,HttpServletResponse response){
        User user = userService.GetUserById(id);
        if (null == user) {
            return servletUtil.renderString(response, JSON.toJSONString(resultUtil.error(responseCode.USER_GET_INFO_ERROR)));
        }
        return servletUtil.renderString(response, JSON.toJSONString(resultUtil.success(responseCode.USER_GET_INFO_SUCCESS,user)));
    }

    @PostMapping("add")
    public String AddUser(User user, HttpServletResponse response){
        User addUser = userService.AddUser(user);
        if (addUser == null) {
            return servletUtil.renderString(response, JSON.toJSONString(resultUtil.error(responseCode.USER_ADD_ERROR)));
        }
        return servletUtil.renderString(response, JSON.toJSONString(resultUtil.success(responseCode.USER_ADD_SUCCESS,addUser)));
    }
}
