package com.citicbank.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void outputfile(StringBuilder stringbuilder, String arg) throws IOException {

        File file = new File(arg+".dat");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(String.valueOf(stringbuilder));
        bw.close();
        osw.close();
        fos.close();

    }
    public static void getdata(Table table,String key) throws IOException {

        Get g = new Get(Bytes.toBytes(key));

        // Reading the data
        Result result = table.get(g);

        // Reading values from Result class object
        byte [] value = result.getValue(Bytes.toBytes("cf"),Bytes.toBytes("name"));
        byte [] value1 = result.getValue(Bytes.toBytes("cf"),Bytes.toBytes("city"));
        byte [] value2 = result.getValue(Bytes.toBytes("cf"),Bytes.toBytes("salary"));

        // Printing the values
        String name = Bytes.toString(value);
        String city = Bytes.toString(value1);
        String salary = Bytes.toString(value2);

        System.out.println("name: " + name + " city: " + city+" salary: " + salary);

    }

    public static void scandata(Table table, String arg) throws IOException {


        String delimiter ="" + (char)0x03;

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        Map<String, List<String>> data = new HashMap<>();

        for (Result result = scanner.next(); result != null; result = scanner.next()) {
            String rowkey = Bytes.toString(result.getRow());
            List<String> values = new ArrayList<String>();
            for (Cell cell : result.listCells()) {
                // each cell represents a column
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                values.add(value);
            }
            data.put(rowkey, values);
        }
            StringBuilder stringbuilder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            stringbuilder.append(entry.getKey());
            for (String value : entry.getValue()) {
                if( value == null){
                    stringbuilder.append(delimiter);
                }
                else
                {
                    stringbuilder.append(delimiter);
                    stringbuilder.append(value);
                }

            }
            stringbuilder.append("\n");
        }
        scanner.close();
        outputfile(stringbuilder,arg);

    }


    public static void main(String[] args) throws IOException {

        // Instantiating Configuration class
        Configuration config = HBaseConfiguration.create();

        config.set("hbase.zookeeper.quorum", args[0]);
        config.set("hbase.zookeeper.property.clientPort", args[1]);

        Connection connection = ConnectionFactory.createConnection(config);

        Table table = connection.getTable(TableName.valueOf(args[2]));

       //getdata(table,"1");
        scandata(table,args[2]);

    }
}