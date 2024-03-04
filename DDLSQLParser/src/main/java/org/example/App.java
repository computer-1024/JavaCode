package org.example;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


/**
 * Hello world!
 *
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws JSQLParserException, IOException {

        BasicConfigurator.configure();
        //String SQL = "create table test1 ( a int ,b double,c decimal(20,4) not null );";
        //String SQL = "select * from A left join B on A.id=B.id and A.age = (select age from C);";
        String SQL = "      select \n" +
                "             COUNT(DISTINCT T1.AGMT_ID)                  --睡眠客户数\n" +
                "        FROM PDM_VIEW.V03_SLP_ACCT_INFO_H   T1 inner join PDM_VIEW.V03_CUST_INFO_H  T2  ON T1.CUST_ID=T2.CUST_ID  --睡眠账户信息历史\n" +
                "       WHERE T1.BANK_NUM = '001'\n" +
                "         AND T1.START_DT <= CURRENT_DATE - 1\n" +
                "         AND T1.END_DT > CURRENT_DATE - 1\n" +
                "         AND T1.SLP_STAT_CD IN('12','09','02')          --12（强制销户）09（零金额销户）02（转营业外收入）\n" +
                "         AND t1.Zero_Amt_Clos_Acct_Dt >= '2023-11-01'  --零金额销户日期在去年11月之后的\n" +
                "         AND T1.SRC_TB_NAME = 'DCBS_001_DPSLGA'\n"+
                "         AND EXISTS (SELECT 1 FROM PDM_VIEW.V03_INFO_H T3 where t3.cust_id=t1.cust_id );";
        //String v_tabname=ddlgettable(SQL);
        //logger.info(ddlgettable(SQL));
        // logger.info(ddlgetdatatype(SQL));
        logger.info(selgettable(SQL));


//        try (XSSFWorkbook wb = new XSSFWorkbook()) {
//            XSSFSheet sheet = wb.createSheet("ddl清单");
//            int i=0;
//            for (String element : ddlgetdatatype(SQL)) {
//                XSSFRow row = sheet.createRow(i++);
//                String[] column_info = element.split("\\|");
//                for (int j = 0; j < 3; j++) {
//
//                    XSSFCell  cell = row.createCell(j);
//                    if (j == 0) {
//                        cell.setCellValue(v_tabname);
//                    } else if (j == 1) {
//                        cell.setCellValue(column_info[0]);
//                    }else {
//                        cell.setCellValue(column_info[1]);
//                    }
//
//                }
//
//                }
//            FileOutputStream fileOut = new FileOutputStream("ooxml-table.xlsx");
//            wb.write(fileOut);
//
//            }
    }

    public static ArrayList<String> ddlgetdatatype(String SQL) throws JSQLParserException {

        ArrayList<String> columnrst = new ArrayList<String>();
        CreateTable crt = (CreateTable) CCJSqlParserUtil.parse(SQL);
        crt.getColumnDefinitions().listIterator();
        for (ColumnDefinition columnDefinition : crt.getColumnDefinitions()) {
            columnrst.add(columnDefinition.getColumnName() + "|" + columnDefinition.getColDataType().toString());
        }

        return columnrst;

    }

    public static String ddlgettable(String SQL) throws JSQLParserException {

        CreateTable crt = (CreateTable) CCJSqlParserUtil.parse(SQL);
        return crt.getTable().toString();

    }

    public static String selgettable(String SQL) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(SQL);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        Set<String> tableList = tablesNamesFinder.getTables(statement);
        return tableList.toString();
    }


}