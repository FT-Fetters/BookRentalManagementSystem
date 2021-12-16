package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.dao.UserInfoDao;
import com.lyun.bookrentalmanagementsystem.entity.User;
import com.lyun.bookrentalmanagementsystem.entity.UserInfo;
import com.lyun.bookrentalmanagementsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    UserService userService;

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo getByUsername(String username) {
        User user = userService.getByUsername(username);
        return userInfoDao.getById(user.getId());
    }
}
