package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.entity.Lease;

import java.sql.Date;
import java.util.List;

public interface LeaseService {
    List<Lease> findAll();
    List<Lease> getByUser(String username);
    List<Lease> getByBook(String name);
    Lease getById(int id);
    void updateDate(Date date, Date retdate,int id);
    void newLease(int userid,int bookid,Date date,Date retdate);
}
