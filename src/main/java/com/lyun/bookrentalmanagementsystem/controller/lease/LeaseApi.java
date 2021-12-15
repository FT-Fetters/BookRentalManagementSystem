package com.lyun.bookrentalmanagementsystem.controller.lease;


import com.lyun.bookrentalmanagementsystem.entity.Lease;
import com.lyun.bookrentalmanagementsystem.service.LeaseService;
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
import java.util.List;

@RequestMapping("/lease")
@RestController
public class LeaseApi {

    @Autowired
    UserService userService;

    @Autowired
    LeaseService leaseService;

    @RequestMapping(value = "/get/user",method = RequestMethod.GET)
    public Object getInfByUser(@RequestParam String username,
                               HttpServletRequest request,
                               HttpServletResponse response){
        if (!UserUtils.checkPower(userService,request,5)){
            if (!username.equals(UserUtils.getUsername(userService,request))){
                return new ResultBody<>(false,500,"not allow");
            }
        }
        List<Lease> leaseList = leaseService.getByUser(username);
        return new ResultBody<>(true,200,leaseList);
    }

}
