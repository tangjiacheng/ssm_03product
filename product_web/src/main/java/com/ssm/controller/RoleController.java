package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import com.ssm.service.IPermissionService;
import com.ssm.service.IRoleService;
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
 * @Date: 2020/6/8 16:41
 * @description: TODO
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page, size);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("roleList", roles);
        mv.setViewName("role/role-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role/role-show");
        return mv;
    }

    @RequestMapping("/toAdd.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String toAdd(){
        return "role/role-add";
    }

    @RequestMapping("/saveRole.do")
    @Secured({"ROLE_ADMIN", "ROLE_NORMAL"})
    public String saveRole(Role role) {
        roleService.saveRole(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findRoleByIdAndAllPermission(Integer id) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = roleService.findOtherPermissions(id);
        mv.addObject("id", id);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role/role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    @Secured("ROLE_ADMIN")
    public String addPermissionToRole(@RequestParam("roleId") String roleId, @RequestParam("ids") String[] ids) {
        roleService.addPermissionToRole(roleId, ids);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/deleteRole.do")
    @Secured("ROLE_ADMIN")
    public String deleteRole(Integer id) {
        roleService.deleteRole(id);
        return "redirect:/role/findAll.do";
    }
}
