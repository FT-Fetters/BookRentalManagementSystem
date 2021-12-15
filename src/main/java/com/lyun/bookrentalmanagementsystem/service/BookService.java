package com.lyun.bookrentalmanagementsystem.service;

import com.alibaba.fastjson.JSONObject;
import com.lyun.bookrentalmanagementsystem.entity.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {

    List<Book> get(String author,
                   String press,
                   String type,
                   String pubdate,
                   String lang);
    JSONObject getBookClass();

    void newBook(String name,String author,String press,String type,String pubdate,String lang);

}
