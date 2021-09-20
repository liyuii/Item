package com.test.dao;

import com.test.entity.File;
import com.test.entity.Form;

import java.util.List;

public interface TableDao {

    /**
     * 获取table列表
     *
     * @return
     */
    public List<Form> tablePage();


    /**
     * 添加table
     *
     * @return
     */
    public void addTable(Form form);


    /**
     * 修改table列表
     *
     * @return
     */
    public void updateTable(Form form);


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
    public Form getTableById(String fid);

}
