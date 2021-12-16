package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> findAll();
    UserInfo getByUsername(String username);
}
