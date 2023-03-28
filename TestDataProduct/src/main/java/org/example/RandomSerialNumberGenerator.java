package org.example;

import java.util.Random;

public class RandomSerialNumberGenerator {

    String[] prefixes = {"MY", "AB", "SE", "FG", "SQ", "SD"};

    public  String generateRandomSerialNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        }
        Random random = new Random();
        String prefix = prefixes[random.nextInt(prefixes.length)];

        StringBuilder sb = new StringBuilder(prefix);

        for (int i = 0; i < n-2; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
