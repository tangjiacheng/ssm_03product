package com.ssm.service;

import com.ssm.domain.SysLog;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/9 13:57
 * @description: TODO
 */
public interface ISysLogService {

    public void saveLog(SysLog sysLog);

    List<SysLog> findAll(Integer page, Integer size);
}
