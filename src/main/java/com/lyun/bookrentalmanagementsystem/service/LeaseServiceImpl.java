package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.dao.LeaseDao;
import com.lyun.bookrentalmanagementsystem.entity.Book;
import com.lyun.bookrentalmanagementsystem.entity.Lease;
import com.lyun.bookrentalmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class LeaseServiceImpl implements LeaseService{

    @Autowired
    private LeaseDao leaseDao;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public List<Lease> findAll() {
        return leaseDao.findAll();
    }

    @Override
    public List<Lease> getByUser(String username) {
        User user = userService.getByUsername(username);
        return leaseDao.getByUser(user.getId());
    }

    @Override
    public List<Lease> getByBook(String name) {
        Book book =  bookService.getByName(name);
        return leaseDao.getByBook(book.getId());
    }

    @Override
    public Lease getById(int id) {
        return leaseDao.getById(id);
    }

    @Override
    public void updateDate(Date date, Date retdate, int id) {
        leaseDao.updateDate(date, retdate, id);
    }
}
