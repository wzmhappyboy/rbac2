package com.cisco.rbac.controller;

import com.cisco.rbac.annotation.JwtIgnore;
import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.UserServiceImpl;
import com.cisco.rbac.jwt.JwtParam;
import com.cisco.rbac.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import com.iteason.anbaoli_vote_system.utils.AjaxResult;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private JwtParam jwtParam;

//用户访问进入首页
    @GetMapping("/")
    @JwtIgnore
    public ModelAndView index(ModelAndView model){
        model.setViewName("index");
        return model;
    }


//登陆
    @ResponseBody
    @RequestMapping(value = "/login")
    @JwtIgnore
    public Map<String,String> login(@RequestParam("id") String id,@RequestParam("password") String password) {
        int id2 = Integer.parseInt(id);


        boolean result = userService.checkUserAndPassword(id2, password);
        if (result) {
            User user=userService.getByIdWithResult(id2);
            List<Role> roleList=user.getRoles();
            Map<String,Object> claim=new HashMap<>();
            claim.put("rolelist",roleList);
            String token = JwtUtils.createToken(id+"", claim,jwtParam);
            if (token == null) {
                System.out.println("用户签名失败");
                return null;
            }
            String a=JwtUtils.getAuthorizationHeader(token);
           System.out.println("用户生成签名:"+a);
            boolean root=false;
            if (id2==10086&&password.equals("10086"))
            {
                root=true;
            }
            Map map = new HashMap<String, String>();
            map.put("a", a);
            if (root==false) {
                map.put("r", "1");
            }
            else {
                map.put("r","3");
            }

            return map;


        }
         else {
//用户名密码都为root则进入超级管理员
            Map map = new HashMap<String, String>();
            map.put("erro", "something wrong happern");
                map.put("r", "2");

            return map;
        }

    }


//普通用户进入用户首页
    @RequestMapping("gouserhome/{id}/{a}")
    @JwtIgnore
    public  ModelAndView showpermission(@PathVariable("id") String id,@PathVariable("a") String a )
    {
    System.out.println("restful传的参数:"+a);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("id",id);
        modelAndView.addObject("a",a);
        return modelAndView;
    }

//    管理员进入管理员首页
    @RequestMapping("goroot/{a}")
    @JwtIgnore
    public  ModelAndView goRoot(@PathVariable("a") String a )
    {
        System.out.println("restful传的参数:"+a);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("root");
        modelAndView.addObject("a",a);
        return modelAndView;
    }

    @RequestMapping("addrole/goroot/{a}")
    @JwtIgnore
    public  ModelAndView goRoot2(@PathVariable("a") String a )
    {
        System.out.println("restful传的参数:"+a);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("root");
        modelAndView.addObject("a",a);
        return modelAndView;
    }

    @RequestMapping("addp/goroot/{a}")
    @JwtIgnore
    public  ModelAndView goRoot3(@PathVariable("a") String a )
    {
        System.out.println("restful传的参数:"+a);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("root");
        modelAndView.addObject("a",a);
        return modelAndView;
    }
//根据用户ID返还用户权限
    @ResponseBody
    @RequestMapping("/permissionin")
    public Map<String,Object> showpermissions(@RequestBody String id)
    {
        Map<String,Object> result=new HashMap<String, Object>();
        int id2=Integer.parseInt(id);
        List<RolePermissionRelation> rightsList=userService.queryUserrights(Integer.parseInt(id));
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<rightsList.size();i++)
        {
            list.add(rightsList.get(i).getPermissionId());
        }
        result.put("list",list);

        return result;
    }
}

