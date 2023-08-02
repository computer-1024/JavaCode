package com.citicbank;

import java.io.File;

public class CommonFunction {

    private String configfile;
    private String entity;
    private String ddl;
    private String gsq;
    private String excel;



    public CommonFunction() {
    }

    public  boolean isFileExists(String csvFile) {
        File file = new File(csvFile);
        return file.exists();
    }


}
