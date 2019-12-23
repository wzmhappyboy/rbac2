package com.cisco.rbac.mapper;


import com.cisco.rbac.entity.Right;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RightMapper {
    //增加权限
    int insertRight(Right user);

    //根据id删除权限信息
    int deleteRightById(int id);

    //查询所有权限信息
    List<Right> queryRight();
}
