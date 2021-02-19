package com.example.aga.dao;

import javax.servlet.http.HttpServletRequest;

public interface MemberDao {
    Object findandsetId(HttpServletRequest request);
    Object findById(HttpServletRequest request);
    Object reNew(HttpServletRequest request);
    Object findByMemberId(HttpServletRequest request);

}
