package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.dao.PressDao;
import com.lyun.bookrentalmanagementsystem.entity.Press;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PressServiceImpl implements PressService{

    @Autowired
    private PressDao pressDao;

    @Override
    public List<Press> findAll() {
        return pressDao.findAll();
    }

    @Override
    public List<Press> get(String name) {
        if (name.equals("-"))name="";
        return pressDao.get(name); }



    @Override
    public Press getByName(String name) {
        return pressDao.getByName(name); }

    @Override
    public void updatePress(String name,String address,String phone){
        pressDao.updatePress(name,address,phone);}

    @Override
    public void deleteByName(String name){
        pressDao.deleteByName(name); }

    @Override
    public void newPress(String name,String address,String phone){
        pressDao.newPress(name,address,phone); }

}
