package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test05 {
    public static void main(String[] args) {
        String str = "123&abc";

        String regStr = "^[0-9]*&[a-z]+$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            System.out.println("matcher.group(0) = " + matcher.group(0));
        }
    }
}
