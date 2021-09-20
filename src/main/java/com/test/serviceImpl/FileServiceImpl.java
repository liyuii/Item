package com.test.serviceImpl;

import com.test.common.Result;
import com.test.common.StateVlaue;
import com.test.dao.FileDao;
import com.test.entity.File;
import com.test.entity.Form;
import com.test.service.FileService;
import com.test.util.FileUtil;
import com.test.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    /**
     *上传文件
     * @param file
     */
    @Override
    @Transactional
    public File upload(MultipartFile file) {

        File realFile = new File();
        if(!StringUtils.isEmpty(file.getOriginalFilename())){
            realFile.setFileId(UUIDUtil.generateUuid());
            realFile.setFilePath(FileUtil.getPath(realFile.getFileId(),file.getOriginalFilename()));
            realFile.setFileName(file.getOriginalFilename());
            realFile.setFileExt(FileUtil.getExt(file.getOriginalFilename()));
            realFile.setFileSize(file.getSize());

            fileDao.saveFile(realFile);

            java.io.File file1 = new java.io.File(realFile.getFilePath());
            try {
                if(!file1.getParentFile().exists()){
                    file1.getParentFile().mkdirs();
                }
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return realFile;
    }

    /**
     *
     * @param fileId
     */
    @Override
    public void download(String fileId) {

    }

    @Override
    @Transactional
    public List<File> getFilePage(String fileIds) {

        List<File> list = new ArrayList<>();

        if(!StringUtils.isEmpty(fileIds)){
            String[] ids = fileIds.split(",");
            for(String fileId: ids){
                File file = fileDao.getFileById(fileId);
                list.add(file);
            }
        }
        return list;
    }
}
