package com.ssm.dao;

import com.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/9 13:58
 * @description: TODO
 */
@Repository("sysLogDao")
public interface ISysLogDao {

    @Insert("insert into sysLog(visitTime, username, ip, url, executionTime, method) values (#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void saveLog(SysLog sysLog);

    @Select("select * from syslog order by visitTime desc")
    List<SysLog> findAll();
}
