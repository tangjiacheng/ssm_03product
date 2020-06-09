package com.ssm.domain;


import java.io.Serializable;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/7 19:48
 * @description: 角色实体类
 */
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<UserInfo> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
