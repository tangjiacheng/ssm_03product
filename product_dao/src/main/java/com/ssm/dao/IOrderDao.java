package com.ssm.dao;

import com.ssm.domain.Member;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 20:36
 * @description: TODO
 */
@Repository("orderDao")
public interface IOrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.ssm.dao.IProductDao.findById"))
    })
    List<Order> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,
                    one = @One(select = "com.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class,
                    many = @Many(select = "com.ssm.dao.ITravellerDao.findByOrderId"))
    })
    Order findById(int id);

    @Select("select * from orders where productId=#{productId}")
    List<Order> findByProductId(Integer productId);

    @Delete("delete from order_traveller where orderId=#{id}")
    void deleteOrder_TravellerByOrderId(Integer id);

    @Delete("delete from orders where id=#{id}")
    void deleteById(Integer id);
}
