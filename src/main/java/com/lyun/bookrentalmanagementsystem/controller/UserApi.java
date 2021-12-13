package com.lyun.bookrentalmanagementsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyun.bookrentalmanagementsystem.entity.User;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.CookieUtils;
import com.lyun.bookrentalmanagementsystem.utils.LogUtils;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserApi {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody JSONObject data, HttpServletResponse response, HttpServletRequest request){
        String username = data.getString("username");
        String password = data.getString("password");
        if (username != null && password != null && userService.check(username,password)){
            String token = User.Token.createToken(username);
            CookieUtils.writeCookie(response,"token",token,3600);
            LogUtils.log(username + " login","login",true,request);
            return new ResultBody<>(true,200,null);
        }else {
            LogUtils.log("login fail","login",true,request);
            return new ResultBody<>(false,501,"error password or username is not exist");
        }
    }
}
