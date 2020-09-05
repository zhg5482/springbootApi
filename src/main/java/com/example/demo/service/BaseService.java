package com.example.demo.service;

import com.example.demo.util.CommonUtil;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class BaseService {

    @Resource
    public RedisUtil redisUtil;

    @Autowired
    public CommonUtil commonUtil;
}
