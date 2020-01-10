package com.cisco.rbac.controller;

import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PageController {
    @Autowired
    UserServiceImpl userService;


    @PostMapping("/user/permission")
    public ModelAndView showpermissions(@RequestParam("userid") int id)
    {
        List<RolePermissionRelation> rightsList=userService.queryUserrights(id);
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<rightsList.size();i++)
        {
            list.add(rightsList.get(i).getPermissionId());
            System.out.println(rightsList.get(i).getPermissionId());
        }
        System.out.println("id:"+id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("id",id);
        mv.addObject("plist",list);
        mv.setViewName("showpermission");
        return mv;
    }
}

