package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao extends BaseDao<UserInfo>{
    UserInfo getById(int id);

}
