package com.ssm.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:38
 * @description: 产品信息
 */
@Data
public class Product {
    private Integer id; //主键
    private String productNum; //编号 唯一
    private String productName;
    private String cityName;
    private Timestamp departureTime;
    private String departureTimeStr;
    private Float productPrice;
    private String productDesc;
    private Integer productStatus;
    private String productStatusStr;
}
