package com.ssm.controller;

import com.ssm.domain.Product;
import com.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/findAll.do")
    public String findAll(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        model.addAttribute("", products);
        return "";
    }
}
