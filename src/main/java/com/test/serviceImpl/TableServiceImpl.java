package com.test.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.common.PageReslt;
import com.test.common.Result;
import com.test.dao.FileDao;
import com.test.dao.TableDao;
import com.test.entity.File;
import com.test.entity.Form;
import com.test.enums.BidNameEnum;
import com.test.enums.PublicityTypeEnum;
import com.test.service.TableService;
import com.test.util.FileUtil;
import com.test.util.UUIDUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl implements TableService {

//    private static final String PATH = "D:/local/";

    @Autowired
    TableDao tableDao;

    @Autowired
    FileDao fileDao;

    @Override
    public PageReslt<Form> tablePage(int page, int rows) {

        PageReslt<Form> result = new PageReslt<>();

        PageHelper.startPage(page, rows);
        List<Form> forms = tableDao.tablePage();
        PageInfo<Form> pageInfo = new PageInfo<Form>(forms);

        for(Form form:forms){
            if(!StringUtils.isEmpty(form.getPublicityType())){
                form.setPublicityType(PublicityTypeEnum.getValue(form.getPublicityType()));
            }

            if(!StringUtils.isEmpty(form.getBidName())) {
                String names = form.getBidName();
                String[] splits = names.split(",");
                StringBuilder build = new StringBuilder();
                for (String split : splits) {
                    build.append(BidNameEnum.getValue(split)).append(",");
                }
                form.setBidName(build.deleteCharAt(build.length() - 1).toString());
            }
        }
        return result.setRows(forms).setTotal(pageInfo.getTotal());
    }

    @Override
    @Transactional
    public void addTable(Form form1, MultipartFile myFile) {

        Form form = fixDate(form1);

        //1.设置属性
        form.setFormId(UUIDUtil.generateUuid());

        //2.保存文件搭配数据库
        if(!StringUtils.isEmpty(myFile.getOriginalFilename())){
            File file = new File();
            file.setFileId(UUIDUtil.generateUuid());
//        file.setFileName(file.getFileId() + myFile.getOriginalFilename());
//        file.setFilePath(PATH + file.getFileName());
            file.setFileName(myFile.getOriginalFilename());
            file.setFilePath(FileUtil.getPath(file.getFileId(),file.getFileName()));

            file.setFormId(form.getFormId());
            fileDao.saveFile(file);

            //3.在表单中加入文件名
//            form.setFormFile(file.getFileName());

            //4.存储文件到磁盘
            java.io.File file1 = new java.io.File(file.getFilePath());
            try {
                if(!file1.getParentFile().exists()){
                    file1.getParentFile().mkdirs();
                }
                myFile.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tableDao.addTable(form);
    }

    @Override
    @Transactional
    public void updateTable(Form form1,MultipartFile myFile) {

        Form form = fixDate(form1);

        System.out.println("更新");

        //1.更新form

        System.out.println(myFile.getOriginalFilename());
        if(!StringUtils.isEmpty(myFile.getOriginalFilename())){

            //2.更新file，先删再增
            File fileByFormId = fileDao.getFileByFormId(form.getFormId());
            if(!StringUtils.isEmpty(fileByFormId)){
                fileDao.deleteFile(fileByFormId.getFileId());
                java.io.File oldFile = new java.io.File(fileByFormId.getFilePath());
                if(oldFile.exists()){
                    oldFile.delete();
                }
            }

            File file = new File();
            file.setFileId(UUIDUtil.generateUuid());
            file.setFileName(myFile.getOriginalFilename());
            file.setFilePath(FileUtil.getPath(file.getFileId(),file.getFileName()));
            file.setFormId(form.getFormId());
            fileDao.saveFile(file);
            //获取表单的附件名
//            form.setFormFile(file.getFileName());

            //3.存储文件到磁盘
            java.io.File file1 = new java.io.File(file.getFilePath());
            try {
                if(!file1.getParentFile().exists()){
                    file1.getParentFile().mkdirs();
                }
                myFile.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tableDao.updateTable(form);
    }

    @Override
    @Transactional
    public void deleteTable(String id) {

        String[] ids = id.split(",");
        for (String formId:ids){
            tableDao.deleteTable(formId);
            File formFile = fileDao.getFileByFormId(formId);
            if(!StringUtils.isEmpty(formFile)){
                fileDao.deleteFile(formFile.getFileId());
                java.io.File file = new java.io.File(formFile.getFilePath());
                if(file.isFile())
                    file.delete();
            }
        }
    }

    @Override
    public Form getTableById(String fid) {
//        Form temp = tableDao.getTableById(fid);
//        temp.setPublicityType(PublicityTypeEnum.getValue(temp.getPublicityType()));
//
//        String names = temp.getBidName();
//        String[] splits = names.split(",");
//        StringBuilder build = new StringBuilder();
//        for (String split:splits){
//            build.append(BidNameEnum.getType(temp.getBidName())).append(",");
//        }
//        temp.setBidName(build.deleteCharAt(build.length()-1).toString());
        return tableDao.getTableById(fid);
    }

    /**
     * 将表单对象传入该方法，使用simpleDateFormat对时间进行格式化
     *
     * @return
     */
    private Form fixDate(Form form){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(!StringUtils.isEmpty(form.getOpenTimeStr())) {
                Date parse1 = dateFormat.parse(form.getOpenTimeStr());
                form.setBidOpenTime(parse1);
            }
            if(!StringUtils.isEmpty(form.getStartTimeStr())){
                Date parse2 = dateFormat.parse(form.getStartTimeStr());
                form.setPublicityStartTime(parse2);
            }

            if(!StringUtils.isEmpty(form.getEndTimeStr())) {
                Date parse3 = dateFormat.parse(form.getEndTimeStr());
                form.setPublicityEndTime(parse3);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return form;
    }
}
