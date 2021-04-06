package com.example.aga.dao;

import com.example.aga.entity.VerificationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Xiaoyu He
 * @Date: 2021/04/01/19:58
 * @Description:
 */
public interface VerificationCodeDao extends MongoRepository<VerificationCode,String> {
    VerificationCode findFirstByEmailAndCode(String email,String code);
    VerificationCode findFirstByEmail(String email);
}
