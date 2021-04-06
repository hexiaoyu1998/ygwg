package com.example.aga.controller;


import com.example.aga.dao.UserDaoImpl;
import com.example.aga.dao.resetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class LoginFlowController {

@Autowired
UserDaoImpl userDao;

@Autowired
resetPassword resetPassword;


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
//
//    @RequestBody
//    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)

    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPass(@RequestParam String email,
                                @RequestParam String code,
                                @RequestParam String newPass) {

        return resetPassword.resetPassword(email,code,newPass);

    }

    @ResponseBody
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public String sendCode(@RequestParam String email) {
        String result = resetPassword.sendCode(email);
        return result;
    }



}
