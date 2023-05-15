package com.study.regexp;

import com.study.extend.*;

/**
 * @USER: jys
 * @DATE: 2023/5/5
 * @TIME: 17:37
 * @DAY_NAME_FULL: 星期五
 */
public class Test13 {
    public static void main(String[] args) {
        String str = "11abc11abc111abc";
        String regStr = "11";
        String[] split = str.split(regStr,10);
        System.out.println("split.length = " + split.length);
        for (String s : split) {
            System.out.println("s = " + s);
        }

    }
}
