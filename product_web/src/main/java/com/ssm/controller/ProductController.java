package com.ssm.controller;

import com.ssm.domain.Product;
import com.ssm.service.IProductService;
import com.ssm.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new DateStringEditor());
    }

    @RequestMapping("/findAll.do") // 查询所有产品
    public String findAll(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        model.addAttribute("productList", products);
        return "product-list";
    }

    @RequestMapping("/toMain.do")
    public String toMain() {
        return "main";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd() {
        return "product-add";
    }

    @RequestMapping("save.do")   // 添加产品
    public String saveProduct(Product product) throws ParseException {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
