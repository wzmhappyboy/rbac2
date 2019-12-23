package com.cisco.rbac.service;

import com.cisco.rbac.entity.Right;

import java.util.List;

public interface RightService {
    boolean insertRight(Right right);

    List<Right> queryRight();

    boolean deleteRightById(int id);

}
