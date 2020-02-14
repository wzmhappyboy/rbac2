package com.cisco.rbac.controller;


import com.cisco.rbac.JwtIgnore;
import com.cisco.rbac.entity.*;
import com.cisco.rbac.service.impl.UserServiceImpl;
import com.cisco.rbac.util.JwtParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private JwtParam jwtParam;
    //插入用户
    @ResponseBody
    @RequestMapping(value = "/ns")
    @JwtIgnore
    public  Map<String,Object> insertUser(@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("password2") String password2){
        System.out.println("进入后台");
        User user=new User();
        String r="0";
//        int id=Integer.parseInt(userMap.get("id"));
//        user.setId(id);

        user.setName(name);
        user.setPassword(password);
        boolean d=true;
        boolean result=false;
        if(!password.equals(password2)){
            d=false;
            r="3";
        }
        if (d) {
             result = userService.insertUser(user);
            if (result) {
                r = "1";
            } else {
                r = "2";
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("result",r);
        return map;
    }

    //查询指定用户
    @GetMapping("/users/{id}")
    public  String getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return  user.toString();
    }

    //显示指定用户权限
    @ResponseBody
    @RequestMapping("/userrights/")
    public  Map<String,Object> getUserrights(@RequestParam("id") String id){
        System.out.println("显示用户权限的用户id:"+id);
        int id2=Integer.parseInt(id);
        List<RolePermissionRelation> rightsList=userService.queryUserrights(id2);
            Map<String,Object> result=new HashMap<>();
            result.put("rightlist",rightsList);
            return result;
    }

@ResponseBody
@RequestMapping(value = "/uroles")

public  Map<String,Object> getUserroles(@RequestParam("id") String id){
        System.out.println("id:"+id);
        int id2=Integer.parseInt(id);
        List<Permission> list=userService.showUserRoles(id2);
        System.out.println(list);
    Map<String,Object> result=new HashMap<>();
    result.put("rightlist",list);
    return result;
}
    //列出所有用户
    @ResponseBody
    @RequestMapping("/showusers")
    public  Map<String,Object> getAllUser(){
        Map<String,Object> result=new HashMap<>();
        List<User> userList=userService.queryUser();
        result.put("userlist",userList);
        return  result;
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
@JwtIgnore
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

    @GetMapping("/")
    @JwtIgnore
    public ModelAndView index(ModelAndView model){
        List<User> userList=userService.queryUser();
        model.addObject("users",userList);
        model.setViewName("index");
//      System.out.println(userList.get(0).toString());
        return model;
    }


    @GetMapping("/logon")
    @JwtIgnore
    public ModelAndView login(ModelAndView model){
        model.setViewName("logon");
//      System.out.println(userList.get(0).toString());
        return model;
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
