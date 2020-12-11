package com.example.aga.dao;

import javax.servlet.http.HttpServletRequest;

public interface UserDao {
    Object isValidate(HttpServletRequest request);
    Object register(HttpServletRequest request);
}
