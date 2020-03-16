package com.cisco.rbac.model.controller;

import com.cisco.rbac.model.entity.User;
import org.apache.shiro.SecurityUtils;

public abstract class AbstractController {
    protected User getUser(){
        return  (User) SecurityUtils.getSubject().getPrincipal();
    }

    protected int getUserId(){
        return  getUser().getId();
    }


}
