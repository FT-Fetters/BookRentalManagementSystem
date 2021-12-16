package com.lyun.bookrentalmanagementsystem.service;

import com.alibaba.fastjson.JSONObject;
import com.lyun.bookrentalmanagementsystem.entity.Press;

import java.util.List;

public interface PressService {
    List<Press> findAll();
    List<Press> get(String name);
    Press getByName(String name);
    void newPress(String name,String address,String phone);
    void deleteByName(String name);
    void updatePress(String name,String address,String phone);

}
