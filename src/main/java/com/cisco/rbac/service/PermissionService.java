package com.cisco.rbac.service;

import com.cisco.rbac.entity.Permission;

import java.util.List;

public interface PermissionService {
    boolean insertRight(Permission permission);

    List<Permission> queryRight();

    boolean deleteRightById(int id);

}
