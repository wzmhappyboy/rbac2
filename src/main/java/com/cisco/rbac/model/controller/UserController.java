package com.cisco.rbac.model.controller;


import com.cisco.rbac.common.jwt.JwtUtils;
import com.cisco.rbac.common.util.PermissionConstants;
import com.cisco.rbac.common.annotation.JwtIgnore;
import com.cisco.rbac.common.annotation.RequiredPermission;
import com.cisco.rbac.model.entity.*;
import com.cisco.rbac.model.service.impl.UserServiceImpl;
import com.cisco.rbac.common.jwt.JwtParam;
import com.github.pagehelper.PageInfo;
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
    @RequestMapping("/userrights")
    public  Map<String,Object> getUserrights(@RequestParam("id") String id){
        int id2=Integer.parseInt(id);
        List<RolePermissionRelation> rightsList=userService.queryUserrights(id2);
            Map<String,Object> result=new HashMap<>();
            result.put("rightlist",rightsList);
            return result;
    }



//    返还用户拥有权限
@ResponseBody
@RequestMapping(value = "/uroles")
@RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
public  Map<String,Object> getUserroles(@RequestParam("id") String id){
        System.out.println("id:"+id);
        int id2=Integer.parseInt(id);
        List<Permission> list=userService.showUserRoles(id2);
        System.out.println(list);
        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i).getList());
        }
        Map<String,Object> result=new HashMap<>();
    result.put("rightlist",list);
    return result;
}
    //列出所有用户
    @ResponseBody
    @RequestMapping("/showusers")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
    public  Map<String,Object> getAllUser(@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "pageSize",defaultValue = "3")String pageSize){
        Map<String,Object> result=new HashMap<>();
        PageInfo<User> pageInfo=userService.queryUser(page,Integer.parseInt(pageSize));

        //        //获得当前页
        result.put("pageNum",pageInfo.getPageNum());
//        //获得一页显示的条数
        System.out.println("显示第："+page+"页");
        result.put("pageSize",pageInfo.getPageSize());
//        //是否是第一页
        result.put("isFirstPage",pageInfo.isIsFirstPage());
        //       System.out.println("isFirstPage:"+pageInfo.isIsFirstPage());
//        //获得总页数
        result.put("totalPages",pageInfo.getPages());
//        //是否是最后一页
        result.put("isLastPage",pageInfo.isIsLastPage());
//System.out.println("isLastPage:"+pageInfo.isIsLastPage());
        result.put("userlist",pageInfo.getList());
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
    @ResponseBody
    @RequestMapping("/removeurr")
    public  Map<String,Object> deleteUserrolerelaitonById(@RequestParam("user_id") String user_id,@RequestParam("role_id") String role_id){
        UserRoleRelation urr=new UserRoleRelation();
        urr.setRoleId(Integer.parseInt(role_id));
        urr.setUserId(Integer.parseInt(user_id));
        boolean result=userService.deleteUserrolerelationById(urr);
        Map<String,Object> r=new HashMap<>();
        if (result)
        {
            r.put("s","1");
        }
        else{
            r.put("s","2");
        }
        return r;
    }

//    用户添加角色
    @ResponseBody
@RequestMapping("/userrolerelations")

public  Map<String,Object> insertUserrolerelation(@RequestParam("user_id") String user_id,@RequestParam("role_id") String role_id){
    UserRoleRelation urr=new UserRoleRelation();
//    int id=Integer.parseInt(userMap.get("id"));
//    user.setId(id);
//    urr.setId(Integer.valueOf(userMap.get("id")));
    urr.setRoleId(Integer.valueOf(role_id));
    urr.setUserId(Integer.valueOf(user_id));
    Map<String,Object> r=new HashMap<>();
    boolean result =userService.insertUserrolerelation(urr);
    if (result){
        r.put("s","1");
    }
    else{
        r.put("s","2");
    }
    return  r;
}

//更新用户信息
    @PostMapping("/newusers")
    public  String updateUser(@RequestBody Map rrrMap){
        User user=new User();
        user.setName((String) rrrMap.get("name"));
        int userId=Integer.valueOf((String) rrrMap.get("id"));
        user.setId(Integer.valueOf((String) rrrMap.get("id")));
        user.setPassword((String) rrrMap.get("password"));
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

    //返还用户拥有角色
    @ResponseBody
    @RequestMapping("/getrolesbyuserid")
    @RequiredPermission(PermissionConstants.USER_PERMISSION)
    public  Map<String,Object> getUserByIdWithResult(@RequestParam("id") String id){
        Map<String,Object> result=new HashMap<>();
        int ad=Integer.parseInt(id);
        User user=userService.getByIdWithResult(ad);
        List<Role> roleList=user.getRoles();
        int l=roleList.size();
        result.put("list",roleList);
        return  result;
    }


//用户进入注册界面
    @GetMapping("/logon")
    @JwtIgnore
    public ModelAndView login(ModelAndView model){
        model.setViewName("logon");
//      System.out.println(userList.get(0).toString());
        return model;
    }


}
