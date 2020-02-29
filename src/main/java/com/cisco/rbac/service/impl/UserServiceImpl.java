package com.cisco.rbac.service.impl;


import com.cisco.rbac.entity.*;
import com.cisco.rbac.mapper.UserMapper;
import com.cisco.rbac.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        public PageInfo<User> queryUser(Integer page, Integer pageSize){
            PageHelper.startPage(page, pageSize);

            List<User> list= userMapper.queryUser();
            return new PageInfo<>(list);
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
    public  boolean deleteUserrolerelationById(UserRoleRelation urr){

        try {

            int effecteNum=userMapper.deleteUserrolerelation(urr);
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

    @Override
  public   boolean checkUserAndPassword(Integer id,String password){
        User user=new User();
        user.setId(id);
        user.setPassword(password);
        User test=userMapper.login(user);
        if (test!=null){
            return  true;
        }
        else {
            return  false;
        }
    }

@Override
    public List<Permission> showUserRoles(int id){
        List<Permission> list=userMapper.findPermissionByUserId(id);
        return  list;
}

@Override
  public   Set<String> getPermissionSet(List<Role> roleList) {
 Set<String> result=new HashSet<>();

    ObjectMapper mapper = new ObjectMapper();
    List<Role> list = mapper.convertValue(roleList, new TypeReference<List<Role>>() { });


    int l = roleList.size();
    for (int i = 0; i < l; i++) {
        System.out.println("返回权限的方法："+list.get(i));
if (list.get(0).getId()==1){
    result.add("admin_permission");
}
else {
    result.add("user_permission");
}
    }
    return result;
}
}