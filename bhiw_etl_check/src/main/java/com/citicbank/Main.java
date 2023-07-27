package com.citicbank;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String csvFile = "SCRIPT_LIST.csv";

        CSVReader CSVReader = new CSVReader();
        CSVReader.readCSVFile(csvFile);

    }



}