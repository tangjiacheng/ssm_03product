package com.ssm.controller;

import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: TJC
 * @Date: 2020/6/8 18:40
 * @description: TODO
 */
@Controller
public class GlobalController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/toMain.do")
    public String toMain() {
        return "main";
    }

    @RequestMapping("/toChangePassword.do")
    public ModelAndView toChangePassoword() {
        ModelAndView mv = new ModelAndView();
        SecurityContext context = SecurityContextHolder.getContext(); // 从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        mv.addObject("username", username);
        mv.setViewName("changePassword");
        return mv;
    }

    @RequestMapping("/changePassword.do")
    public ModelAndView changePassword(String username, String password1, String password2) {
        ModelAndView mv = new ModelAndView();
        if (!password1.equals(password2)) {
            mv.addObject("msg", "两次密码不一致");
            mv.setViewName("changePassword");
        }else {
            userService.changePassword(username, password1);
            mv.setViewName("redirect:/toMain.do");
        }
        return mv;
    }
}
