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


    //插入用户
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

    //查询指定用户
    @GetMapping("/users/{id}")
    public  String getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return  user.toString();
    }

    //显示指定用户权限
    @GetMapping("userrights/{id}")
    public  String getUserrights(@PathVariable("id") int id){
        List<RolePermissionRelation> rightsList=userService.queryUserrights(id);
        return  rightsList.toString();
    }

    //列出所有用户
    @GetMapping("users")
    public  String getAllUser(){
        List<User> userList=userService.queryUser();
        return  userList.toString();
    }

    //删除指定用户
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

    //删除用户权限
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
    urr.setRoleId(Integer.valueOf(userMap.get("role_id")));
    urr.setUserId(Integer.valueOf(userMap.get("user_id")));
    boolean result =userService.insertUserrolerelation(urr);
    if (result){
        return  "success";
    }
    else{
        return  "fail";
    }
}

//更新用户信息
    @PostMapping("/newusers")
    public  String updateUser(@RequestBody Map rrrMap){
        User user=new User();
        user.setName((String) rrrMap.get("name"));
        int userId=Integer.valueOf((String) rrrMap.get("id"));
        user.setId(Integer.valueOf((String) rrrMap.get("id")));
        user.setPassword((String) rrrMap.get("password"));
        userService.deleteUserrolerelationById(Integer.valueOf((String) rrrMap.get("id")));
      //  roleidlist.forEach();
        List<Integer> roleidlist =(List<Integer>) rrrMap.get("rolelist");
        for (int i=0;i<roleidlist.size();i++){
            int  roleId=roleidlist.get(i);
            UserRoleRelation urr=new UserRoleRelation();
            urr.setUserId(userId);
            urr.setRoleId(roleId);
            userService.insertUserrolerelation(urr);
        }

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

    //返回List<权限>
//    @GetMapping("/permissionsbyid/{id}")
//    public  String getPerssionById(@PathVariable("id") int id){
//        User user=userService.getPerssionById(id);
//        System.out.println(user);
//        List<Permission> permissions=user.getPermissions();
//        permissions.forEach(n->System.out.println(n));
//        return  user.toString();
//    }
}
