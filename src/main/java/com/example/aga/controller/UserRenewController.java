package com.example.aga.controller;


import com.example.aga.dao.MemberDaoImpl;

import com.example.aga.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/member")
public class UserRenewController {

    @Autowired
    MemberDaoImpl memberDao;

    @ResponseBody
    @RequestMapping(value = "/updateid",method = RequestMethod.POST)
    public Object updateId(HttpServletRequest request){
        return memberDao.findandsetId(request);
    }

    @ResponseBody
    @RequestMapping(value = "/renew",method = RequestMethod.POST)
    public Object renew(HttpServletRequest request){
        return memberDao.reNew(request);
    }
}
