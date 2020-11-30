package com.bbz.service;

import com.bbz.entity.Log;

import java.util.List;

public interface LogService {
    List<Log> selectPageService(Integer pageNow, Integer size);

    Integer count();
}
