package com.lyun.bookrentalmanagementsystem.controller.book;


import com.lyun.bookrentalmanagementsystem.entity.Book;
import com.lyun.bookrentalmanagementsystem.service.BookService;
import com.lyun.bookrentalmanagementsystem.utils.ImageTools;
import com.lyun.bookrentalmanagementsystem.utils.PathTools;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
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

    @RequestMapping(value = "/get/name",method = RequestMethod.GET)
    public Object getByName(@RequestParam String name){
        if (bookService.getByName(name) != null){
            return new ResultBody<>(true,200,bookService.getByName(name));
        }else {
            return new ResultBody<>(false,500,"not found book");
        }
    }

    @SneakyThrows
    @RequestMapping(value = "/cover",method = RequestMethod.GET)
    public Object getBookCover(@RequestParam String name){
        if (bookService.getByName(name) == null){
            return new ResultBody<>(false,500,"unknown book");
        }
        File coverDir = new File(PathTools.getRunPath() + "/cover");
        if (!coverDir.exists()){
            coverDir.mkdirs();
            return new ResultBody<>(false,501,"not have cover");
        }
        String filename = DigestUtils.md5DigestAsHex(name.getBytes(StandardCharsets.UTF_8));
        File coverFile = new File(coverDir.getAbsolutePath() + "/" + filename +".jpg");
        if (coverFile.exists()){
            BufferedImage img = ImageIO.read(coverFile);
            String imgBase64 = ImageTools.imgToBase64(img);
            return new ResultBody<>(true,200,imgBase64);
        }else {
            return new ResultBody<>(false,501,"not have cover");
        }
    }

    @RequestMapping(value = "/class",method = RequestMethod.GET)
    public ResultBody<?> getBookClass(){
        return new ResultBody<>(true,200,bookService.getBookClass());
    }

}
