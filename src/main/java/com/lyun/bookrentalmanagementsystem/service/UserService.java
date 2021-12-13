package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean check(String username,String password);
    User getByUsername(String username);
}
