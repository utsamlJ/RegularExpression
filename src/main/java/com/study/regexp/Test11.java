package com.study.regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test11 {
    public static void main(String[] args) {
//        String str = "https://www.bilibili.com/video/BV1Eq4y1E79W/?p=17&spm_id_from=pageDriver&vd_source=435ceb8c5850be6a188772aa8a739253";
////        String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w-]+";
//        String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w-]+(\\/[\\w-?=&/%.#]*)?$";
//        Pattern pattern = Pattern.compile(regStr);
//        Matcher matcher = pattern.matcher(str);
//        while (matcher.find()){
//            System.out.println("matcher.group(0) = " + matcher.group(0));
//        }
        String ipStr = "192.168.0.1";
        String regStr = "\\.";
        String[] split = ipStr.split(regStr);
        System.out.println("split.length = " + split.length);
        System.out.println(Arrays.toString(split));
//        for (String s : ipStr.split(regStr)) {
//            System.out.println("s = " + s);
//        }
//        Matcher matcher = Pattern.compile(regStr).matcher(ipStr);
//
//        while (matcher.find()){
//            System.out.println("matcher.group(0) = " + matcher.group(0));
//        }
    }
}
