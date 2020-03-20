package com.cisco.rbac.model.controller;

import com.cisco.rbac.common.annotation.JwtIgnore;
import com.cisco.rbac.model.entity.Role;
import com.cisco.rbac.model.entity.RolePermissionRelation;
import com.cisco.rbac.model.entity.User;
import com.cisco.rbac.model.service.impl.UserServiceImpl;
import com.cisco.rbac.common.jwt.JwtParam;
import com.cisco.rbac.common.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/")
    @JwtIgnore
    public String index(){
        return "login";
    }


//登录
    @ResponseBody
    @RequestMapping(value = "/login")
    @JwtIgnore
    public Map<String,String> login(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletResponse response) {
        int id2 = Integer.parseInt(id);
        Map map = new HashMap<String, String>();
        boolean result = userService.checkUserAndPassword(id2, password);
        if (result) {
            User user = userService.getByIdWithResult(id2);
            List<Role> roleList = user.getRoles();
            Map<String, Object> claim = new HashMap<>();
            //生成JWT存储用户角色信息
            claim.put("rolelist", roleList);
            String token = JwtUtils.createToken(id + "", claim, jwtParam);
            if (token == null) {
                System.out.println("用户签名失败");
                return null;
            }
            String a = JwtUtils.getAuthorizationHeader(token);
            System.out.println("用户生成签名:" + a);
            map.put("a", a);
            map.put("r", "1");
            return map;
        } else {

            map.put("erro", "something wrong happern");
            map.put("r", "2");
            return map;
        }
    }


    //用户进入注册界面
    @RequestMapping("/logon")
    @JwtIgnore
    public String login(){
        return "logon";
    }



    @RequestMapping("/unauthorized")
    @JwtIgnore
    public ModelAndView unauth(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("unauth");
        return modelAndView;
    }


    @RequestMapping("/index")
    @JwtIgnore
    public  String home()
    {
        return "index";
    }

    @RequestMapping("addrole/goroot")
    @JwtIgnore
    public  ModelAndView goRoot2()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("addp/goroot/")
    @JwtIgnore
    public  ModelAndView goRoot3(@PathVariable("a") String a )
    {
        System.out.println("restful传的参数:"+a);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("a",a);
        return modelAndView;
    }



    @RequestMapping("main")
    public String main(){
        return "main";
    }


    @RequestMapping("users.html")
    public String users(){return "users";}

    @RequestMapping("roles.html")
public String roles(){return "roles";}


@RequestMapping("druid")
public String druid(){
        return "http://localhost:8084/druid/index.html";
}


@RequestMapping("permissions.html")
    public  String permissions(){
        return "permissions";
}
}

