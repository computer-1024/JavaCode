package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        File myFile = new File("D:\\Spring\\check_etlplus\\test.xlsx");

        FileInputStream fis = new FileInputStream(myFile);

        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        if (myWorkBook.getSheetIndex("BHIW仓内作业整理")!=-1)
        {
            myWorkBook.removeSheetAt(myWorkBook.getSheetIndex("BHIW仓内作业整理"));
        }

        int rowcount=0;
        myWorkBook.createSheet(WorkbookUtil.createSafeSheetName("BHIW仓内作业整理"));
        myWorkBook.getSheet("BHIW仓内作业整理");
        myWorkBook.getSheet("BHIW仓内作业整理").createRow(rowcount);
        myWorkBook.getSheet("BHIW仓内作业整理").getRow(rowcount).createCell(0).setCellValue("BHIW仓内作业");
        myWorkBook.getSheet("BHIW仓内作业整理").getRow(rowcount).createCell(1).setCellValue("触发作业");
        myWorkBook.getSheet("BHIW仓内作业整理").getRow(rowcount).createCell(2).setCellValue("依赖作业");

        String Etl_Job_Name = "A";

        myWorkBook.getSheet("BHIW仓内作业整理").createRow(++rowcount);
        myWorkBook.getSheet("BHIW仓内作业整理").getRow(rowcount).createCell(0).setCellValue(Etl_Job_Name);

        StringBuilder trigger_rst = new StringBuilder();
        StringBuilder relyon_rst= new StringBuilder();

        //查询触发依赖
        XSSFSheet tmp_sheet = myWorkBook.getSheet("dependence");

        for (Row tmp_row : tmp_sheet) {
            if (tmp_row.getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim().toUpperCase().equals(Etl_Job_Name)) {

                if (tmp_row.getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim().toUpperCase().length() > 0) {
                    trigger_rst.append(Etl_Job_Name.trim().toUpperCase()).append("->").append(tmp_row.getCell(3).toString().trim().toUpperCase()).append(";");
                }
                if (tmp_row.getCell(1,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim().toUpperCase().length() > 0) {
                    relyon_rst.append(Etl_Job_Name.trim().toUpperCase()).append("->").append(tmp_row.getCell(1).toString().trim().toUpperCase()).append(";");
                }
            }
        }

        System.out.println("trigger_rst:"+trigger_rst);
        System.out.println("trigger_rst:"+relyon_rst);

        FileOutputStream fos = new FileOutputStream(myFile);
        myWorkBook.write(fos);
        myWorkBook.close();
        fis.close();
        fos.close();

    }

}
