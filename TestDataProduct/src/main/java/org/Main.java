package org;

import org.example.CustomerBaseInfo;
import org.example.RandomDateGenerator;
import org.example.RandomSerialNumberGenerator;
import org.productdata.CustInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


public class Main {


    public static void main(String[] args) throws IOException {

        CustInfo custinfo = new CustInfo();
        custinfo.WirteData();
        RandomDateGenerator randomdategenerator=new RandomDateGenerator();
        System.out.println(randomdategenerator.generate10RandomDate());
        System.out.println(randomdategenerator.generate8RandomDate());
        RandomSerialNumberGenerator randomserialnumbergenerator =new RandomSerialNumberGenerator();
        System.out.println(randomserialnumbergenerator.generateRandomSerialNumber(23));

    }
}
