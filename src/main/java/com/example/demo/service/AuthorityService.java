package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.Constants;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthorityService extends BaseService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Object Login(String username, String password,String userAgent) {
        User user = userMapper.getUser(username,password);
        JSONObject obj = new JSONObject();
        if (null != user && user.getPassword().equals(commonUtil.passwordToHash(password))) {
            String token = commonUtil.generateToken(userAgent, username);
            Boolean save = redisUtil.set(token,JSONObject.toJSONString(user), Constants.TOKEN_TIMEOUT);
            if (save) {
                obj.put("token",token); //保存redis
                obj.put("nonce",commonUtil.create_nonce_str()); //保存redis
                obj.put("expire_time", Constants.TOKEN_TIMEOUT);
            }
        }
        return obj;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    public Boolean LogOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (redisUtil.hasKey(token)) {
            Boolean delete = redisUtil.del(token);
            if (!delete) {
                return false;
            }
            return true;
        }
       return true;
    }
}
