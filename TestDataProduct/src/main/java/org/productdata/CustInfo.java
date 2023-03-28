package org.productdata;

import org.example.CustomerBaseInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustInfo {
    public void WirteData() throws IOException {

        OutputStream outputStream = Files.newOutputStream(Paths.get("CustInfo.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        for (int i = 1; i < 3; i++) {
            CustomerBaseInfo customeridgenerator = new CustomerBaseInfo();
            String rst= customeridgenerator.GenerateIDGenerator()+"|"+ customeridgenerator.GenerateTypeGenerator()+"|"+ customeridgenerator.RandomCustomerNameGenerator()+"|"+ customeridgenerator.GeneratePhoneNumber()+"|"+ customeridgenerator.RandomCustomerIdGenerator()+"\n";
            byte[] bytes = rst.getBytes(StandardCharsets.UTF_8);
            bufferedOutputStream.write(bytes);
        }
        bufferedOutputStream.close();

    }
}

