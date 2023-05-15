package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test06 {
    public static void main(String[] args) {
        String str = "aabbdd";
        String regStr = "(a+)(b+)(c*)(d+)";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println("matcher.group(0) = " + matcher.group(0));
            System.out.println("matcher.group(1) = " + matcher.group(1));
            System.out.println("matcher.group(2) = " + matcher.group(2));
            System.out.println("matcher.group(3) = " + matcher.group(3));
            System.out.println("matcher.group(4) = " + matcher.group(4));
        }
    }
}
