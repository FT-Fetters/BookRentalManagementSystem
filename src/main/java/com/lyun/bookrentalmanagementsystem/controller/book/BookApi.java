package com.lyun.bookrentalmanagementsystem.controller.book;


import com.lyun.bookrentalmanagementsystem.entity.Book;
import com.lyun.bookrentalmanagementsystem.service.BookService;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookApi {

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public ResultBody<?> get(@RequestParam String author,
                      @RequestParam String press,
                      @RequestParam String type,
                      @RequestParam String pubdate,
                      @RequestParam String language,
                      HttpServletRequest request,
                      HttpServletResponse response){
        List<Book> bookList = bookService.get(author, press, type, pubdate, language);
        return new ResultBody<>(true,200,bookList);
    }

}
