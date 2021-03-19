package com.yasso.dfbb.utils;

import java.io.File;

/**
 * @author guoc
 * @version 1.0
 * @date 2021/2/23 16:27
 */
public class FileUtil {

    //
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean delete = file.delete();
            System.out.println(delete);
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\guochuang\\Desktop\\kkk\\精通正则表达式.pdf";
        deleteFile(filePath);
    }
}
