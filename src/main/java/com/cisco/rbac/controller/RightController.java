package com.cisco.rbac.controller;

import com.cisco.rbac.entity.Right;
import com.cisco.rbac.service.impl.RightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RightController {
    @Autowired
    RightServiceImpl rightService;

    @PostMapping("/right")
    public  String insertUser(@RequestBody Map<String,String> userMap){
        Right right=new Right();
        int id=Integer.parseInt(userMap.get("id"));
        right.setId(id);
        right.setName(userMap.get("name"));
        right.setDescription(userMap.get("description"));
        boolean result =rightService.insertRight(right);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }



    @GetMapping("queryRight")
    public  String getAllRight(){
        List<Right> rightList=rightService.queryRight();
        return  rightList.toString();
    }

    @DeleteMapping("/right/{id}")
    public  String deleteUserById(@PathVariable("id") int id){
        boolean result=rightService.deleteRightById(id);
        if (result)
        {
            return  "success";
        }
        else{
            return  "fail";
        }
    }
}
