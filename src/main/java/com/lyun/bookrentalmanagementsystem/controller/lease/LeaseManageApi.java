package com.lyun.bookrentalmanagementsystem.controller.lease;

import com.lyun.bookrentalmanagementsystem.service.LeaseService;
import com.lyun.bookrentalmanagementsystem.service.UserService;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import com.lyun.bookrentalmanagementsystem.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@RequestMapping("/lease/manage")
@RestController
public class LeaseManageApi {

    @Autowired
    LeaseService leaseService;

    @Autowired
    UserService userService;

    @RequestMapping("/update/date")
    public Object updateDate(@RequestParam String date,
                             @RequestParam String retdate,
                             @RequestParam int id,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (UserUtils.checkPower(userService, request, 5)){
            return new ResultBody<>(false,502,"permission denied");
        }
        Date bd = Date.valueOf(date);
        Date rd = Date.valueOf(retdate);
        if (bd.after(rd)){
            return new ResultBody<>(false,500,"return date is early than date");
        }
        if (leaseService.getById(id) == null){
            return new ResultBody<>(false,501,"unknown lease");
        }
        leaseService.updateDate(bd,rd,id);
        return new ResultBody<>(true,200,null);
    }

}
