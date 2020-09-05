package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.Constants;
import com.example.demo.constant.ResponseCode;
import com.example.demo.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.SortedMap;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CommonUtil commonUtil;

    Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    //存放鉴权信息的Header名称，默认是Authorization
    private String Authorization = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============进入拦截器=====================");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            log.info("===============无需拦截=====================");
            return true;
        }

        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            //依次检查各变量是否存在?
            String token = request.getHeader(Authorization);
            Object loginStatus = redisUtil.get(token);
            if (StringUtils.isEmpty(token) || Objects.isNull(loginStatus)) {
                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_TOKEN)));
                return false;
            }
            String timestampStr = request.getHeader("timestamp");
            if (StringUtils.isEmpty(timestampStr)) {
                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_TIMESTAMP)));
                return false;
            }

            //接口幂等性
//            String nonce = request.getHeader("nonce");
//            if (StringUtils.isEmpty(nonce)) {
//                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_NONCE)));
//                return false;
//            }

            long timestamp = 0;
            try {
                timestamp = Long.parseLong(timestampStr);
            } catch (Exception e) {
                log.error("timestamp 发生异常",e);
            }

            //前端传过来的时间戳与服务器当前时间戳差值大于180，则当前请求的timestamp无效
            if (Math.abs(timestamp - commonUtil.create_timestamp()) > Constants.TIMEOUT) {
                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_TIMESTAMP_INVALID)));
                return false;
            }

            //判断redis中的nonce，确认当前请求是否为重复请求，控制API接口幂等性
//            boolean nonceExists = redisUtil.hasKey(Constants.SIGN_KEY+timestampStr+nonce);
//            if (nonceExists) {
//                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_DUPLICATION)));
//                return false;
//            }

            String sign = request.getHeader("sign");

            HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
            //获取全部参数(包括URL和body上的)
            SortedMap<String, String> allParams = HttpUtils.getAllParams(requestWrapper);
            allParams.put("token",token);
            allParams.put("timestamp",timestampStr);
            allParams.put("sign",sign);
            //对参数进行签名验证
            boolean isSigned = SignUtil.verifySign(allParams);
            isSigned = true; //测试

            if (StringUtils.isEmpty(sign)) {
                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_SIGN)));
                return false;
            }

            log.info("获取到的token为: {} ", token);
            log.info("获取到的timestamp为: {} ", timestampStr);
            log.info("获取到的sign为: {} ", sign);

            if (!isSigned) {
                ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_VERIFY_FAIL)));
                return false;
            }
            log.info("===============验证成功=====================");
            return true;
        }
        log.info("===============跳过拦截=====================");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
