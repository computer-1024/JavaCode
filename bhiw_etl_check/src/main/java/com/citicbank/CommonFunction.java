package com.citicbank;

import java.io.File;

public class CommonFunction {


    //检查文件是否存在
    public static  boolean isFileExists(String csvFile) {
        File file = new File(csvFile);
        return file.exists();
    }

    //统计目录下的文件个数
    public static int countFilesInDirectory(String directoryPath) {

        File directory = new File(directoryPath);
        int fileCount = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileCount++;
                } else if (file.isDirectory()) {
                    fileCount += countFilesInDirectory(file.getAbsolutePath());
                }
            }
        }
        return fileCount;
    }

    //检查对应文件是否是UTF-8格式

    //检查代码文件的换行符是否是UNIX(LF)而不是WINDOWS(CR LF)


}
