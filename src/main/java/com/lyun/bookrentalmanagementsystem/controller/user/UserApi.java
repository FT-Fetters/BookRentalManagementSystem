package com.lyun.bookrentalmanagementsystem.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lyun.bookrentalmanagementsystem.entity.User;
import com.lyun.bookrentalmanagementsystem.entity.UserInfo;
import com.lyun.bookrentalmanagementsystem.service.UserInfoService;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.CookieUtils;
import com.lyun.bookrentalmanagementsystem.utils.LogUtils;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import com.lyun.bookrentalmanagementsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    /**
     * 用户登录
     */
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

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object register(@RequestBody JSONObject data, HttpServletResponse response, HttpServletRequest request){
        String username = data.getString("username");
        String password = data.getString("password");
        if (username != null && password != null){
            if(userService.getByUsername(username)!=null){
                if (password.length()>=6){
                    userService.newUser(username,password);
                    return new ResultBody<>(true,200,null);
                }else {
                    return new ResultBody<>(false,502,"password is too short");
                }
            }else {
                return new ResultBody<>(false,501,"username already exists");
            }
        }else {
            return new ResultBody<>(false,500,"missing parameter");
        }
    }

    /**
     * 湖区用户信息
     * @param username 用户名
     */
    @RequestMapping(value = "/info/get",method = RequestMethod.GET)
    public Object getUserInfo(@RequestParam String username, HttpServletRequest request){
        if (!UserUtils.isLogin(request)){
            return new ResultBody<>(false,500,"not login");
        }
        if (!UserUtils.checkPower(userService, request, 5)){
            if (!username.equals(UserUtils.getUsername(userService,request))){
                return new ResultBody<>(false,500,"not allow");
            }
        }
        UserInfo userInfo = userInfoService.getByUsername(username);
        return new ResultBody<>(true,200,userInfo);
    }
}
