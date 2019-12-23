package com.cisco.rbac.controller;


import com.cisco.rbac.entity.Rolerightrelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.Userrolerelation;
import com.cisco.rbac.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user")
    public  String insertUser(@RequestBody Map<String,String> userMap){
        User user=new User();
        int id=Integer.parseInt(userMap.get("id"));
        user.setId(id);
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

    @GetMapping("/user/{id}")
    public  String getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return  user.toString();
    }

    @GetMapping("userrights/{id}")
    public  String getUserrights(@PathVariable("id") int id){
        List<Rolerightrelation> rightsList=userService.queryUserrights(id);
        Rolerightrelation rolerightrelation=rightsList.get(0);
      //  System.out.println(rolerightrelation.toString());
        return  rightsList.toString();
    }

    @GetMapping("user")
    public  String getAllUser(){
        List<User> userList=userService.queryUser();
        return  userList.toString();
    }

    @DeleteMapping("/user/{id}")
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
@PostMapping("/userrolerelation")
public  String insertUserrolerelation(@RequestBody Map<String,String> userMap){
    Userrolerelation urr=new Userrolerelation();
//    int id=Integer.parseInt(userMap.get("id"));
//    user.setId(id);
    urr.setId(Integer.valueOf(userMap.get("id")));
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
