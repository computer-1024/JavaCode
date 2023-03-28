package org.example;

import java.util.Random;

public class RandomCustomerNameGenerator {
    public String PerCustomerNameGenerator(){

        String[] FIRST_NAMES = {"张", "李", "王", "赵", "陈", "刘", "周", "吴", "朱", "沈", "徐", "孙", "马", "胡", "郑", "何", "高", "林", "钟", "谢"};
        String[] LAST_NAMES = {"三", "四", "五", "六", "七", "八", "九", "十", "华", "龙", "军", "文", "辉", "明", "国", "伟", "强", "磊", "涛", "云"};
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return  firstName + lastName;

    }
    public String CorCustomerNameGenerator(){

        String[] FIRST_WORDS = {"金", "利", "昌", "鑫", "合", "美", "盛", "亚", "华", "信", "宏", "达", "中", "嘉", "鸿", "泰", "永", "博", "广", "东"};
        String[] SECOND_WORDS = {"盛", "华", "通", "达", "兴", "信", "泰", "利", "和", "仁", "安", "泽", "良", "福", "祥", "全", "顺", "吉", "富", "巨"};
        Random random = new Random();
        String firstWord = FIRST_WORDS[random.nextInt(FIRST_WORDS.length)];
        String secondWord = SECOND_WORDS[random.nextInt(SECOND_WORDS.length)];
        return firstWord + secondWord + "公司";

    }
}
