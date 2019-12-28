package com.cisco.rbac.service;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;

public interface RoleService {
    boolean insertRole(Role role);

    boolean insertRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean deleteRolerightrelationById(int id);
}
