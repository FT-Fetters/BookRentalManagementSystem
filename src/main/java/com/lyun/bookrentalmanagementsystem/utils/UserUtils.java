package com.lyun.bookrentalmanagementsystem.utils;

import com.lyun.bookrentalmanagementsystem.entity.User;
import com.lyun.bookrentalmanagementsystem.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static boolean checkPower(UserService userService, HttpServletRequest request,int power){
        String token = CookieUtils.getCookie(request,"token");
        String username = User.Token.tokens.get(token);
        if (userService.getByUsername(username) == null)return false;
        int userPower = userService.getByUsername(username).getPower();
        return userPower < power;
    }

    public static String getUsername(UserService userService,HttpServletRequest request){
        String token = CookieUtils.getCookie(request,"token");
        return User.Token.tokens.get(token);
    }

    public static int getUserId(UserService userService,HttpServletRequest request){
        String token = CookieUtils.getCookie(request,"token");
        return userService.getByUsername(User.Token.tokens.get(token)).getId();
    }

    public static boolean isLogin(HttpServletRequest request){
        return User.Token.tokens.containsKey(CookieUtils.getCookie(request,"token"));
    }
}
