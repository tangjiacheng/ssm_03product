package com.ssm.dao;

import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/7 20:48
 * @description: TODO
 */
public interface IRoleDao {

    @Select("select * from role")
    List<Role> findAll();

    // 根据用户id查询所有对应角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.ssm.dao.IPermissionDao.findByRoleId", fetchType = FetchType.LAZY)),
    })
    List<Role> findRoleByUserId(int userId);

    @Insert("insert into role(roleName, roleDesc) values (#{roleName}, #{roleDesc})")
    void saveRole(Role role);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.ssm.dao.IPermissionDao.findByRoleId", fetchType = FetchType.LAZY)),
    })
    Role findById(int id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermissions(int id);

    // 传个参数时, 需要使用@Param注解
    @Insert("insert into role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteRole_PermissionByRoleId(int id);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteUser_RoleByRoleId(int id);

    @Delete("delete from role where id=#{id}")
    void deleteRoleById(int id);
}
