package com.citicbank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {

    private  String configPath;
    private  String ConfigFile;
    public CSVReader() {

    }
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setConfigFile(String configFile) {
        ConfigFile = configFile;
    }

    public void readCSVFile() throws IOException {

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(this.configPath+"\\"+ this.ConfigFile)), "GBK"))) {
            String line;
            boolean skipFirstLine = true; // 设置为true表示跳过第一行
            // 逐行读取CSV文件内容
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue; // 跳过第一行
                }
                // 使用逗号分隔符解析CSV行
                String[] data = line.split(",",-1);

                //检查基本的规则
                Check_Base_Rule check_base_rule = new Check_Base_Rule();
                check_base_rule.setArray_data(data);

                if( check_base_rule.base_check() )
                {
                    System.out.println(check_base_rule.base_rst_print());
                    //检查流程性规则
                    Check_Process_Rule check_process_rule = new Check_Process_Rule();
                    check_process_rule.setArray_data(data);
                    check_process_rule.setConfigPath(this.configPath);

                    if(check_process_rule.process_check())
                    {
                        System.out.println("1");
                    }
                    else
                    {
                        System.out.println(check_process_rule.process_rst_print());
                        break;
                    }

                }
                else
                {
                    System.out.println(check_base_rule.base_rst_print());

                }

            }
        }
    }
}
