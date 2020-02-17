package com.cisco.rbac.service;

import com.cisco.rbac.entity.Permission;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.UserRoleRelation;

import java.util.List;

public interface UserService {
//    int insert(User user);
//    int delete(int userId);
//    int update(User user);
//    User selectById(int userId);
//    List<User> selectAll();
//    List<Permission> selectAllPermissions(int userId);

    boolean checkUserAndPassword(Integer id,String password);

    boolean insertUser(User user);
    boolean deleteUserById(int id);

    boolean updateUser(User user);

    User getUserById(int id);
    List<User> queryUser();
    User getByIdWithResult(int id);



    boolean insertUserrolerelation(UserRoleRelation urr);

    List<RolePermissionRelation> queryUserrights(int id);

    boolean deleteUserrolerelationById(UserRoleRelation urr);


    User getPerssionById(int id);

    List<Permission> showUserRoles(int id);

}
