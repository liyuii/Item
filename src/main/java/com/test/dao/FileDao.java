package com.test.dao;

import com.test.entity.File;
import com.test.entity.Form;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileDao {

    /**
     * 保存附件
     *
     * @return
     */
    void saveFile(File file);

    /**
     * 删除file
     *
     * @return
     */
    void deleteFile(String id);

    /**
     * 根据id获取table
     *
     * @return
     */
    File getFileByFormId(String fid);

//    void upload(File file);

    File getFileById(String fileId);

    void updateFile(File file);
}
