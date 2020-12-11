package com.example.aga.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser=request.getSession().getAttribute("userName");
        System.out.println("user"+loginUser);
        Object loginMember=request.getSession().getAttribute("memberName");
        System.out.println("member"+loginMember);

        if(loginUser==""||loginMember=="")
        {
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        else
        {
            return true;
        }

    }
}

