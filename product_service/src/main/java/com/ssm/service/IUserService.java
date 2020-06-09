package com.ssm.service;

import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/7 19:42
 * @description: TODO
 */
public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    void saveUser(UserInfo user);

    UserInfo findById(int id);

    List<Role> findRestRole(int userId);

    void addRoleToUser(int userId, String[] ids);
}
