package com.test.service;

import com.github.pagehelper.PageInfo;
import com.test.common.PageReslt;
import com.test.entity.Form;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TableService {

//    /**
//     * 获取table列表
//     *
//     * @return
//     */
//    public List<Form> tablePage();


    /**
     * 获取table列表
     *
     * @return
     */
    public PageReslt<Form> tablePage(int page, int rows);


    /**
     * 添加table
     *
     * @return
     */
//    public void addTable(Form form,MultipartFile myFile);
    public void addTable(Form form,String files);


    /**
     * 修改table列表
     *
     * @return
     */
    public void updateTable(Form form,MultipartFile myFile);


    /**
     * 删除table
     *
     * @return
     */
    public void deleteTable(String id);


    /**
     * 根据id获取table
     *
     * @return
     */
    public Form getTableById(String id);

}
