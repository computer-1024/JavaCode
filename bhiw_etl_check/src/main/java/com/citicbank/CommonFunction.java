package com.citicbank;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.mozilla.universalchardet.UniversalDetector;

import javax.smartcardio.CommandAPDU;

public class CommonFunction {


    //检查文件是否存在
    public static boolean isFileExists(String csvFile) {
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
    public static boolean isUTF8WithoutBOMCodeFile(String filename)  {

        File file = new File(filename);

            try(BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
                // Check for UTF-8 BOM (Byte Order Mark)
                byte[] bom = new byte[3];
                if (inputStream.markSupported()) {
                    inputStream.mark(3);
                    if (inputStream.read(bom) == 3 && bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF) {
                        System.out.print(file.getName()+"的字符集为:"+"UTF-8-BOM.");
                        return false; // File has UTF-8 BOM
                    }
                }
            }
            catch (Exception e) {
                return false;
            }


            try{
                byte[] bytes = FileUtils.readFileToByteArray(file);
                UniversalDetector detector = new UniversalDetector(null);
                detector.handleData(bytes, 0, bytes.length);
                detector.dataEnd();
                System.out.print(file.getName()+"的字符集为:"+detector.getDetectedCharset()+".");
                return "UTF-8".equalsIgnoreCase(detector.getDetectedCharset()) ||(file.getName().equals("running-job.conf") && "US-ASCII".equalsIgnoreCase(detector.getDetectedCharset())) ;
            }catch (Exception e) {
                return false;
            }
    }


    //检查代码文件的换行符是否是UNIX(LF)而不是WINDOWS(CR LF)
    public static boolean hasUnixNewlines(String fileName) {

        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath())))) {
            int character;
            while ((character = reader.read()) != -1) {

                String currentcharacter=String.valueOf((char) character);

                if (currentcharacter.equals("\r"))
                {
                    int nextcharacter=reader.read();

                    if (String.valueOf((char) nextcharacter).equals("\n"))
                    {
                        System.out.print(file.getName()+"代码脚本是Windows CRLF换行符.");
                        return false;
                    }

                }

            }
            System.out.print(file.getName()+"代码脚本存是Unix LF换行符.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /* 判断是否在running-job.conf文件清单里*/
    public static boolean hasRunningConf(String fileName,String objName,String rerunningType)  {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {

                if (rerunningType.equalsIgnoreCase("T")
                        && objName.substring(0, 5).equalsIgnoreCase("bhif_")
                        && line.split("\\|", -1)[0].equalsIgnoreCase("EDW-BHIF-T_" + objName)) {
                    return true;
                }

                if (rerunningType.equalsIgnoreCase("V")
                        && objName.substring(0, 5).equalsIgnoreCase("bhif_")
                        && line.split("\\|", -1)[0].equalsIgnoreCase("EDW-BHIF-" + objName + "-EXP")) {
                    return true;
                }

            }
            System.out.println("重跑表/视图作业未配置running-job.conf配置文件，请检查.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /* 判断 通过使用正则表达式查看在某文件是否存在某字符串 */
    public static boolean hasContainString(String ScriptFileName,String StringName ){

        String regex="^(sh){1}\\s{1,}(\\$ETLPLUS_HOME/script/autoImport/jobExportOper.sh){1}\\s{1,}(TableList/"+StringName+"/"+StringName+".xls){1}\\s{1,}(0){1}\\s{1,}(NEDW){1}\\s{1,}.*";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(ScriptFileName)), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
               if (Pattern.matches(regex, line))
               {
                   return true;
               }

            }
            System.out.print("实体表/视图表未配置修改字符集的命令,请检查.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}
