package com.bbz.serviceimp;

import com.bbz.dao.LogDAO;
import com.bbz.entity.Log;
import com.bbz.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LogServiceImp implements LogService {
    @Resource
    private LogDAO logDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Log> selectPageService(Integer pageNow, Integer size) {     //分页查询
        Integer begin=(pageNow-1)*size;
        Integer end=pageNow*size;
        return logDAO.selectPage(begin,end);
    }

    @Override
    public Integer count() {
        return logDAO.count();
    }
}
