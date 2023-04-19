package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class Main {

    public static void main(String[] args) throws IOException {
        // HBase Configuration
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "remote-server1,remote-server2");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.client.retries.number", "3");
        config.set("hbase.rpc.timeout", "20000");

        // HBase Table
        TableName tableName = TableName.valueOf("mytable");
        Connection connection = ConnectionFactory.createConnection(config);
        Table table = connection.getTable(tableName);

        // HBase Scan
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        // Output file
        String fileName = "output.txt";
        File file = new File(fileName);
        FileOutputStream fop = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fop, StandardCharsets.UTF_8));

        // Write results to file
        for (Result result : scanner) {
            List<String> row = new ArrayList<>();
            for (Cell cell : result.listCells()) {
                row.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }
            String line = String.join(",", row);
            writer.write(line);
            writer.newLine();
        }
        writer.close();

        // Clean up
        scanner.close();
        table.close();
        connection.close();
    }
}
