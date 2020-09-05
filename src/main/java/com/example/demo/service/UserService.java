package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User GetUserById(int id);

    User AddUser(User user);

    User findByName(String name);
}
