package com.cisco.rbac.entity;

import org.springframework.data.relational.core.sql.In;

public class Userrolerelation {
    Integer id;
    Integer user_id;
    Integer role_id;

    public Userrolerelation() {
    }

    public Userrolerelation(Integer id, Integer role_id, Integer user_id) {
        this.id = id;
        this.role_id=role_id;
        this.user_id=user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
