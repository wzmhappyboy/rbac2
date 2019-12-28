package com.cisco.rbac.service;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;

import java.util.List;

public interface RoleService {
    boolean insertRole(Role role);

    boolean deleteRoleById(int id);

    boolean updateRole(Role role);

    List<Role> queryRole();

    boolean insertRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean deleteRolerightrelationById(int id);
}
