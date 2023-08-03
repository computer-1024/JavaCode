package com.citicbank;

import java.io.File;

public class CommonFunction {



    public static  boolean isFileExists(String csvFile) {
        File file = new File(csvFile);
        return file.exists();
    }

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

}
