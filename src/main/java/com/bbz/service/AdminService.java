package com.bbz.service;

import com.bbz.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {
    HashMap<String,Object> login(Admin admin, String code, HttpServletRequest request);

}
