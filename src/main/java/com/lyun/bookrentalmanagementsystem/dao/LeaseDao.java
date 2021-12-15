package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Lease;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaseDao extends BaseDao<Lease>{

    List<Lease> getByUser(int userId);
}
