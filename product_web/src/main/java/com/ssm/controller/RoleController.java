package com.ssm.controller;

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
    public String findAll(Model model){
        List<Role> roles = roleService.findAll();
        model.addAttribute("roleList", roles);
        return "role/role-list";
    }

    @RequestMapping("/findById.do")
    public String findById(int id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role/role-show";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd(){
        return "role/role-add";
    }

    @RequestMapping("/saveRole.do")
    @Secured("ROLE_ADMIN")
    public String saveRole(Role role) {
        roleService.saveRole(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @Secured("ROLE_ADMIN")
    public String findRoleByIdAndAllPermission(int id, Model model) {
        List<Permission> permissions = roleService.findOtherPermissions(id);
        model.addAttribute("id", id);
        model.addAttribute("permissionList", permissions);
        return "role/role-permission-add";
    }

    @RequestMapping("/addPermissionToRole.do")
    @Secured("ROLE_ADMIN")
    public String addPermissionToRole(@RequestParam("roleId") String roleId, @RequestParam("ids") String[] ids) {
        roleService.addPermissionToRole(roleId, ids);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/deleteRole.do")
    @Secured("ROLE_ADMIN")
    public String deleteRole(int id) {
        roleService.deleteRole(id);

        return "redirect:/role/findAll.do";
    }
}
