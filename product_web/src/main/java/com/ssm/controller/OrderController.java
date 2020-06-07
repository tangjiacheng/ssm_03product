package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.service.IOrderService;
import com.ssm.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 20:34
 * @description: TODO
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /*@RequestMapping("/findAll.do")  // 查询全部订单 未分页
    public String findAll(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("ordersList", orders);
        return "orders-list";
    }*/

    @RequestMapping("/findAll.do")  // 查询全部订单 分页
    public String findAll(@RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "4") int size,
                          Model model) {
        List<Order> orders = orderService.findAll(page, size);
        // pageInfo 就是一个分页的bean
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        model.addAttribute("pageInfo", pageInfo);
        return "orders-list";
    }

    @RequestMapping("/findById.do")
    public String findById(@RequestParam(name = "id") int id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("orders", order);
        return "orders-show2";
    }
}
