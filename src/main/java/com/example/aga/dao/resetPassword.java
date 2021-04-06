package com.example.aga.dao;


import com.example.aga.entity.UserEntity;
import com.example.aga.entity.VerificationCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Xiaoyu He
 * @Date: 2021/04/01/19:48
 * @Description:
 */
@Component
public class resetPassword {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    VerificationCodeDao verificationCodeDao;

    @Autowired
    JavaMailSender mailSender;


//
    @Value("${spring.mail.username}")
    String mailAddress;

    @Value("${spring.mail.host}")
    String ALIDM_SMTP_HOST;

    @Value("${spring.mail.password}")
    String password;

    @Value("javax.net.ssl.SSLSocketFactory")
    String socketFactoryclass;

    @Value("${spring.mail.port}")
    String port;



    public String sendCode(String email) {
        //首先验证
        try {
            Random random = new Random();
            Query query = new Query(Criteria.where("userEmail").is(email));
            UserEntity userEntity = mongoTemplate.findOne(query, UserEntity.class);
            if (userEntity != null) {
                String code = String.valueOf(random.nextInt(900000) + 100000);
                VerificationCode verificationCode = new VerificationCode();
                verificationCode.setEmail(email);
                verificationCode.setCode(code);
                verificationCode.setDate(new Date());
                VerificationCode findVerificationCode = verificationCodeDao.findFirstByEmail(email);
                if(findVerificationCode==null){
                    verificationCodeDao.save(verificationCode);
                }
                else {
                    Query query1 = new Query(Criteria.where("email").is(email));
                    Update update = new Update();
                    update.set("code",code);
                    update.set("Date",new Date());
                    mongoTemplate.updateFirst(query1,update,VerificationCode.class);
                }
                //将验证码发送给用户
                //1. 构建邮件内容
                String subject = "YGWG of AGA Verification Code";
                String content = "Hello " + userEntity.getUserName() + ":<br/>" +
                        "You are trying to reset your password, the verification code is <b>" + code + "</b>. Please use this code in 30 minutes .<br/>" +
                        "Welcome to <a href='http://www.aga-ygwg.com/' target='_blank'>YGWG of AGA</a> !";
                //2.发送邮件
                Boolean flag = sendEmail(email, subject, content);
                if (flag) {

                    return "suc";
                } else {
                    return "Email failed to send.";
                }
                }
            else {
                    return "no user";
                }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Boolean sendEmail(String to, String subject, String content) {
        final Log logger = LogFactory.getLog(getClass());
//
//        String ALIDM_SMTP_HOST = "smtp.163.com";
//        String socketFactoryclass = "javax.net.ssl.SSLSocketFactory";
//        String port = "465";
//        String password = "JWQIDVJAMJJGQXLJ";
//        String mailAddress = "xiaoyuhe1121@163.com";



        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);

        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
         props.put("mail.smtp.socketFactory.class", socketFactoryclass);
         props.put("mail.smtp.socketFactory.port", port);
         props.put("mail.smtp.port", port);

        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user", mailAddress);
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", password);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);

        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession){

        };
        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(mailAddress, subject);
            message.setFrom(from);
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(MimeMessage.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
            return true;
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            logger.warn(err);
            System.out.println(err);
            return false;
        }

    }
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            InternetAddress internetAddress = new InternetAddress(mailAddress, "YGWG of AGA", "UTF-8");
//            helper.setFrom(internetAddress);
//            helper.setTo(to);
//            helper.setCc(mailAddress);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//            mailSender.send(mimeMessage);
//           return true;
//        } catch (Exception e) {
////            e.printStackTrace();
//            logger.warn(e.toString());
//            System.out.println(e.toString());
//            return false;
//        }

//    }

    public String resetPassword(String email,String code,String newPass){

        Query query = new Query(Criteria.where("userEmail").is(email));
        UserEntity userEntity = mongoTemplate.findOne(query, UserEntity.class);
        if(userEntity!=null){
            VerificationCode verificationCode = verificationCodeDao.findFirstByEmailAndCode(email,code);
            if(verificationCode==null){
                return "error!";
            }
            else{
                userEntity.setUserPassword(newPass);
                Update update = new Update();
                update.set("userPassword",newPass);

                Query query1 = new Query(Criteria.where("email").is(email));
                mongoTemplate.remove(query1,VerificationCode.class);

                mongoTemplate.updateFirst(query,update,UserEntity.class);
                return "suc";
            }
        }
        return "error!";


    }

}
