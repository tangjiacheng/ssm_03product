package com.ssm.service.impl;

import com.ssm.dao.IProductDao;
import com.ssm.domain.Product;
import com.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:48
 * @description: TODO
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}
