package com.cisco.rbac.controller;


import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.UserRoleRelation;
import com.cisco.rbac.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/users")
    public  String insertUser(@RequestBody Map<String,String> userMap){
        User user=new User();
//        int id=Integer.parseInt(userMap.get("id"));
//        user.setId(id);
        user.setName(userMap.get("name"));
        user.setPassword(userMap.get("password"));
        boolean result =userService.insertUser(user);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }

    @GetMapping("/users/{id}")
    public  String getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return  user.toString();
    }

    @GetMapping("userrights/{id}")
    public  String getUserrights(@PathVariable("id") int id){
        List<RolePermissionRelation> rightsList=userService.queryUserrights(id);
        return  rightsList.toString();
    }

    @GetMapping("users")
    public  String getAllUser(){
        List<User> userList=userService.queryUser();
        return  userList.toString();
    }

    @DeleteMapping("/users/{id}")
    public  String deleteUserById(@PathVariable("id") int id){
        boolean result=userService.deleteUserById(id);
        if (result)
        {
            return  "success";
        }
        else{
            return  "fail";
        }
    }


//    用户添加角色
@PostMapping("/userrolerelations")
public  String insertUserrolerelation(@RequestBody Map<String,String> userMap){
    UserRoleRelation urr=new UserRoleRelation();
//    int id=Integer.parseInt(userMap.get("id"));
//    user.setId(id);
//    urr.setId(Integer.valueOf(userMap.get("id")));
    urr.setRole_id(Integer.valueOf(userMap.get("role_id")));
    urr.setUser_id(Integer.valueOf(userMap.get("user_id")));
    boolean result =userService.insertUserrolerelation(urr);
    if (result){
        return  "success";
    }
    else{
        return  "fail";
    }
}
}
