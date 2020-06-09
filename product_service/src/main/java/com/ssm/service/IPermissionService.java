package com.ssm.service;

import com.ssm.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 17:03
 * @description: TODO
 */
public interface IPermissionService {

    public List<Permission> findAll();

    void savePermission(Permission permission);

    void deleteById(int id);
}
