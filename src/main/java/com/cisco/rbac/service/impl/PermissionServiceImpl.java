package com.cisco.rbac.service.impl;

import com.cisco.rbac.entity.Permission;
import com.cisco.rbac.mapper.PermissionMapper;
import com.cisco.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Transactional
    @Override
    public  boolean insertRight(Permission permission){
        if(permission.getId()!=null&&!"".equals(permission.getId())){
            try {
                int effecteNum = permissionMapper.insertRight(permission);
                if (effecteNum > 0) {
                    System.out.println("增加权限成功，id为" + permission.getId());
                    return true;
                } else {
                    throw new RuntimeException("插入信息失败，插入行数有误");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入信息失败"+e.getMessage());
            }
        }
        else {
            throw  new RuntimeException("id不能为空！！！");
        }
    }




    @Override
    public List<Permission> queryRight(){
        return permissionMapper.queryRight();
    }

    @Override
    public  boolean deleteRightById(int id){
        try {
            int effecteNum= permissionMapper.deleteRightById(id);
            if (effecteNum>0){
                return  true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

}
