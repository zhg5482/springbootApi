package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao层
 */
@Repository
public interface UserMapper {

    /**
     * 通过ID获取用户信息
     * @param id 用户编号
     * @return
     */
    User GetUserById(int id);

    /**
     * 通过用户名和密码获取用户
     * @param username 用户名
     * @param password 密码
     * @return user
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    /**
     * 添加用户
     * @param user 用户对象
     * @return true/false
     */
    Boolean AddUser(User user);

    /**
     * 获取用户
     * @param user 用户对象
     * @return userß
     */
    User findOne(User user);
}
