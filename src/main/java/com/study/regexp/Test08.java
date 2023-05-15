package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test08 {
    public static void main(String[] args) {
        //目标字符串
        String str = "aa1 bb2 cc3 dd4";
        //正则表达式
        String regStr = "[a-z]+[123]";
        //将给定的正则表达式编译并赋予给Pattern类
        Pattern pattern = Pattern.compile(regStr);
        //构建目标字符串的正则匹配引擎
        Matcher matcher = pattern.matcher(str);
        //找到下一个匹配，如果没有找到，就返回false
        while (matcher.find()){
            //输出匹配到的部分
            System.out.println("匹配到的内容 = " + matcher.group(0));
        }
    }
}