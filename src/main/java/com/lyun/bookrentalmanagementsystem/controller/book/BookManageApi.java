package com.lyun.bookrentalmanagementsystem.controller.book;


import com.lyun.bookrentalmanagementsystem.service.BookService;
import com.lyun.bookrentalmanagementsystem.service.PressService;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;

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

    /**
     * 新建图书
     * @param name 书名
     * @param author 作者
     * @param press 出版社
     * @param type 书籍种类
     * @param pubdate 出版日期
     * @param lang 语种
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public Object newBook(@RequestParam String name,
                          @RequestParam String author,
                          @RequestParam String press,
                          @RequestParam String type,
                          @RequestParam String pubdate,
                          @RequestParam String lang,
                          HttpServletRequest request,
                          HttpServletResponse response){
        if (!UserUtils.checkPower(userService, request, 5)){
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


    /**
     * 删除书籍
     * @param name 书名
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object deleteBook(@RequestParam String name,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (!UserUtils.checkPower(userService, request, 5)){
            return new ResultBody<>(false,500,"permission denied");
        }
        if (bookService.getByName(name)==null){
            return new ResultBody<>(false,501,"not found book");
        }
        bookService.deleteByName(name);
        return new ResultBody<>(true,200,null);
    }

    /**
     * 更细书籍
     * @param name 书名
     * @param author 作者
     * @param press 出版社
     * @param type 书籍种类
     * @param pubdate 出版日期
     * @param lang 语种
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateBook(@RequestParam String name,
                             @RequestParam String author,
                             @RequestParam String press,
                             @RequestParam String type,
                             @RequestParam String pubdate,
                             @RequestParam String lang,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (!UserUtils.checkPower(userService, request, 5)){
            return new ResultBody<>(false,502,"permission denied");
        }
        if (pressService.getByName(press)==null){
            return new ResultBody<>(false,500,"unknown press");
        }
        if (!LangUtils.containsLang(lang)){
            return new ResultBody<>(false,501,"unknown lang");
        }
        bookService.updateBook(name, author, press, type, pubdate, lang);
        return new ResultBody<>(true,200,null);
    }

    @SneakyThrows
    @RequestMapping(value = "/upload/cover",method = RequestMethod.POST)
    public Object uploadCover(@RequestParam("file") MultipartFile file,@RequestParam String bookName, HttpServletRequest request){
        if (!UserUtils.checkPower(userService, request, 5)){
            return new ResultBody<>(false,502,"permission denied");
        }
        if (bookService.getByName(bookName) == null){
            return new ResultBody<>(false,500,"unknown book");
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        int unixSep = fileName.lastIndexOf('/');
        int winSep = fileName.lastIndexOf('\\');
        int pos = (Math.max(winSep, unixSep));
        if (pos != -1)  {
            fileName = fileName.substring(pos + 1);
        }
        String[] filenameSplit = fileName.split("\\.");
        String suffix = filenameSplit[filenameSplit.length-1];
        if (suffix.equals("jpg")){
            String savePath = PathTools.getRunPath() + "/cover/";
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                boolean mkdirs = saveDir.mkdirs();
            }
            File saveFile = new File(DigestUtils.md5DigestAsHex(bookName.getBytes(StandardCharsets.UTF_8)) + ".jpg");
            boolean b = saveFile.setWritable(true, false);
            file.transferTo(saveFile);
            LogUtils.log(" upload cover","upload",true,request);
            return new ResultBody<>(true,200,null);
        }else {
            LogUtils.log(  " upload cover fail, cause error suffix","upload",true,request);
            return new ResultBody<>(false,501,"wrong file type");
        }
    }
}
