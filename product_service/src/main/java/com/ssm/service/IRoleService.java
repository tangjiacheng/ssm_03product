package com.ssm.service;

import com.ssm.domain.Permission;
import com.ssm.domain.Role;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 16:42
 * @description: TODO
 */
public interface IRoleService {

    public List<Role> findAll();

    void saveRole(Role role);

    Role findById(int id);

    List<Permission> findOtherPermissions(int id);

    void addPermissionToRole(String roleId, String[] ids);

    void deleteRole(int roleId);

    /*void deleteRole_PermissionByRoleId(int id);

    void deleteUser_RoleByRoleId(int id);

    void deleteRoleById(int id);*/
}
