package com.cisco.rbac.model.mapper;

import com.cisco.rbac.model.entity.Role;
import com.cisco.rbac.model.entity.RolePermissionRelation;
import com.cisco.rbac.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int insertRole(Role role);

    int deleteRoleById(int id);

    boolean updateRole(Role role);
    int insertRolerightrelation(RolePermissionRelation rolePermissionRelation);
    int deleteRolerightrelationById(RolePermissionRelation rolePermissionRelation);


    Role getByIdWithSelect(int id);
    List<Role> queryRole();
    List<User> findUsersByUserId(int id);

List<RolePermissionRelation> showpermissionbyroleid(int id);

    boolean updateRolerightrelation(RolePermissionRelation rolePermissionRelation);

}
