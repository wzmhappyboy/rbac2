package com.cisco.rbac.service;

import com.cisco.rbac.entity.Rolerightrelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.Userrolerelation;

import java.util.List;

public interface UserService {

    boolean insertUser(User user);

    User getUserById(int id);

    List<User> queryUser();

    boolean deleteUserById(int id);

    boolean insertUserrolerelation(Userrolerelation urr);

    List<Rolerightrelation> queryUserrights(int id);
}
