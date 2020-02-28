package com.cisco.rbac.service;

import com.cisco.rbac.entity.Permission;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionService {
    boolean insertPermission(Permission permission);

    PageInfo<Permission> queryPermission(Integer page, Integer pageSize);

    boolean deletePermissionById(int id);

    boolean updatePermission(Permission permission);



}
