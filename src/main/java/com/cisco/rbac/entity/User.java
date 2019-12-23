package com.cisco.rbac.entity;

public class User {

    private Integer id;
    private String password;
    private String name;

    public User(Integer id,String password,String name) {
        this.id = id;
        this.name=name;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
