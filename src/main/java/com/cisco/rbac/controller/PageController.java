package com.cisco.rbac.controller;

import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.UserServiceImpl;
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

    @ResponseBody
    @RequestMapping(value = "/login")
    public Map<String,String> login(@RequestParam("id") String id,@RequestParam("password") String password) {
        int id2 = Integer.parseInt(id);
      //  String password = userMap.get("password");
        System.out.println("id:"+id+"password:"+password);
        boolean result = userService.checkUserAndPassword(id2, password);
        if (result) {
            String token = userService.getToken(id);
            Map map = new HashMap<String, String>();
            map.put("token", token);
            map.put("r","1");
            return map;
        } else {
            Map map = new HashMap<String, String>();
            map.put("erro", "something wrong happern");
            map.put("r","2" );
            return map;
        }

    }







    @RequestMapping("gouserhome/{id}")
    public  ModelAndView showpermission(@PathVariable("id") String id )
    {
    System.out.println("restful传的参数:"+id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userhome");
        modelAndView.addObject("id",id);
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

