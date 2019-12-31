package com.cisco.rbac.controller;


import com.cisco.rbac.entity.*;
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

    @DeleteMapping("/userrolerelations/{id}")
    public  String deleteUserrolerelaitonById(@PathVariable("id") int id){
        boolean result=userService.deleteUserrolerelationById(id);
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

    @PostMapping("/newusers")
    public  String updateUser(@RequestBody Map<String,String> rrrMap){
        User user=new User();
        user.setName(rrrMap.get("name"));
        user.setId(Integer.valueOf(rrrMap.get("id")));
        user.setPassword(rrrMap.get("password"));
        boolean result =userService.updateUser(user);
        if (result){
            return  "success";
        }
        else {
            return  "fail";
        }
    }

    //返还类内的list<角色>
    @GetMapping("/usersbyid/{id}")
    public  String getUserByIdWithResult(@PathVariable("id") int id){
        User user=userService.getByIdWithResult(id);
        System.out.println(user);
        List<Role> roleList=user.getRoles();
        roleList.forEach(n->System.out.println(n));
        return  user.toString();
    }
    @GetMapping("/permissionsbyid/{id}")
    public  String getPerssionById(@PathVariable("id") int id){
        User user=userService.getPerssionById(id);
        System.out.println(user);
        List<Permission> permissions=user.getPermissions();
        permissions.forEach(n->System.out.println(n));
        return  user.toString();
    }
}
