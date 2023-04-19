package com.citicbank.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Instantiating Configuration class
        Configuration config = HBaseConfiguration.create();

        config.set("hbase.zookeeper.quorum", "192.168.1.8");
        config.set("hbase.zookeeper.property.clientPort", "2181");

        Connection connection = ConnectionFactory.createConnection(config);

        Table table = connection.getTable(TableName.valueOf("emp"));

        Get g = new Get(Bytes.toBytes("1"));

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
}