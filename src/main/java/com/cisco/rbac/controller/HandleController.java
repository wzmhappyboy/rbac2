package com.cisco.rbac.controller;


import com.cisco.rbac.annotation.JwtIgnore;
import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.RoleServiceImpl;
import com.cisco.rbac.service.impl.UserServiceImpl;
import com.cisco.rbac.jwt.JwtParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HandleController {
    @Autowired
    private JwtParam jwtParam;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;


//    删除用户
    @ResponseBody
    @RequestMapping("/deletusers")
    public Map<String,Object> deleteUserById(@RequestParam("id") String id){
        int ad=Integer.parseInt(id);
System.out.println("排进来要删除的id："+id);
        boolean result=userService.deleteUserById(ad);
        Map<String,Object> r=new HashMap<>();
        if (result)
        {
            r.put("success","1");
        }
        else{
            r.put("success","0");
        }

        return r;
    }


//    进入管理用户界面
    @RequestMapping("/manageur/{id}/{a}")
    @JwtIgnore
    public ModelAndView manageur(@PathVariable("id") String id, @PathVariable("a") String a )
    {
        int ad=Integer.parseInt(id);
        User user=userService.getByIdWithResult(ad);
        List<Role> roleList=user.getRoles();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("manageuserrolerelation");
        modelAndView.addObject("id",id);
        modelAndView.addObject("a",a);
        modelAndView.addObject("list",roleList);
        return modelAndView;
    }

//    进入管理角色界面
    @RequestMapping("/managerp/{id}/{a}")
    @JwtIgnore
    public ModelAndView managerp(@PathVariable("id") String id, @PathVariable("a") String a )
    {
        int ad=Integer.parseInt(id);

List<RolePermissionRelation> list=roleService.showpermissionbyroleid(ad);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("managerpr");
        modelAndView.addObject("id",id);
        modelAndView.addObject("a",a);
        modelAndView.addObject("list",list);
        return modelAndView;
    }

//    进入添加角色界面
    @RequestMapping("/addrole/{a}")
    @JwtIgnore
    public ModelAndView addrole( @PathVariable("a") String a )
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addrole");
        modelAndView.addObject("a",a);
        return modelAndView;
    }

    @RequestMapping("/addp/{a}")
    @JwtIgnore
    public ModelAndView addp( @PathVariable("a") String a )
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addpermission");
        modelAndView.addObject("a",a);
        return modelAndView;
    }
}
