package com.cisco.rbac.model.service;

import com.cisco.rbac.model.entity.Permission;
import com.github.pagehelper.PageInfo;

public interface PermissionService {
    boolean insertPermission(Permission permission);

    PageInfo<Permission> queryPermission(Integer page, Integer pageSize);

    boolean deletePermissionById(int id);

    boolean updatePermission(Permission permission);



}
