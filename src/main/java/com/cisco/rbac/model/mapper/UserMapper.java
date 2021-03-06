package com.cisco.rbac.model.mapper;

import com.cisco.rbac.model.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

//    int insert(User user);
//
//    int delete(int userId);
//
//    int update(User user);
//    int deleteUserRoles(int userId);
//    int addUserRoles(UserRoleRelation userRoleRelation);
//
//    User selectById(int userId);
//    List<User> selectAll();
//    List<Permission> selectAllPermissions(int userId);
    int insertUser(User user);

    int deleteUserById(int id);

    boolean    updateUser(User user);
    int insertUserrolerelation(UserRoleRelation urr);
    int deleteUserrolerelation(UserRoleRelation urr);

    int clearUserRoles(int id);

    User getUserById(int id);
    List<User> queryUser();
    List<Permission> findPermissionByUserId(int id);
List<Permission> selectChildrenPermissionById(int id);

    User getPerssionById(int id);

    List<RolePermissionRelation> queryUserrights(int id);

    User getByIdWithSelect(int id);

    List<Role> findRoleByUserId(int id);

    User login(User user);
}