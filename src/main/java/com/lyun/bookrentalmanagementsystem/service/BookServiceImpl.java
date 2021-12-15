package com.lyun.bookrentalmanagementsystem.service;

import com.alibaba.fastjson.JSONObject;
import com.lyun.bookrentalmanagementsystem.dao.BookDao;
import com.lyun.bookrentalmanagementsystem.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService{


    @Autowired
    private BookDao bookDao;


    @Override
    public List<Book> get(String author, String press, String type, String pubdate, String lang) {
        if (author.equals("-"))author="";
        if (press.equals("-"))press="";
        if (type.equals("-"))type="";
        if (pubdate.equals("-"))pubdate="";
        if (lang.equals("-"))lang="";
        return bookDao.get(author, press, type, pubdate, lang);
    }

    @Override
    public JSONObject getBookClass() {
        JSONObject res = new JSONObject();
        res.put("author",bookDao.getAuthorClass());
        res.put("press",bookDao.getPressClass());
        res.put("type",bookDao.getTypeClass());
        res.put("pubdate",bookDao.getPubdateClass());
        res.put("lang",bookDao.getLangClass());
        return res;
    }

    @Override
    public Book getByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public void newBook(String name, String author,String press, String type, String pubdate, String lang) {
        bookDao.newBook(name, author, press, type, pubdate, lang);
    }

    @Override
    public void deleteByName(String name) {
        bookDao.deleteByName(name);
    }

    @Override
    public void updateBook(String name, String author, String press, String type, String pubdate, String lang) {
        bookDao.updateBook(name, author, press, type, pubdate, lang);
    }


}
