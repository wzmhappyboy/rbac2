package com.cisco.rbac.model.controller;

import com.cisco.rbac.common.annotation.RequiredPermission;
import com.cisco.rbac.common.util.PermissionConstants;
import com.cisco.rbac.model.entity.Permission;
import com.cisco.rbac.model.service.impl.PermissionServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {
    @Autowired
    PermissionServiceImpl permissionService;

    //分页显示权限
    @ResponseBody
    @GetMapping("/permissionslist")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
    public  Map<String,Object> getPermissions(@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "pageSize",defaultValue = "3") String pageSize){
        PageInfo<Permission> pageInfo = permissionService.queryPermission(page,Integer.parseInt(pageSize));

        Map<String,Object> result =new HashMap<>();

//        //获得当前页
        result.put("pageNum",pageInfo.getPageNum());
//        //获得一页显示的条数
        result.put("pageSize",pageInfo.getPageSize());
//        //是否是第一页
        result.put("isFirstPage",pageInfo.isIsFirstPage());
        //       System.out.println("isFirstPage:"+pageInfo.isIsFirstPage());
//        //获得总页数
        result.put("totalPages",pageInfo.getPages());
//        //是否是最后一页
        result.put("isLastPage",pageInfo.isIsLastPage());
        result.put("permissionslist",pageInfo.getList());
        System.out.println("分页返还权限:"+pageInfo.getList());

        return  result;
    }



    //新增权限
    @ResponseBody
    @PostMapping("/permissions")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)
    public  Map<String,Object> insertPermission(@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("url") String url){
        Permission permission =new Permission();
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        boolean result = permissionService.insertPermission(permission);
System.out.println("插入权限方法");
        Map<String,Object> r=new HashMap<>();
        if (result){
            r.put("s","1");
        }
        else{
            r.put("s","0");
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
    @DeleteMapping("/permissions")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)

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

//返回指定权限
    @ResponseBody
@GetMapping("/permissions/{id}")
public  Map<String,Object> getPermissionById(@PathVariable("id") int id){
    Permission p=permissionService.queryPermissionById(id);

        List<Permission> permissionList=new LinkedList<>();
        permissionList.add(p);

    Map<String,Object> result=new HashMap<>();
    result.put("p",p);
    result.put("plist",permissionList);
    return  result;
}



    //更改权限
    @ResponseBody
    @PutMapping("/permissions")
    @RequiredPermission(PermissionConstants.ADMIN_PERMISSION)

    public  Map<String,Object> updatePermission(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("url") String url,@RequestParam("type") int type){
        System.out.println("执行更改权限方法");
        Map<String,Object> map=new HashMap<>();
        Permission permission=new Permission();
        permission.setId(id);
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setType(type);
        boolean result =permissionService.updatePermission(permission);
        if (result){
            map.put("s","1");
        }
        else {
            map.put("s","0");
        }
        return map;
    }
}
