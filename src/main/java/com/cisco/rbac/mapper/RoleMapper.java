package com.cisco.rbac.mapper;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int insertRole(Role role);

    int deleteRoleById(int id);

    boolean updateRole(Role role);
    int insertRolerightrelation(RolePermissionRelation rolePermissionRelation);
    int deleteRolerightrelationById(int id);

    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

    Role getByIdWithSelect(int id);

    List<Role> queryRole();
    List<User> findUsersByUserId(int id);
}
