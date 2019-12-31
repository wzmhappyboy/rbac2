package com.cisco.rbac.entity;

public class Permission {
private  Integer id;
private  String name;
private  String description;

    public Permission() {
    }

    public Permission(Integer id, String name, String description) {
        this.id = id;
        this.name=name;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}