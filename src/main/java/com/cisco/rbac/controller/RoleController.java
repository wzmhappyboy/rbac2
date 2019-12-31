package com.cisco.rbac.controller;

import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PostMapping("/roles")
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
    @PostMapping("/rolerightrelations")
    public  String insertRolerightrelation(@RequestBody Map<String,String> userMap){
        RolePermissionRelation rrr=new RolePermissionRelation();
        //int id=Integer.parseInt(userMap.get("id"));
        int role_id=Integer.parseInt(userMap.get("role_id"));
        int right_id=Integer.parseInt(userMap.get("right_id"));
        int right_type= Integer.parseInt(userMap.get("right_type"));
        rrr.setPermission_id(right_id);
        rrr.setPermission_type(right_type);
        rrr.setRole_id(role_id);
//        rrr.setId(id);

        boolean result =roleService.insertRolerightrelation(rrr);
        if (result){
            return  "success";
        }
        else{
            return  "fail";
        }
    }

    //给角色改权限
    @PostMapping("/newrolerightrelations")
    public  String updateRolerightrelation(@RequestBody Map<String,String> rrrMap){
        RolePermissionRelation rrr=new RolePermissionRelation();
        rrr.setId(Integer.valueOf(rrrMap.get("id")));
        rrr.setRole_id(Integer.valueOf(rrrMap.get("role_id")));
        rrr.setPermission_type(Integer.valueOf(rrrMap.get("right_type")));
        rrr.setPermission_id(Integer.valueOf(rrrMap.get("right_id")));
        boolean result =roleService.updateRolerightrelation(rrr);
        if (result){
            return  "success";
        }
        else {
            return  "fail";
        }
    }

    //删除角色权限
    @DeleteMapping("/rolerightrelations/{id}")
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

    @DeleteMapping("/roles/{id}")
    public  String deleteRoleById(@PathVariable("id") int id){
        boolean result=roleService.deleteRoleById(id);
        if (result)
        {
            return  "success";
        }
        else{
            return  "fail";
        }
    }

    //改角色信息
    @PostMapping("/newroles")
    public  String updateRole(@RequestBody Map<String,String> rrrMap){
        Role r=new Role();
        r.setDescription(rrrMap.get("description"));
        r.setId(Integer.valueOf(rrrMap.get("id")));
        r.setName(rrrMap.get("name"));
        boolean result =roleService.updateRole(r);
        if (result){
            return  "success";
        }
        else {
            return  "fail";
        }
    }

    @GetMapping("roles")
    public  String getAllPermissions(){
        List<Role> permissionList = roleService.queryRole();
        return  permissionList.toString();
    }


    //返还类内的list<user>
    @GetMapping("/rolesbyid/{id}")
    public  String getUserByIdWithResult(@PathVariable("id") int id){
        Role role=roleService.getByIdWithResult(id);
        System.out.println(role);
        List<User> userList=role.getUsers();
        userList.forEach(n->System.out.println(n));
        return role.toString();
    }
}
