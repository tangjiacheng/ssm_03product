package com.ssm.controller;

import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "user/user-list";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd() {
        return "user/user-add";
    }

    @RequestMapping("/save.do")
    public String save(UserInfo user) {
        userService.saveUser(user);
        return "redirect:/user/findAll.do";
    }

    // 查询指定id的用户
    @RequestMapping("/findById.do")
    public String findById(int id, Model model) {
        UserInfo user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/user-show";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    @Secured("ROLE_ADMIN")
    public String findUserByIdAndAllRole(int id, Model model) {
        List<Role> roles = userService.findRestRole(id);
        model.addAttribute("userId", id);
        model.addAttribute("roleList", roles);
        return "user/user-role-add";
    }

    @RequestMapping("/addRoleToUser.do")
    @Secured("ROLE_ADMIN")
    public String addRoleToUser(@RequestParam("userId") int userId, @RequestParam("ids") String[] ids){
        userService.addRoleToUser(userId, ids);
        return "redirect:/user/findAll.do";
    }
}
