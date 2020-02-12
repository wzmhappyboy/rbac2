package com.cisco.rbac.controller;

import com.cisco.rbac.JwtIgnore;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.UserServiceImpl;
import com.cisco.rbac.util.JwtParam;
import com.cisco.rbac.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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


    @ResponseBody
    @RequestMapping(value = "/login")
    @JwtIgnore
    public Map<String,String> login(@RequestParam("id") String id,@RequestParam("password") String password) {
        int id2 = Integer.parseInt(id);
      //  String password = userMap.get("password");
        System.out.println("id:"+id+"password:"+password);
        boolean result = userService.checkUserAndPassword(id2, password);
        if (result) {
            String token = JwtUtils.createToken(id+"", jwtParam);
            if (token == null) {
               // log.error("===== 用户签名失败 =====");
                System.out.println("用户签名失败");
                return null;
            }
            String a=JwtUtils.getAuthorizationHeader(token);
            //log.info("===== 用户{}生成签名{} =====", userId, token);
           System.out.println("用户生成签名:"+a);

            // return JwtUtils.getAuthorizationHeader(token);


            Map map = new HashMap<String, String>();
            map.put("a", a);
            map.put("r","1");
            return map;


        }



         else {
            Map map = new HashMap<String, String>();
            map.put("erro", "something wrong happern");
            map.put("r","2" );
            return map;
        }

    }







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





    @ResponseBody
    @RequestMapping("/permissionin")
    public Map<String,Object> showpermissions(@RequestBody String id)
    {
        Map<String,Object> result=new HashMap<String, Object>();
        System.out.println("ajax传过去的id:"+id);
        int id2=Integer.parseInt(id);
        List<RolePermissionRelation> rightsList=userService.queryUserrights(Integer.parseInt(id));
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<rightsList.size();i++)
        {
            list.add(rightsList.get(i).getPermissionId());
            System.out.println(rightsList.get(i).getPermissionId());
        }
        result.put("list",list);
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("id",id);
//        mv.addObject("plist",list);
//        mv.setViewName("showpermission");
        return result;
    }
}

