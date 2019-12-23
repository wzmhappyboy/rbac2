package com.cisco.rbac.service.impl;


import com.cisco.rbac.entity.Rolerightrelation;
import com.cisco.rbac.entity.User;
import com.cisco.rbac.entity.Userrolerelation;
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
        if(user.getId()!=null&&!"".equals(user.getId())){
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
        else {
            throw  new RuntimeException("id不能为空！！！");
        }
        }

        @Override
        public  User getUserById(int id){
        return  userMapper.getUserById(id);
        }


        @Override
        public List<User> queryUser(){
        return userMapper.queryUser();
        }

        @Override
        public  List<Rolerightrelation> queryUserrights(int id){return  userMapper.queryUserrights(id);}

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

    @Transactional
    @Override
    public  boolean insertUserrolerelation(Userrolerelation urr){
        if(urr.getId()!=null&&!"".equals(urr.getId())){
            try {
                int effecteNum = userMapper.insertUserrolerelation(urr);
                if (effecteNum > 0) {
                    System.out.println("增加成功，id为" + urr.getId());
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


    }

