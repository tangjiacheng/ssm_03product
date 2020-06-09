package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderService.findAll(page, size);
        // pageInfo 就是一个分页的bean
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("order/orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Order order = orderService.findById(id);
        mv.addObject("orders", order);
        mv.setViewName("order/orders-show");
        return mv;
    }
}
