package com.cisco.rbac.entity;

public class Rolerightrelation {
    Integer id;
    Integer role_id;
    Integer right_id;
    Integer right_type;

    public Rolerightrelation() {
    }

    public Rolerightrelation(Integer id, Integer role_id, Integer right_id, Integer right_type) {
        this.id = id;
        this.right_id=right_id;
        this.role_id=role_id;
        this.right_type=right_type;
    }

    @Override
    public String toString() {
        return "Rolerightrelation{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", right_id=" + right_id +
                ", right_type=" + right_type +
                '}';
    }

    public Integer getRight_type() {
        return right_type;
    }

    public void setRight_type(Integer right_type) {
        this.right_type = right_type;
    }

    public Integer getRight_id() {
        return right_id;
    }

    public void setRight_id(Integer right_id) {
        this.right_id = right_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
