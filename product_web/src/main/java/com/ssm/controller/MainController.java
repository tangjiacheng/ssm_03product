package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: TJC
 * @Date: 2020/6/8 18:40
 * @description: TODO
 */
@Controller
public class MainController {

    @RequestMapping("/toMain.do")
    public String toMain() {
        return "main";
    }
}
