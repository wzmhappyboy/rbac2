package com.cisco.rbac.model.service.impl;

import com.cisco.rbac.model.entity.Permission;
import com.cisco.rbac.model.mapper.PermissionMapper;
import com.cisco.rbac.model.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public  boolean insertPermission(Permission permission){
 //       if(permission.getId()!=null&&!"".equals(permission.getId())){
            try {
                int effecteNum = permissionMapper.insertPermission(permission);
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
//        }
//        else {
//            throw  new RuntimeException("id不能为空！！！");
//        }
    }




    @Override
    public PageInfo<Permission> queryPermission(Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);
        List<Permission> list=permissionMapper.queryPermission();

return new PageInfo<>(list);

    }

    @Override
    public  boolean deletePermissionById(int id){
        try {
            int effecteNum= permissionMapper.deletePermissionById(id);
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

    @Transactional
    @Override
    public  boolean updatePermission(Permission p){
        if (p.getId()!=null&&!"".equals(p.getId())){
            try {
                boolean effecteNum=permissionMapper.updatePermission(p);
                if (effecteNum) {
                    System.out.println("更新成功，主键为："+p.getId());
                    return  true;
                }else {
                    throw  new RuntimeException("更新信息失败，插入行数有误");
                }
            } catch (Exception e){
                throw  new RuntimeException("更新信息失败了："+e.getMessage());
            }

        }
        else {
            throw  new RuntimeException("信息不能为空！！");
        }
    }

}
