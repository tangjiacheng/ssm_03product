package com.ssm.service;

import com.ssm.domain.Product;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:47
 * @description: TODO
 */
public interface IProductService {

    public List<Product> findAll(Integer page, Integer size) throws Exception;

    void save(Product product) throws ParseException;

    Product findById(Integer id);

    void updateProduct(Product product);
}
