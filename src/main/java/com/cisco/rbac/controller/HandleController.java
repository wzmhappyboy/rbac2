package com.cisco.rbac.controller;


import com.cisco.rbac.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HandleController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/deletusers/{id}/{a}")
    public Map<String,Object> deleteUserById(@PathVariable("id") String id,@PathVariable("a") String a){
        int ad=Integer.parseInt(id);

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
}
