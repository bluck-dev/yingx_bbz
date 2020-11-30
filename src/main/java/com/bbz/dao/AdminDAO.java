package com.bbz.dao;

import com.bbz.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDAO extends Mapper<Admin> {
        Admin login(String name);
}
