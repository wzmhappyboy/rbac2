package com.cisco.rbac.controller;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.Rolerightrelation;
import com.cisco.rbac.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PostMapping("/role")
    public  String insertRole(@RequestBody Map<String,String> userMap){
        Role role=new Role();
        int id=Integer.parseInt(userMap.get("id"));
        role.setId(id);
        role.setName(userMap.get("name"));
        role.setDescription(userMap.get("description"));
        boolean result =roleService.insertRole(role);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }

    //给角色赋予权限
    @PostMapping("/rolerightrelation")
    public  String insertRolerightrelation(@RequestBody Map<String,String> userMap){
        Rolerightrelation rrr=new Rolerightrelation();
        int id=Integer.parseInt(userMap.get("id"));
        int role_id=Integer.parseInt(userMap.get("role_id"));
        int right_id=Integer.parseInt(userMap.get("right_id"));
        int right_type= Integer.parseInt(userMap.get("right_type"));
        rrr.setRight_id(right_id);
        rrr.setRight_type(right_type);
        rrr.setRole_id(role_id);
        rrr.setId(id);

        boolean result =roleService.insertRolerightrelation(rrr);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }

    //给角色改权限
    @PostMapping("/newrolerightrelation")
    public  String updateRolerightrelation(@RequestBody Map<String,String> rrrMap){
        Rolerightrelation rrr=new Rolerightrelation();
        rrr.setId(Integer.valueOf(rrrMap.get("id")));
        rrr.setRole_id(Integer.valueOf(rrrMap.get("role_id")));
        rrr.setRight_type(Integer.valueOf(rrrMap.get("right_type")));
        rrr.setRight_id(Integer.valueOf(rrrMap.get("right_id")));
        boolean result =roleService.updateRolerightrelation(rrr);
        if (result){
            return  "success";
        }
        else {
            return  "fail";
        }
    }

    //删除角色权限
    @DeleteMapping("/rolerightrelation/{id}")
    public  String deleteUserById(@PathVariable("id") int id){
        boolean result=roleService.deleteRolerightrelationById(id);
        if (result)
        {
            return  "success";
        }
        else{
            return  "fail";
        }
    }
}
