package com.example.aga.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Configuration
public class LoginHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("userName");
        System.out.println("user" + loginUser);
        Object loginMember = request.getSession().getAttribute("memberName");
        System.out.println("member" + loginMember);
        String way = request.getServletPath().replace(".html","");


        if (loginUser == null && loginMember == null ) {
            response.sendRedirect("/ygwg/log.html");
            return false;
        } else{
            if("/userspace".equals(way) && loginUser ==null){
                response.sendRedirect("/ygwg/memberspace.html");
                return false;
            }
            if("/memberspace".equals(way) && loginMember == null){
                response.sendRedirect("/ygwg/userspace.html");
                return false;
            }
            if("/log".equals(way)){
                if(loginUser!=null){
                    response.sendRedirect("/ygwg/userspace.html");
                    return false;
                }
                else{
                    response.sendRedirect("/ygwg/memberspace.html");
                    return false;
                }
            }
        }
            return true;



    }
}

