package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.dao.LeaseDao;
import com.lyun.bookrentalmanagementsystem.entity.Lease;
import com.lyun.bookrentalmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class LeaseServiceImpl implements LeaseService{

    @Autowired
    private LeaseDao leaseDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Lease> findAll() {
        return leaseDao.findAll();
    }

    @Override
    public List<Lease> getByUser(String username) {
        User user = userService.getByUsername(username);
        return leaseDao.getByUser(user.getId());
    }
}
