package com.cisco.rbac.entity;

public class RolePermissionRelation {
    Integer id;
    Integer role_id;
    Integer permissionId;
    Integer permissionType;

    public RolePermissionRelation() {
    }

    public RolePermissionRelation(Integer id, Integer role_id, Integer permissionId, Integer permissionType) {
        this.id = id;
        this.permissionId = permissionId;
        this.role_id=role_id;
        this.permissionType = permissionType;
    }

    @Override
    public String toString() {
        return "Rolerightrelation{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", right_id=" + permissionId +
                ", right_type=" + permissionType +
                '}';
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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
