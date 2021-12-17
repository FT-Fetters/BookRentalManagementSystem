package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Lease;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface LeaseDao extends BaseDao<Lease>{

    List<Lease> getByUser(int userId);
    List<Lease> getByBook(int bookId);
    Lease getById(int id);
    void updateDate(Date date,Date retdate,int id);
    void newLease(int userid,int bookid,Date date,Date retdate);
}
