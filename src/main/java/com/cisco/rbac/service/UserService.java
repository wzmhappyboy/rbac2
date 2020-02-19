package com.cisco.rbac.service;

import com.cisco.rbac.entity.*;

import java.util.List;
import java.util.Set;

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

    Set<String> getPermissionSet(List<Role> roleList);
}
