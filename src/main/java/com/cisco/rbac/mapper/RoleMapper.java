package com.cisco.rbac.mapper;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.Rolerightrelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    //添加角色
    int insertRole(Role role);

    //给角色加权限
    int insertRolerightrelation(Rolerightrelation rolerightrelation);

    //给角色改权限
    boolean updateRolerightrelation(Rolerightrelation rolerightrelation);

    //给角色删权限
    int deleteRolerightrelationById(int id);
}
