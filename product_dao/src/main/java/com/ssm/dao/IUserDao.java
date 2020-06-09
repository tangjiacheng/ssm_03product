package com.ssm.dao;

import com.ssm.domain.Role;
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

    @Select("select * from users where id=#{id}")
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
    UserInfo findById(int id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findRestRole(int userId);

    @Insert("insert into users_role(userId, roleId) values (#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);

    @Update("update users set username=#{username}, email=#{email}, PASSWORD=#{password}, phoneNum=#{phoneNum}, status=#{status} where id=#{id};")
    void updateUser(UserInfo user);


    @Delete("delete from users_role where userId=#{id}")
    void deleteUsers_RoleByUserId(Integer id);

    @Delete("delete from users where id=#{id}")
    void deleteUserById(Integer id);
}
