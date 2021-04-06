package com.example.aga.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Document
@Data
public class VerificationCode {


    @Field("_id")
    String id;

    String email;
    String code;
    Date date;

}
