package com.example.aga.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogOutController {

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
