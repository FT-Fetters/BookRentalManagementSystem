package com.lyun.bookrentalmanagementsystem.controller.press;

import com.alibaba.druid.sql.visitor.functions.DateAdd;
import com.lyun.bookrentalmanagementsystem.entity.Book;
import com.lyun.bookrentalmanagementsystem.service.BookService;
import com.lyun.bookrentalmanagementsystem.service.PressService;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import com.lyun.bookrentalmanagementsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/press/manage")
public class PressManageApi {
    @Autowired
    UserService userService;

    @Autowired
    PressService pressService;

    @Autowired
    BookService bookService;

    /**
     * 新建出版社
     * @param name 出版社名字
     * @param address 出版社地址
     * @param phone 出版社电话
      */
    @RequestMapping(value ="/new",method = RequestMethod.POST)
    public Object newPress(@RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String phone,
                           HttpServletRequest request,
                           HttpServletResponse response){
        if (!UserUtils.checkPower(userService,request,5)){
            return new ResultBody<>(false,502,"permission denied");
        }
        if (pressService.getByName(name) != null){
            return new ResultBody<>(false,501,"already exist");
        }
        pressService.newPress(name, address,phone);
        return new ResultBody<>(true,200,null);
    }

    /**
     * 删除出版社
     * @param name 出版社名字
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object deletePress(@RequestParam String name,
                              HttpServletRequest request,
                              HttpServletResponse response){
        if (!UserUtils.checkPower(userService,request,5)){
            return new ResultBody<>(false,500,"permission denied");
        }
        pressService.deleteByName(name);
        return new ResultBody<>(true,200,null);
    }

    /**
     * @param name 出版社名字
     * @param address 出版社地址
     * @param phone 出版社电话
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updatePress(@RequestParam String name,
                              @RequestParam String address,
                              @RequestParam String phone,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        if (!UserUtils.checkPower(userService, request, 5)) {
            return new ResultBody<>(false, 502, "permission denied");
        }
        pressService.updatePress(name, address, phone);
        return new ResultBody<>(true, 200, null);
    }
}
