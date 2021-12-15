package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.entity.Press;

import java.util.List;

public interface PressService {
    List<Press> findAll();
    Press getByName(String name);
}
