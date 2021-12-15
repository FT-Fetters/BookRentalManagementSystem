package com.lyun.bookrentalmanagementsystem.utils;

import com.lyun.bookrentalmanagementsystem.entity.User;
import com.lyun.bookrentalmanagementsystem.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static boolean checkPower(UserService userService, HttpServletRequest request,int power){
        String token = CookieUtils.getCookie(request,"token");
        String username = User.Token.tokens.get(token);
        int userPower = userService.getByUsername(username).getPower();
        return userPower >= power;
    }
}
