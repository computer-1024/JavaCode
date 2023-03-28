
package org.example;

import java.util.Random;


public class IdNumberGenerator {
    // 生成随机数对象
    private Random random = new Random();

    // 生成中国身份证号码
    public  String generateIdNumber() {
        // 随机生成一个地区码
        int areaCode = generateAreaCode();

        // 随机生成一个出生年份
        int birthYear = generateBirthYear();

        // 随机生成一个出生月份
        int birthMonth = generateBirthMonth();


        // 随机生成一个出生日期
        int birthDay = generateBirthDay(birthYear, birthMonth);

        // 随机生成一个顺序码
        int sequenceCode = generateSequenceCode();

        // 身份证前17位
        String rst= String.format("%06d",areaCode) +
                String.format("%04d",birthYear)+
                String.format("%02d",birthMonth)+
                String.format("%02d",birthDay)+
                String.format("%03d",sequenceCode);

        // 计算校验码
        char checkCode = generateCheckCode(rst);

        return rst+checkCode;
    }

    private char generateCheckCode(String idCardNumber){

        // 加权因子
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 校验码对应值
        char[] checksumValues = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        for (int i = 0; i < idCardNumber.length() - 1; i++) {
            char c = idCardNumber.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("Invalid character in ID card number: " + c);
            }
            int digit = c - '0';
            sum += digit * weights[i];
        }

        int checksumIndex = sum % 11;

        return checksumValues[checksumIndex];

    }

    // 生成地区码
    private  int generateAreaCode() {
        // 地区码范围：110000-820000
        return 110000 + random.nextInt(710000);
    }

    // 生成出生年份
    private  int generateBirthYear() {
        // 年份范围：1900-2022
        return 1900 + random.nextInt(123);
    }

    // 生成出生月份
    private  int generateBirthMonth() {
        // 月份范围：1-12
        return 1 + random.nextInt(12);
    }

    // 生成出生日期
    private  int generateBirthDay(int birthYear, int birthMonth) {
        // 计算指定年月的天数
        int maxDay = getMaxDayOfMonth(birthYear, birthMonth);

        // 出生日期范围：1-maxDay
        return 1 + random.nextInt(maxDay);
    }

    // 计算指定年月的最大天数
    private  int getMaxDayOfMonth(int year, int month) {
        if (month == 2) {
            // 闰年2月29天，平年2月28天
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            // 4、6、9、11月30天
            return 30;
        } else {
            // 1、3、5、7、8、10、12月31天
            return 31;
        }
    }

    // 判断指定年份是否为闰年
    private  boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    // 生成顺序码
    private  int generateSequenceCode() {
        // 顺序码范围：001-999
        Random random = new Random();
        // 生成一个3位随机数
        int randomNum = random.nextInt(999) + 1;
        return randomNum ;
    }

    public  String  OrganizationCodeGenerator ()
    {
        String CHARSET = "0123456789";
        int[] WEIGHTS = {3, 7, 9, 10, 5, 8, 4, 2}; // 权重数组
        StringBuilder sb = new StringBuilder();
        sb.append(getRandomString(8,CHARSET)); // 生成8位随机字符串作为本体代码
        sb.append("-");
        sb.append(getCheckCode(sb.toString(),WEIGHTS)); // 计算并添加校验码
        return sb.toString();
    }

    private  String getRandomString(int length,String CHARSET) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(index));
        }
        return sb.toString();
    }

    private  char getCheckCode(String code, int[] WEIGHTS ) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (i < WEIGHTS.length) {
                int weight = WEIGHTS[i];
                if (c >= '0' && c <= '9') {
                    sum += weight * (c - '0');
                }
            }
        }
        int remainder = 11 - sum % 11;
        if (remainder == 10) {
            return 'X';
        } else if (remainder == 11) {
            return '0';
        } else {
            return (char) (remainder + '0');
        }
    }



}

