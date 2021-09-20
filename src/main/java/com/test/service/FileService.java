package com.test.service;

import com.test.common.PageReslt;
import com.test.common.Result;
import com.test.entity.File;
import com.test.entity.Form;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    File upload(MultipartFile file);

    void download(String fileId);

    List<File> getFilePage(String fileIds);
}
