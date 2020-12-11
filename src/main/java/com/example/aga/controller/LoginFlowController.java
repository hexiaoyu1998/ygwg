package com.example.aga.controller;


import com.example.aga.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class LoginFlowController {

@Autowired
UserDaoImpl userDao;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object log_in(HttpServletRequest request)
    {
        return userDao.isValidate(request);
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object register(HttpServletRequest request){
        return userDao.register(request);
    }
}
