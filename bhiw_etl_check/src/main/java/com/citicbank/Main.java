package com.citicbank;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String csvFile = "D:\\document\\SCRIPT_LIST.csv";

        //检查文件是否存在
        CommonFunction CommonFunction = new CommonFunction();

        if(CommonFunction.isFileExists(csvFile))
        {
            CSVReader CSVReader = new CSVReader();
            CSVReader.setConfigPath(new File(csvFile).getParent());
            CSVReader.setConfigFile(new File(csvFile).getName());
            CSVReader.readCSVFile();
        }
        else
        {
            System.out.println("\""+csvFile+"\" 文件不存在,请检查");
        }

    }



}