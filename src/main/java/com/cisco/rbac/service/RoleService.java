package com.cisco.rbac.service;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.Rolerightrelation;

public interface RoleService {
    boolean insertRole(Role role);

    boolean insertRolerightrelation(Rolerightrelation rolerightrelation);

    boolean updateRolerightrelation(Rolerightrelation rolerightrelation);

    boolean deleteRolerightrelationById(int id);
}
