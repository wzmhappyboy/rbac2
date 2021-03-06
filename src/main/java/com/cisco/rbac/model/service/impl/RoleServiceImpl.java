package com.cisco.rbac.model.service.impl;

import com.cisco.rbac.common.annotation.JwtIgnore;
import com.cisco.rbac.model.entity.Role;
import com.cisco.rbac.model.entity.RolePermissionRelation;
import com.cisco.rbac.model.mapper.RoleMapper;
import com.cisco.rbac.model.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
@Autowired
RoleMapper roleMapper;

//增加角色
    @Transactional
    @Override
    public  boolean insertRole(Role role){
//        if(role.getId()!=null&&!"".equals(role.getId())){
            try {
                int effecteNum = roleMapper.insertRole(role);
                if (effecteNum > 0) {
                    System.out.println("增加角色成功，id为" + role.getId());
                    return true;
                } else {
                    throw new RuntimeException("插入信息失败，插入行数有误");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入信息失败"+e.getMessage());
            }
   //     }
//        else {
//            throw  new RuntimeException("id不能为空！！！");
//        }
    }

    //增加权限
    @Transactional
    @Override
    public  boolean insertRolerightrelation(RolePermissionRelation rrr){
//        if(rrr.getId()!=null&&!"".equals(rrr.getId())){
            try {
                int effecteNum = roleMapper.insertRolerightrelation(rrr);
                if (effecteNum > 0) {
                    System.out.println("增加用户权限成功，id为" + rrr.getId());
                    return true;
                } else {
                    throw new RuntimeException("插入信息失败，插入行数有误");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入信息失败"+e.getMessage());
            }
        }
//        else {
//            throw  new RuntimeException("id不能为空！！！");
//        }
//    }

    @Transactional
    @Override
    public  boolean updateRolerightrelation(RolePermissionRelation rrr){
        if (rrr.getId()!=null&&!"".equals(rrr.getId())){
            try {
                boolean effecteNum=roleMapper.updateRolerightrelation(rrr);
                if (effecteNum) {
                    System.out.println("更新成功，主键为："+rrr.getId());
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

    @Override
    public  boolean deleteRolerightrelationById(RolePermissionRelation rrr){
        try {
            int effecteNum=roleMapper.deleteRolerightrelationById(rrr);
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


    @Override
    public  boolean deleteRoleById(int id){
        try {
            int effecteNum=roleMapper.deleteRoleById(id);
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
    public  boolean updateRole(Role role){
        if (role.getId()!=null&&!"".equals(role.getId())){
            try {
                boolean effecteNum=roleMapper.updateRole(role);
                if (effecteNum) {
                    System.out.println("更新成功，主键为："+role.getId());
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

    @Override
    @JwtIgnore
    public PageInfo<Role> queryRole(Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);

        List<Role> list= roleMapper.queryRole();
        return new PageInfo<>(list);
    }

    @Override
    public Role  getByIdWithResult(int id){
        return  roleMapper.getByIdWithSelect(id);
    }

    @Override
    public List<RolePermissionRelation> showpermissionbyroleid(int id){
        return roleMapper.showpermissionbyroleid(id);
}


}
