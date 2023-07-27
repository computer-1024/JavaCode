package com.citicbank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {

    public void readCSVFile(String file) throws IOException {

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(file)), "GBK"))) {
            String line;
            boolean skipFirstLine = true; // 设置为true表示跳过第一行
            // 逐行读取CSV文件内容
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue; // 跳过第一行
                }
                // 使用逗号分隔符解析CSV行
                String[] data = line.split(",");

                Check_Base_Rule check_rule = new Check_Base_Rule();
                check_rule.setArray_data(data);
                System.out.println(check_rule.rst_print());


            }
        }
    }
}
