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

    //增加角色
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
        rrr.setPermissionId(right_id);
        rrr.setPermissionType(right_type);
        rrr.setRoleId(role_id);
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
    public  String updateRolerightrelation(@RequestBody Map rrrMap){
        RolePermissionRelation rrr=new RolePermissionRelation();
        rrr.setId(Integer.valueOf((String) rrrMap.get("id")));
        rrr.setRoleId(Integer.valueOf((String) rrrMap.get("role_id")));
        rrr.setPermissionType(Integer.valueOf((String) rrrMap.get("right_type")));
        rrr.setPermissionId(Integer.valueOf((String) rrrMap.get("right_id")));
        roleService.deleteRolerightrelationById(Integer.valueOf((String) rrrMap.get("role_id")));

        List<Integer> rightlist =(List<Integer>) rrrMap.get("rightlist");
        for(int i=0;i<rightlist.size();i++)
        {
            int rightId=rightlist.get(i);
            RolePermissionRelation rpr=new RolePermissionRelation();
            rpr.setPermissionId(rightId);
            rpr.setRoleId(Integer.valueOf((String) rrrMap.get("role_id")));
            rpr.setPermissionType(0);
            roleService.insertRolerightrelation(rpr);
        }

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


    //删除角色
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


    //列出所有权限
    @GetMapping("roles")
    public  String getAllPermissions(){
        List<Role> permissionList = roleService.queryRole();
        return  permissionList.toString();
    }


    //返还类内的list<role>
    @GetMapping("/rolesbyid/{id}")
    public  String getUserByIdWithResult(@PathVariable("id") int id){
        Role role=roleService.getByIdWithResult(id);
        System.out.println(role);
        List<User> userList=role.getUsers();
        userList.forEach(n->System.out.println(n));
        return role.toString();
    }
}
