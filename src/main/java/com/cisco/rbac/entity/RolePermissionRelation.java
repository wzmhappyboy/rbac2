package com.cisco.rbac.entity;

public class RolePermissionRelation {
    Integer id;
    Integer role_id;
    Integer permission_id;
    Integer permission_type;

    public RolePermissionRelation() {
    }

    public RolePermissionRelation(Integer id, Integer role_id, Integer permission_id, Integer permission_type) {
        this.id = id;
        this.permission_id = permission_id;
        this.role_id=role_id;
        this.permission_type = permission_type;
    }

    @Override
    public String toString() {
        return "Rolerightrelation{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", right_id=" + permission_id +
                ", right_type=" + permission_type +
                '}';
    }

    public Integer getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(Integer permission_type) {
        this.permission_type = permission_type;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
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
