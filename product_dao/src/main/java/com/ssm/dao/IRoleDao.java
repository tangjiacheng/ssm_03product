package com.ssm.dao;

import com.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/7 20:48
 * @description: TODO
 */
public interface IRoleDao {

    // 根据用户id查询所有对应角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(int userId);
}
