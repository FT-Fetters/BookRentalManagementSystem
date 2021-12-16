package com.lyun.bookrentalmanagementsystem.controller.press;

import com.lyun.bookrentalmanagementsystem.entity.Press;
import com.lyun.bookrentalmanagementsystem.service.PressService;
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

@RequestMapping("/press")
@RestController
public class PressApi {

    @Autowired
    PressService pressService;

    @RequestMapping("/all")
    public Object test(){
        return new ResultBody<>(true,200,pressService.findAll());
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public ResultBody<?> get(@RequestParam String name,
                             HttpServletRequest requese,
                             HttpServletResponse response){
        List<Press> pressList = pressService.get(name);
        return new ResultBody<>(true,200,pressList);

    }

}
