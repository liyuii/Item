package com.test.util;

public class FileUtil {

    public static final String PATH = "D:/local/";

    /**
     * 获取磁盘存储路径
     * @param fileId
     * @param fileName
     * @return
     */
    public static String getPath(String fileId,String fileName){
        return PATH + fileId + fileName;
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public static String getExt(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
