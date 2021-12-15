package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Press;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PressDao extends BaseDao<Press>{

    Press getByName(String name);
}
