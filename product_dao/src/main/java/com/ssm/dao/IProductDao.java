package com.ssm.dao;

import com.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 14:45
 * @description: TODO
 */
public interface IProductDao {

    @Select("select * from product order by departureTime asc")  //查询所有产品信息
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus) " +
            "values(#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
    void save(Product product);

    @Select("select * from product where id=#{id}")  // 根据id查询产品
    public Product findById(Integer id);
}
