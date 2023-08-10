package com.citicbank;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
    public static boolean isUTF8WithoutBOMCodeFile(String filename) {

        File file = new File(filename);
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            // Check for UTF-8 BOM (Byte Order Mark)
            byte[] bom = new byte[3];
            if (inputStream.markSupported()) {
                inputStream.mark(3);
                if (inputStream.read(bom) == 3 && bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF) {
                    System.out.println("代码脚本不是UTF-8格式");
                    return false; // File has UTF-8 BOM
                }
                inputStream.reset();
            }

            // Read and decode the content to check for valid UTF-8
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!isValidUTF8String(line)) {
                    System.out.println("代码脚本不是UTF-8格式");
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean isValidUTF8String(String str) {
        try {
            new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            return true;
        } catch (Exception e) {
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
                        System.out.println("代码脚本存在Windows CRLF换行符");
                        return false;
                    }

                }

            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
