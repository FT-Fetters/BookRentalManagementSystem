package com.lyun.bookrentalmanagementsystem.dao;

import com.lyun.bookrentalmanagementsystem.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao extends BaseDao<Book>{
    List<Book> get(String author,String press,String type, String pubdate, String lang);
}
