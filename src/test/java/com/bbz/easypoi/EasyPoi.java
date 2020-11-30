package com.bbz.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bbz.YingxBbzApplication;
import com.bbz.dao.UserDAO;
import com.bbz.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = YingxBbzApplication.class)
@RunWith(SpringRunner.class)
public class EasyPoi {
    private static final Logger log = LoggerFactory.getLogger(EasyPoi.class);
    @Resource
    private UserDAO userDAO;
    @Test
    public void Poiexport(){
        List<User> users = userDAO.selectAll();
        //导出设置的参数  参数:大标题,工作表名
        ExportParams exportParams = new ExportParams("YXAPP用户","用户");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("E:\\YingxUser.xls")));
            log.debug("导出成功!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
