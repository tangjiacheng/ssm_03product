package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IOrderDao;
import com.ssm.domain.Order;
import com.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 20:35
 * @description: TODO
 */
@Service("orderService")
@Transactional
class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Order> findAll(int page, int size) {
        // pageNum表示起始页码值, pageSize表示每页显示条数
        PageHelper.startPage(page, size);
        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }
}
