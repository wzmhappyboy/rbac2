package com.cisco.rbac.mapper;

import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

//    添加用户信息
    int insertUser(User user);

    //根据id删除用户信息
    int deleteUserById(int id);

    //根据用户id查用户信息
    User getUserById(int id);

    //查询所有用户信息
    List<User> queryUser();

    //添加用户角色关系
    int insertUserrolerelation(UserRoleRelation urr);

    //查看用户所有权限的权限号、权限类别
    List<RolePermissionRelation> queryUserrights(int id);
}