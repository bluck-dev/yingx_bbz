package com.bbz.serviceimp;

import com.bbz.annotcation.AddLog;
import com.bbz.dao.CateGoryDAO;
import com.bbz.entity.CateGory;
import com.bbz.po.CateGoryPO;
import com.bbz.service.CateGoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CateGoryServiceImp implements CateGoryService {
    @Resource
    private CateGoryDAO cateGoryDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
//    @AddCache("添加缓存")
    public List<CateGory> selectA(Integer pageNow,Integer size) {           //一级分页查
        Integer begin=(pageNow-1)*size;
        Integer end=pageNow*size;
        List<CateGory> cateGories = cateGoryDAO.selectA(begin, end);
        return cateGories;
    }

    @Override
//    @AddCache("添加缓存")
    public Integer countSetvice() {
        return cateGoryDAO.count();
    }           //查一级总行数

    @AddLog("添加类别")
    @Override
    public void add(CateGory cateGory) {                //添加
        //判断添加的一级还是二级
        if (cateGory.getParentId()==null){
            cateGory.setId(UUID.randomUUID().toString());
            cateGory.setLevels(1);
            cateGoryDAO.insertSelective(cateGory);
        }else {
            cateGory.setId(UUID.randomUUID().toString());
            cateGory.setLevels(2);
            cateGoryDAO.insertSelective(cateGory);
        }

    }

    @Override
//    @DelCache("删除缓存")
    @AddLog("修改类别")
    public void update(CateGory cateGory) {                //修改
        //判断修改的一级还是二级
        if (cateGory.getParentId()==null){
            cateGoryDAO.updateByPrimaryKeySelective(cateGory);
        }else {
            cateGoryDAO.updateByPrimaryKeySelective(cateGory);
        }

    }

    @Override
//    @AddCache("添加缓存")
    public List<CateGory> selectB(String id, Integer pageNow, Integer size) {           //二级分页查
        Integer begin=(pageNow-1)*size;
        Integer end=pageNow*size;
        return cateGoryDAO.selectB(id,begin,end);
    }

    @Override
//    @AddCache("添加缓存")
    public Integer countB(String id) {                                     //查二级总行数
        return cateGoryDAO.countB(id);
    }//查二级类别数量

    @Override
    public List<CateGory> selectOnelevels() {                                   //查所有一级类别
        return cateGoryDAO.selectOnelevels();
    }//查所有一级

    @Override
    public List<CateGory> selectTwolevels() {
        return cateGoryDAO.selectTwolevels();
    }

    @Override
    @AddLog("删除类别")
//    @DelCache("删除缓存")
    public String delete(CateGory cateGory) {                   //删除
        String message=null;
        Integer count = cateGoryDAO.countB(cateGory.getId());
        if(count==0) {
            cateGoryDAO.de(cateGory.getId());
            message = "删除成功!";
        }else if (count!=0){
            message = "该类别下有二级,无法删除!";
        }

        return message;
    }

    @Override
    public List<CateGoryPO> queryAllCategory() {        //前台展示所有类别
        return cateGoryDAO.queryAllCategory();
    }
}
