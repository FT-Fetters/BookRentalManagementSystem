package com.lyun.bookrentalmanagementsystem.controller.book;


import com.lyun.bookrentalmanagementsystem.service.BookService;
import com.lyun.bookrentalmanagementsystem.service.PressService;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.LangUtils;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import com.lyun.bookrentalmanagementsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book/manage")
public class BookManageApi {

    String[] bookLang = {"zh_cn","en_us","en_uk"};

    @Autowired
    PressService pressService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public Object newBook(@RequestParam String name,
                          @RequestParam String author,
                          @RequestParam String press,
                          @RequestParam String type,
                          @RequestParam String pubdate,
                          @RequestParam String lang,
                          HttpServletRequest request,
                          HttpServletResponse response){
        if (!UserUtils.checkPower(userService,request,5)){
            return new ResultBody<>(false,502,"permission denied");
        }
        if (pressService.getByName(press)==null){
            return new ResultBody<>(false,500,"unknown press");
        }
        if (!LangUtils.containsLang(lang)){
            return new ResultBody<>(false,501,"unknown lang");
        }
        bookService.newBook(name, author, press, type, pubdate, lang);
        return new ResultBody<>(true,200,null);
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object deleteBook(@RequestParam String name,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (bookService)

    }
}
