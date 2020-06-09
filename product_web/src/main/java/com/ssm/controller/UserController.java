package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page, size);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("userList", userList);
        mv.setViewName("user/user-list");
        return mv;
    }

    @RequestMapping("/toAdd.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String toAdd() {
        return "user/user-add";
    }

    @RequestMapping("/save.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String save(UserInfo user) {
        userService.saveUser(user);
        return "redirect:/user/findAll.do";
    }

    // 查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user/user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findUserByIdAndAllRole(Integer id) {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = userService.findRestRole(id);
        mv.addObject("userId", id);
        mv.addObject("roleList", roles);
        mv.setViewName("user/user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    @Secured("ROLE_ADMIN")
    public String addRoleToUser(@RequestParam("userId") Integer userId, @RequestParam("ids") String[] ids){
        userService.addRoleToUser(userId, ids);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/deleteUser.do")
    @Secured("ROLE_ADMIN")
    public String deleteUser(Integer id) {
        userService.deleteUserById(id);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/toUpdate.do")
    public ModelAndView toUpdate(Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user/user-update");
        return mv;
    }

    @RequestMapping("/update.do")
    public String updateUser(UserInfo user) {
        userService.updateUser(user);
        return "redirect:/user/findAll.do";
    }
}
