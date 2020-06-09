package com.ssm.service;

import com.ssm.domain.Order;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 20:35
 * @description: TODO
 */
public interface IOrderService {

    List<Order> findAll(int page, int size);

    Order findById(int id);

    List<Order> findByProductId(Integer productId);

    void deleteById(Integer id);
}
