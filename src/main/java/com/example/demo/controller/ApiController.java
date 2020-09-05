package com.example.demo.controller;

import com.example.demo.constant.ResponseCode;
import com.example.demo.util.ResultUtil;
import com.example.demo.util.ServletUtil;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public class ApiController {

    public ServletUtil servletUtil;

    public ResultUtil resultUtil;

    public ResponseCode responseCode;
}
