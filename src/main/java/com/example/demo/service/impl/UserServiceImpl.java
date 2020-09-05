package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService  {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonUtil commonUtil;

    @Override
    public User GetUserById(int id) {
        User user = userMapper.GetUserById(id);
        return user;
    }

    @Override
    public User AddUser(User user){
        if (user.getUsername() == null || user.getPassword() == null) {
            return  null;
        }

        if (this.findByName(user.getUsername()) != null) {
            return null;//用户名已使用
        }

        String password = commonUtil.passwordToHash(user.getPassword());
        if (password == null) {
            return null;
        }
        user.setPassword(password);
        Boolean aBoolean = userMapper.AddUser(user);
        if (!aBoolean) { //添加失败
            return null;
        }
        return this.findByName(user.getUsername());
    }

    @Override
    public User findByName(String name) {
        User param = new User();
        param.setUsername(name);
        return userMapper.findOne(param);
    }
}
