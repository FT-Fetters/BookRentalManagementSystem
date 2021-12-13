package com.lyun.bookrentalmanagementsystem.dao;


import com.lyun.bookrentalmanagementsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<User>{
    User getByUsername(String username);
}
