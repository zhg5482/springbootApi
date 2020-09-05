package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User GetUserById(int id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    Boolean AddUser(User user);

    User findOne(User user);
}
