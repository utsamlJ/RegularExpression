package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test10 {
    public static void main(String[] args) {
        int a = 11;
        String str = "撒发大水啊所发生的";
        String regStr = "^[\u4e00-\u9fa5]+$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("saf");
            System.out.println(matcher.group(0));
        }
    }
}
