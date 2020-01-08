package com.cisco.rbac.entity;

public class RolePermissionRelation {
    Integer id;
    Integer roleId;
    Integer permissionId;
    Integer permissionType;

    public RolePermissionRelation() {
    }

    public RolePermissionRelation(Integer id, Integer role_id, Integer permissionId, Integer permissionType) {
        this.id = id;
        this.permissionId = permissionId;
        this.roleId =role_id;
        this.permissionType = permissionType;
    }

    @Override
    public String toString() {
        return "Rolerightrelation{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", rightId=" + permissionId +
                ", rightType=" + permissionType +
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
