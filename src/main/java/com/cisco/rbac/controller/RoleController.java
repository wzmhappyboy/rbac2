package com.cisco.rbac.controller;

import com.cisco.rbac.PermissionConstants;
import com.cisco.rbac.annotation.RequiredPermission;
import com.cisco.rbac.entity.Permission;
import com.cisco.rbac.entity.Role;
import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.service.impl.PermissionServiceImpl;
import com.cisco.rbac.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PermissionServiceImpl permissionService;


    //增加角色
    @ResponseBody
    @RequestMapping("/addroles")
    public  Map<String,Object> insertRole(@RequestParam("name") String name,@RequestParam("description") String description){
        Role role=new Role();
        role.setName(name);
        role.setDescription(description);
        boolean result =roleService.insertRole(role);
       Map<String,Object> r=new HashMap<>();
        if (result){
            r.put("s","1");
        }
        else{
            r.put("s","2");
        }
    return r;
    }

    //给角色赋予权限
    @ResponseBody
    @RequestMapping("/rolerightrelations")
    public  Map<String,Object> insertRolerightrelation(@RequestParam("role_id") String role_id,@RequestParam("right_id") String right_id){
        RolePermissionRelation rrr=new RolePermissionRelation();
        //int id=Integer.parseInt(userMap.get("id"));
        Map<String,Object> r=new HashMap<>();
        rrr.setPermissionId(Integer.parseInt(right_id));
        rrr.setPermissionType(0);
        rrr.setRoleId(Integer.parseInt(role_id));
//        rrr.setId(id);

        boolean result =roleService.insertRolerightrelation(rrr);
        if (result){
            r.put("s","1");
        }
        else{
            r.put("s","2");
        }
        return r;
    }

    //给角色改权限
    @PostMapping("/newrolerightrelations")
    public  String updateRolerightrelation(@RequestBody Map rrrMap){
        RolePermissionRelation rrr=new RolePermissionRelation();
        rrr.setId(Integer.valueOf((String) rrrMap.get("id")));
        rrr.setRoleId(Integer.valueOf((String) rrrMap.get("role_id")));
        rrr.setPermissionType(Integer.valueOf((String) rrrMap.get("right_type")));
        rrr.setPermissionId(Integer.valueOf((String) rrrMap.get("right_id")));
        roleService.deleteRolerightrelationById(rrr);

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
    @ResponseBody
    @RequestMapping("/removerrr")
    public  Map<String,Object> deleteUserById(@RequestParam("role_id") String role_id,@RequestParam("right_id") String right_id){
    System.out.println("删除的角色ID:"+role_id+"删除的权限id:"+right_id);
       RolePermissionRelation rolePermissionRelation=new RolePermissionRelation();
       rolePermissionRelation.setRoleId(Integer.parseInt(role_id));
       rolePermissionRelation.setPermissionId(Integer.parseInt(right_id));
       rolePermissionRelation.setPermissionType(0);
       Map<String,Object> r=new HashMap<>();
       boolean result=roleService.deleteRolerightrelationById(rolePermissionRelation);
        if (result)
        {
            r.put("s","1");
        }
        else{
            r.put("s","2");
        }
        return  r;
    }


    //删除角色
    @ResponseBody
    @RequestMapping("/deleteroles")
    public  Map<String,Object> deleteRoleById(@RequestParam("id") String id){
        Map<String,Object> r=new HashMap<>();
        System.out.println("要删除的角色id为："+id);
        int id2=Integer.parseInt(id);
        boolean result=roleService.deleteRoleById(id2);
        if (result)
        {
            r.put("suceesee","1");
        }
        else{
            r.put("suceesee","0");
        }
        return  r;
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

//   返还所有角色
    @ResponseBody
    @RequestMapping("/showroles")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
    public  Map<String,Object> getAllRoles(){
        List<Role> roleList = roleService.queryRole();
        Map<String,Object> result =new HashMap<>();
        result.put("roleslist",roleList);
        return  result;
    }



    //列出所有权限
    @ResponseBody
    @RequestMapping("/showps")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
    public  Map<String,Object> getAllPermissions(){
        List<Permission> permissionList = permissionService.queryPermission();

        Map<String,Object> result =new HashMap<>();
        result.put("permissionslist",permissionList);
        return  result;
    }




}
