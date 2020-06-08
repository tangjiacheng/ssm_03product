package com.ssm.dao;

import com.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/7 19:47
 * @description: TODO
 */
public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.ssm.dao.IRoleDao.findRoleByUserId", fetchType = FetchType.LAZY))
    })
    public UserInfo findByUserName(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(username, password, email, phoneNum, status) values(#{username}, #{password}, #{email}, #{phoneNum}, #{status})")
    void saveUser(UserInfo user);
}
