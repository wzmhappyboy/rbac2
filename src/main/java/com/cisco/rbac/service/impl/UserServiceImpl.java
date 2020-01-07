package com.cisco.rbac.service.impl;


import com.cisco.rbac.entity.RolePermissionRelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.UserRoleRelation;
import com.cisco.rbac.mapper.UserMapper;
import com.cisco.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional
    @Override
    public  boolean insertUser(User user){
//        if(user.getId()!=null&&!"".equals(user.getId())){
            try {
                int effecteNum = userMapper.insertUser(user);
                if (effecteNum > 0) {
                    System.out.println("增加成功，用户id为" + user.getId());
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
//        }

        @Override
        public  User getUserById(int id){
        return  userMapper.getUserById(id);
        }

        @Override
        public  User getByIdWithResult(int id){
        return  userMapper.getByIdWithSelect(id);
        }

        @Override
        public  User getPerssionById(int id){return  userMapper.getPerssionById(id);}

        @Override
        public List<User> queryUser(){
        return userMapper.queryUser();
        }

        @Override
        public  List<RolePermissionRelation> queryUserrights(int id){return  userMapper.queryUserrights(id);}

    @Override
    public  boolean deleteUserById(int id){
        try {
             int effecteNum=userMapper.deleteUserById(id);
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
    public  boolean deleteUserrolerelationById(int id){
        try {
            int effecteNum=userMapper.deleteUserrolerelation(id);
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
    public  boolean insertUserrolerelation(UserRoleRelation urr){
 //       if(urr.getId()!=null&&!"".equals(urr.getId())){
            try {
                int effecteNum = userMapper.insertUserrolerelation(urr);
                if (effecteNum > 0) {
                    System.out.println("增加角色成功，角色id为" + urr.getRoleId());
                    return true;
                } else {
                    throw new RuntimeException("插入信息失败，插入行数有误");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入信息失败"+e.getMessage());
            }
 //       }
//        else {
//            throw  new RuntimeException("id不能为空！！！");
//        }
    }

    @Transactional
    @Override
    public  boolean updateUser(User user){
        if (user.getId()!=null&&!"".equals(user.getId())){
            try {
                boolean effecteNum=userMapper.updateUser(user);
                if (effecteNum) {
                    System.out.println("更新成功，主键为："+user.getId());
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

