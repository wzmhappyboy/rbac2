package com.cisco.rbac.entity;

public class Role {
    private  Integer id;
    private  String name;
    private  String description;

    public Role(Integer id,String name,String description) {
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Role() {
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
