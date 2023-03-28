package org.example;

import java.util.Random;

public class GeneratePhoneNumber {
    public String GeneratePerNumber() {
        String[] prefixArray = {"130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "153", "155", "156", "157", "158", "159", "186", "187", "188", "198", "199"};
        Random random = new Random();
        String prefix = prefixArray[random.nextInt(prefixArray.length)];
        String suffix = "";

        for (int i = 0; i < 8; i++) {
            suffix += random.nextInt(10);
        }
        return prefix + suffix;
    }

    public String GenerateCorNumber() {
        String[] AREA_CODES = {"010","021","022","023","0310","0351","024","0431","0451","025","0571","0551","0591","0791","0531","0371","027","0731","020","0771","0898","028","0851","0871","029","0931","0971","0471","0891","0991","0951","852","853"};
        Random random = new Random();
        String areaCode = AREA_CODES[random.nextInt(AREA_CODES.length)];
        int exchangeCode = 1000 + random.nextInt(9000);
        int subscriberNumber = 1000 + random.nextInt(9000);
        return areaCode + "-" + exchangeCode + subscriberNumber;
    }


}
