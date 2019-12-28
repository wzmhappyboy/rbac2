package com.cisco.rbac.service;

import com.cisco.rbac.entity.Permission;

import java.util.List;

public interface PermissionService {
    boolean insertPermission(Permission permission);

    List<Permission> queryPermission();

    boolean deletePermissionById(int id);

    boolean updatePermission(Permission permission);



}
