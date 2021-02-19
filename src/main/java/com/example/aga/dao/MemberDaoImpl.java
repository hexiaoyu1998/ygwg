package com.example.aga.dao;

import com.example.aga.entity.MemberEntity;
import com.example.aga.entity.UserEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Object findandsetId(HttpServletRequest request) {
        String memberId=request.getParameter("memberId");
        Query query = new Query(Criteria.where("memberId").is(memberId));
        MemberEntity member = mongoTemplate.findOne(query, MemberEntity.class);
        if(member==null){
            return "The member ID does not exist!";
        }
        else{
            HttpSession session= request.getSession();
            String userName= (String) session.getAttribute("userName");
            Query query1=new Query(Criteria.where("userName").is(userName));
            Update update=new Update();
            update.set("memberId",memberId);
            mongoTemplate.updateFirst(query1,update,UserEntity.class);
            return "done";
        }
    }

    @Override
    public Object findById(HttpServletRequest request) {
        String memberId=request.getParameter("memberId");
        if(memberId!=null)
        {
            Query query=new Query(Criteria.where("_id").is(memberId));
            return mongoTemplate.findOne(query,MemberEntity.class);
        }
        return null;
    }

    @Override
    public Object findByMemberId(HttpServletRequest request){

        String memberId=request.getParameter("memberId");
        if(memberId!=null)
        {
            Query query=new Query(Criteria.where("memberId").is(memberId));
            return mongoTemplate.findOne(query,MemberEntity.class);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public Object reNew(HttpServletRequest request) {
        String memberId= (String) request.getSession().getAttribute("memberId");
        Query query = new Query(Criteria.where("memberId").is(memberId));
        MemberEntity memberEntity=mongoTemplate.findOne(query,MemberEntity.class);


        //进行renew
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

            Date dueDate=simpleDateFormat.parse(memberEntity.getDueTime());
            String now= simpleDateFormat.format(new Date());
            Date nowDate=simpleDateFormat.parse(now);

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(nowDate);


            calendar.add(Calendar.MONTH,1);
            nowDate= simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));

            if(dueDate.after(nowDate)){
                return "Please renew your membership one month before it expires.";
            }
            else{

                calendar.setTime(dueDate);
                calendar.add(Calendar.YEAR,2);
                String dueTime= simpleDateFormat.format(calendar.getTime());
                request.getSession().setAttribute("dueTime",dueTime);

                query= Query.query(Criteria.where("_id").is(memberId));

                Update update=new Update();
                update.set("dueTime",dueTime);
                mongoTemplate.updateFirst(query,update,MemberEntity.class);
                return "successfully renew!";
            }
    }
}
