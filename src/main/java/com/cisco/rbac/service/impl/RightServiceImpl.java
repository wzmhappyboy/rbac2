package com.cisco.rbac.service.impl;

import com.cisco.rbac.entity.Right;
import com.cisco.rbac.mapper.RightMapper;
import com.cisco.rbac.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RightServiceImpl implements RightService {
    @Autowired
    RightMapper rightMapper;

    @Transactional
    @Override
    public  boolean insertRight(Right right){
        if(right.getId()!=null&&!"".equals(right.getId())){
            try {
                int effecteNum = rightMapper.insertRight(right);
                if (effecteNum > 0) {
                    System.out.println("增加权限成功，id为" + right.getId());
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
    public List<Right> queryRight(){
        return rightMapper.queryRight();
    }

    @Override
    public  boolean deleteRightById(int id){
        try {
            int effecteNum=rightMapper.deleteRightById(id);
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
