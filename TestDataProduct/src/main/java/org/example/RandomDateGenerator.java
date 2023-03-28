package org.example;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {

    private final int yearstart= 1900;
    private final int yearend= 2024;


    private  int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public String  generate8RandomDate(){

        // Create a random year between 1900 and 2099
        int year = getRandomNumberInRange(yearstart, yearend);

        // Create a random day of year between 1 and 365 (or 366 for leap year)
        int dayOfYear = getRandomNumberInRange(1, LocalDate.ofYearDay(year, 1).lengthOfYear());

        // Create a LocalDate object from the year and day of year
        LocalDate randomDate = LocalDate.ofYearDay(year, dayOfYear);

        // Format the LocalDate as an 8-digit string using the pattern "yyyyMMdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return randomDate.format(formatter);

    }

    public String  generate10RandomDate(){

        // Create a random year between 1900 and 2099
        int year = getRandomNumberInRange(yearstart, yearend);

        // Create a random day of year between 1 and 365 (or 366 for leap year)
        int dayOfYear = getRandomNumberInRange(1, LocalDate.ofYearDay(year, 1).lengthOfYear());

        // Create a LocalDate object from the year and day of year
        LocalDate randomDate = LocalDate.ofYearDay(year, dayOfYear);

        // Format the LocalDate as an 8-digit string using the pattern "yyyyMMdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return randomDate.format(formatter);

    }


}
