package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.ISysLogDao;
import com.ssm.domain.SysLog;
import com.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/9 13:58
 * @description: TODO
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;


    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }
}
