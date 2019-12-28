package com.cisco.rbac.controller;

import com.cisco.rbac.entity.Permission;
import com.cisco.rbac.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RightController {
    @Autowired
    PermissionServiceImpl rightService;

    @PostMapping("/rights")
    public  String insertUser(@RequestBody Map<String,String> userMap){
        Permission permission =new Permission();
       // int id=Integer.parseInt(userMap.get("id"));
      //  permission.setId(id);
        permission.setName(userMap.get("name"));
        permission.setDescription(userMap.get("description"));
        boolean result =rightService.insertRight(permission);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }



    @GetMapping("rights")
    public  String getAllRight(){
        List<Permission> permissionList =rightService.queryRight();
        return  permissionList.toString();
    }

    @DeleteMapping("/rights/{id}")
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
