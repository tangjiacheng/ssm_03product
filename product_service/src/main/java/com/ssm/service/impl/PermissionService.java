package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IPermissionDao;
import com.ssm.domain.Permission;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 17:04
 * @description: TODO
 */
@Service("permissionService")
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        permissionDao.deleteFromRole_PermissionByPID(id);
        permissionDao.deleteById(id);
    }
}
