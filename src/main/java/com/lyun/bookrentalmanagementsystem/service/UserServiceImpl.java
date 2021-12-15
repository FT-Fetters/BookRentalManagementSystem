package com.lyun.bookrentalmanagementsystem.service;


import com.lyun.bookrentalmanagementsystem.dao.UserDao;
import com.lyun.bookrentalmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean check(String username, String password) {
        User user = userDao.getByUsername(username);
        if (user == null){
            return false;
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        return user.getPassword().equals(password);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public void newUser(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        userDao.newUser(username,password);
    }
}
