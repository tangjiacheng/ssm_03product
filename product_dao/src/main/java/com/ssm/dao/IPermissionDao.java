package com.ssm.dao;

import com.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 16:04
 * @description: TODO
 */
public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(int roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName, url) values (#{permissionName}, #{url})")
    void savePermission(Permission permission);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_PermissionByPID(int id);

    @Delete("delete from permission where id=#{id}")
    void deleteById(int id);
}
