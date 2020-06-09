package com.ssm.controller;

import com.ssm.domain.Permission;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String findAll(Model model) {
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("permissionList", permissions);
        return "permission/permission-list";
    }

    @RequestMapping("/toAdd.do")
    @Secured("ROLE_ADMIN")
    public String toAdd() {
        return "permission/permission-add";
    }

    @RequestMapping("/savePermission.do")
    @Secured("ROLE_ADMIN")
    public String savePermission(Permission permission) {
        permissionService.savePermission(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/deletePermission.do")
    @Secured("ROLE_ADMIN")
    public String deletePermission(int id) {
        permissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }
}
