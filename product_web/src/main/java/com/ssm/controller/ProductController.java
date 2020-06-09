package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import com.ssm.service.IOrderService;
import com.ssm.service.IProductService;
import com.ssm.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/6 15:16
 * @description: TODO
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new DateStringEditor());
    }

    @RequestMapping("/findAll.do") // 查询所有产品
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page, size);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("productList", products);
        mv.setViewName("product/product-list");
        return mv;
    }

    @RequestMapping("/toAdd.do")
    public String toAdd() {
        return "product/product-add";
    }

    @RequestMapping("save.do")   // 添加产品
    public String saveProduct(Product product) throws ParseException {
        productService.save(product);
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/findOrders.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public ModelAndView findOrders(Integer id) {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        List<Order> orders = orderService.findByProductId(id);
        mv.addObject("product", product);
        mv.addObject("orders", orders);
        mv.setViewName("product/product-orders-show");
        return mv;
    }

    @RequestMapping("/toUpdate.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public ModelAndView toUpdate(Integer id) {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product", product);
        mv.setViewName("product/product-update");
        return mv;
    }

    @RequestMapping("/updateProduct.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/product/findAll.do";
    }

}
