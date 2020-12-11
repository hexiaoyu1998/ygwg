package com.example.aga.dao;

import com.example.aga.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Object isValidate(HttpServletRequest request) {
//        登陆
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        //先用userName找
        Query query_name = new Query(Criteria.where("userName").is(userAccount));
        UserEntity user_name = mongoTemplate.findOne(query_name, UserEntity.class);
        if(user_name==null)  //用name找不到
        {
            //用email找
            Query query_email = new Query(Criteria.where("userEmail").is(userAccount));
            UserEntity user_email = mongoTemplate.findOne(query_email, UserEntity.class);

            if(user_email==null) {  //用email找不到
                return "Please register your account!";
            }
            else{   //用email找到了
                //密码正确
                if(user_email.getUserPassword().equals(userPassword)){
                    user_email.setUserPassword("");
//                    if(user_email.getMemberId()!=null){
//                        return "member";
//                    }
//                    else {
//                        return "user";
//                    }

                    return user_email;
                }
                else{
                    //密码不正确
                    return "Your account or password is wrong!";
                }
            }
        }
        else{
            if(user_name.getUserPassword().equals(userPassword))
            {
                //密码正确
                user_name.setUserPassword("");
//                if(user_name.getMemberId()!=null){
//                    return "member";
//                }
//                else {
//                    return"user";
//                }
                return user_name;
            }
            else{
                //密码不正确
                return "Your account or password is wrong!";
            }
        }



    }

    @Override
    public Object register(HttpServletRequest request) {
//        注册

        //注册不可重复邮箱,不可重复姓名
        String userName=request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

//        Criteria criteria=new Criteria();
//        criteria.and("userName").is(userName);
//        criteria.and("userEmail").is(userEmail);
//        Query query = new Query(criteria);
        Query query_email = new Query(Criteria.where("userEmail").is(userEmail));
        UserEntity user_email = mongoTemplate.findOne(query_email, UserEntity.class);

        Query query_name = new Query(Criteria.where("userName").is(userName));
        UserEntity user_name = mongoTemplate.findOne(query_name, UserEntity.class);

        if (user_email == null&&user_name==null){
            //用户未注册
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);  //姓名
            userEntity.setUserEmail(userEmail);    //邮箱地址
            userEntity.setUserPassword(userPassword);  //密码
//            userEntity.setIsMember("Unregistered member");           //是否是会员
//            userEntity.setRegisterTime("Unregistered member");       //会员注册时间
//            userEntity.setOverDate("Unregistered member");         //会员过期时间为空
//            userEntity.setIsoverDate("Unregistered member"); //会员是否过期

//            Date date =new Date();
//            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            userEntity.setRegisterTime(simpleDateFormat.format(date));
//            userEntity.setOverDate(simpleDateFormat.format(date.getTime()+ 365 * 24 * 60 * 60 * 1000L));
//            userEntity.setOverDue(false);

            mongoTemplate.save(userEntity);
            return "Registered successfully!";
        }
        return "User already exists!";
    }




}
