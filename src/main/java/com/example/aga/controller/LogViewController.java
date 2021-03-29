package com.example.aga.controller;

import com.example.aga.dao.MemberDao;
import com.example.aga.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Xiaoyu He
 * @Date: 2021/03/25/21:05
 * @Description:
 */

@Controller
public class LogViewController {
    @Autowired
    UserDao userDao;

    @Autowired
    MemberDao memberDao;
    @RequestMapping(value = {"/log", "/log.html"},method = RequestMethod.GET)
    public ModelAndView LogViewControl(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        Object loginUser = request.getSession().getAttribute("userName");
        System.out.println("user" + loginUser);
        Object loginMember = request.getSession().getAttribute("memberName");
        System.out.println("member" + loginMember);

        if(loginUser==null && loginMember==null){
            modelAndView.setViewName("log");
        }
        else{
            if(loginUser!=null)
                response.sendRedirect("/ygwg/userspace.html");
            else
                response.sendRedirect("/ygwg/memberspace.html");
        }

        return modelAndView;

    }


}
