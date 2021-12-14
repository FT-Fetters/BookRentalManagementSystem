package com.lyun.bookrentalmanagementsystem.controller.press;

import com.lyun.bookrentalmanagementsystem.service.PressService;
import com.lyun.bookrentalmanagementsystem.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/press")
@RestController
public class PressApi {


    @Autowired
    PressService pressService;

    @RequestMapping("/all")
    public Object test(){
        return new ResultBody<>(true,200,pressService.findAll());
    }

}
