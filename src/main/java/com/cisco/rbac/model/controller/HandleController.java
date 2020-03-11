package com.cisco.rbac.model.controller;


import com.cisco.rbac.common.annotation.JwtIgnore;
import com.cisco.rbac.model.entity.Permission;
import com.cisco.rbac.model.entity.Role;
import com.cisco.rbac.model.entity.RolePermissionRelation;
import com.cisco.rbac.model.entity.User;
import com.cisco.rbac.model.service.impl.PermissionServiceImpl;
import com.cisco.rbac.model.service.impl.RoleServiceImpl;
import com.cisco.rbac.model.service.impl.UserServiceImpl;
import com.cisco.rbac.common.jwt.JwtParam;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    PermissionServiceImpl permissionService;
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
    @RequestMapping("/manageur/{id}/{a}/{page}")
    @JwtIgnore
    public ModelAndView manageur(@PathVariable("id") String id, @PathVariable("a") String a ,@PathVariable(value = "page") int page)
    {
        int ad=Integer.parseInt(id);
        User user=userService.getByIdWithResult(ad);
        PageInfo<Role> pageInfo = roleService.queryRole(page,5);

        List<Role> roleList=user.getRoles();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());
        modelAndView.addObject("roles", pageInfo.getList());
        modelAndView.setViewName("manageuserrolerelation");
        modelAndView.addObject("id",id);
        modelAndView.addObject("a",a);
        modelAndView.addObject("list",roleList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());
        modelAndView.addObject("pageSize", pageInfo.getPageSize());
        modelAndView.addObject("totalPages", pageInfo.getPages());

        return modelAndView;
    }

//    进入管理角色界面
    @RequestMapping("/managerp/{id}/{a}/{page}")
    @JwtIgnore
    public ModelAndView managerp(@PathVariable("id") String id, @PathVariable("a") String a,@PathVariable(value = "page") int page )
    {
        int ad=Integer.parseInt(id);

        PageInfo<Permission> pageInfo = permissionService.queryPermission(page,5);

List<RolePermissionRelation> list=roleService.showpermissionbyroleid(ad);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("managerpr");
        modelAndView.addObject("id",id);
        modelAndView.addObject("a",a);
        modelAndView.addObject("list",list);


        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());
        modelAndView.addObject("ps", pageInfo.getList());
        modelAndView.addObject("page",page);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());
        modelAndView.addObject("pageSize", pageInfo.getPageSize());
        modelAndView.addObject("totalPages", pageInfo.getPages());


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
