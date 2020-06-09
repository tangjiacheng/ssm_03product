package com.ssm.domain;

import com.ssm.utils.DateUtils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: TJC
 * @Date: 2020/6/9 12:59
 * @description: TODO
 */
public class SysLog implements Serializable {
    private Integer id;
    private Timestamp visitTime;
    private String visitTimeStr;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVisitTimeStr() {
        if (visitTime != null) {
            visitTimeStr = DateUtils.date2String(visitTime, "yyyy-MM-dd HH:mm:ss");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }
}
