package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Permission;
import com.ssm.service.IPermissionService;
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
 * @Date: 2020/6/8 17:02
 * @description: TODO
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page, size);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("permissionList", permissions);
        mv.setViewName("permission/permission-list");
        return mv;
    }

    @RequestMapping("/toAdd.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String toAdd() {
        return "permission/permission-add";
    }

    @RequestMapping("/savePermission.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String savePermission(Permission permission) {
        permissionService.savePermission(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/deletePermission.do")
    @Secured("ROLE_ADMIN")
    public String deletePermission(Integer id) {
        permissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }
}
