package com.ssm.service;

import com.ssm.domain.Product;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:47
 * @description: TODO
 */
public interface IProductService {

    public List<Product> findAll() throws Exception;
}
