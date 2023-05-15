package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test07 {
    public static void main(String[] args) {
        String str = "aabbccdd";

        String regStr = "(?<g1>\\w\\w)(?<g2>\\w\\w)";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            System.out.println("matcher.group(0) = " + matcher.group(0));
            System.out.println("matcher.group(\"g1\") = " + matcher.group("g1"));
            System.out.println("matcher.group(\"g2\") = " + matcher.group("g2"));
        }
    }
}
