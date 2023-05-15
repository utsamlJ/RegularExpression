package com.study.regexp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试用正则表达式获取单词、数字
 */
public class Test01 {
    public static void main(String[] args) {
        String str = "Java 是一个通用术语，用于表示 Java 软件及其组件，包括“Java 运行时环境 (JRE)”、“Java 虚拟机 (JVM)”以及“插件”。 [1]\n" +
                "Java具有大部分编程语言所共有的一些特征，被特意设计用于互联网的分布式环境。Java具有类似于C++语言的形式和感觉，但它要比C++语言更易于使用，而且在编程时彻底采用了一种以对象为导向的方式。\n" +
                "Java版本指的是 Java 系列和更新编号。示例：在网站上或者 Windows 程序中，版本显示为 Java 8 Update 25。旧版本也可显示为 1.7.0_65，这表示 Java 7 Update 65。";

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println("找到单词：" + matcher.group(0));
        }

        Pattern pattern1 = Pattern.compile("[0-9]+");
        Matcher matcher1 = pattern1.matcher(str);

        while (matcher1.find()) {
            System.out.println("找到数字：" + matcher1.group(0));
        }

        Pattern pattern2 = Pattern.compile("([0-9]+)|([a-zA-Z]+)");
        Matcher matcher2 = pattern2.matcher(str);

        while (matcher2.find()) {
            System.out.println("找到单词/数字：" + matcher2.group(0));
        }


    }

}
