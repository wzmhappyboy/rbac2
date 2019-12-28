package com.cisco.rbac.service;

import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.UserRoleRelation;

import java.util.List;

public interface UserService {

    boolean insertUser(User user);

    User getUserById(int id);

    List<User> queryUser();

    boolean deleteUserById(int id);

    boolean insertUserrolerelation(UserRoleRelation urr);

    List<RolePermissionRelation> queryUserrights(int id);
}
