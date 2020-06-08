package com.ssm.controller;

import com.ssm.domain.UserInfo;
import com.ssm.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/8 14:54
 * @description: TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model) {
        List<UserInfo> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user-list";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd() {
        return "user-add";
    }

    @RequestMapping("/save.do")
    public String save(UserInfo user) {
        userService.saveUser(user);
        return "redirect:/user/findAll.do";
    }
}
