package com.lyun.bookrentalmanagementsystem.service;

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
}
