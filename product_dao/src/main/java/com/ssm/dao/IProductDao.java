package com.ssm.dao;

import com.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:45
 * @description: TODO
 */
@Repository
public interface IProductDao {

    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
}
