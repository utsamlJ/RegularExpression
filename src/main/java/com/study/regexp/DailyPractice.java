package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @USER: jys
 * @DATE: 2023/5/26
 * @TIME: 14:57
 * @DAY_NAME_FULL: 星期五
 */
public class DailyPractice {
    public static void main(String[] args) {
        String str = "1998年12月8日，第二代Java平台的企业版J2EE发布。" +
                "1999年6月，Sun公司发布了第二代Java平台(简称为Java2) 的3个版本: " +
                "J2ME (Java2 Micro Edition，Java2平台的微型版) ，应用于移动、无线及有限资源的环境;" +
                "J2SE (Java 2 Standard Edition，Java 2平台的标准版)，应用于桌面环境;" +
                "J2EE (Java 2Enterprise Edition，Java 2平台的企业版) ，应用3443于基于Java的应用服务器。" +
                "Java 2平台的发布，是Java发展过程中最重要的一个里程碑，标志着Java的应用开始普及9889.1.1.2";

        String reg = "\\d[.\\d]*";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            System.out.println("matcher.group(0) = " + matcher.group(0));
        }
    }
}
