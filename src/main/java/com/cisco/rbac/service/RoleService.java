package com.cisco.rbac.service;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;

import java.util.List;

public interface RoleService {
    boolean insertRole(Role role);

    boolean deleteRoleById(int id);

    boolean updateRole(Role role);

    List<Role> queryRole();

    List<RolePermissionRelation> showpermissionbyroleid(int id);

    boolean insertRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

    boolean deleteRolerightrelationById(RolePermissionRelation rolePermissionRelation);

    Role getByIdWithResult(int id);
}
