package com.cisco.rbac.model.controller;

import com.cisco.rbac.model.entity.Permission;
import com.cisco.rbac.model.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PermissionController {
    @Autowired
    PermissionServiceImpl permissionService;


    //新增权限
    @ResponseBody
    @RequestMapping("/addper")
    public  Map<String,Object> insertPermission(@RequestParam("name") String name,@RequestParam("description") String description){
        Permission permission =new Permission();
        permission.setName(name);
        permission.setDescription(description);
        boolean result = permissionService.insertPermission(permission);

        Map<String,Object> r=new HashMap<>();
        if (result){
            r.put("s","1");
        }
        else{
            r.put("s","2");
        }
        return r;

    }



    //查询所有权限
//    @GetMapping("/permissions")
//    public  String getAllPermissions(){
//        List<Permission> permissionList = permissionService.queryPermission();
//        return  permissionList.toString();
//    }

    //删除指定权限
    @ResponseBody
    @RequestMapping("/deleterights")
    public  Map<String,Object> deleteUserById(@RequestParam("id") String id){
        int id2= Integer.parseInt(id);
        boolean result= permissionService.deletePermissionById(id2);
        Map<String,Object> r=new HashMap<>();
        if (result)
        {
            r.put("s","1");
        }
        else{
            r.put("s","0");
        }
        return  r;
    }


    //更改权限
    @PutMapping("/permission")
    public  String updatePermission(@RequestBody Map<String,String> rrrMap){

        Permission permission=new Permission();
        permission.setId(Integer.valueOf(rrrMap.get("id")));
        permission.setName(rrrMap.get("permissionName"));
        permission.setDescription(rrrMap.get("description"));
        boolean result =permissionService.updatePermission(permission);
        if (result){
            return  "success";
        }
        else {
            return  "fail";
        }
    }
}
