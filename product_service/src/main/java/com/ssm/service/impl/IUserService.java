package com.ssm.service.impl;

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
}
