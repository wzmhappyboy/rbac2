package com.cisco.rbac.mapper;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    //添加角色
    int insertRole(Role role);

    //删除角色
    int deleteRoleById(int id);

    //改角色信息
    boolean updateRole(Role role);

    //查看所有角色
    List<Role> queryRole();



    //给角色加权限
    int insertRolerightrelation(RolePermissionRelation rolePermissionRelation);

    //给角色改权限
    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

    //给角色删权限
    int deleteRolerightrelationById(int id);
}
