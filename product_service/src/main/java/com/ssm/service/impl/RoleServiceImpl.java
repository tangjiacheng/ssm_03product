package com.ssm.service.impl;

import com.ssm.dao.IRoleDao;
import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 16:43
 * @description: TODO
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(int id) {
        return roleDao.findOtherPermissions(id);
    }

    @Override
    @Transactional
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(Integer.parseInt(roleId), Integer.parseInt(permissionId));
        }
    }

    @Override
    @Transactional
    public void deleteRole(int roleId) {
        roleDao.deleteRole_PermissionByRoleId(roleId);
        roleDao.deleteUser_RoleByRoleId(roleId);
        roleDao.deleteRoleById(roleId);
    }
/*
    @Override
    public void deleteRole_PermissionByRoleId(int id) {
        roleDao.deleteRole_PermissionByRoleId(id);
    }

    @Override
    public void deleteUser_RoleByRoleId(int id) {
        roleDao.deleteUser_RoleByRoleId(id);
    }

    @Override
    public void deleteRoleById(int id) {
        roleDao.deleteRoleById(id);
    }*/
}
