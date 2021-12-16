package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Press;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PressDao extends BaseDao<Press>{
    List<Press> get(String name);
    Press getByName(String name);
    void newPress(String name,String address,String phone);
    void deleteByName(String name);
    void updatePress(String name,String address,String phone);

}
