package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Year;
import java.util.List;

@Mapper
public interface BookDao extends BaseDao<Book>{
    List<Book> get(String author,String press,String type, String pubdate, String lang);
    List<String> getAuthorClass();
    List<String> getPressClass();
    List<String> getTypeClass();
    List<Year> getPubdateClass();
    List<String> getLangClass();
    void newBook(String name, String author,String press, String type, String pubdate, String lang);
    Book getByName(String name);
}
