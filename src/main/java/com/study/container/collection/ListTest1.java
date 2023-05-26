package com.study.container.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @USER: jys
 * @DATE: 2023/5/23
 * @TIME: 13:40
 * @DAY_NAME_FULL: 星期二
 */
public class ListTest1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaaaa");
        list.add("bbbbb");
        list.add("ccccc");

        list.equals("");


        String[] strArr = new String[0];
        List<String> list1 = Arrays.asList(strArr);
//        strArr[0] = "1";
//        strArr[1] = "2";
//        strArr[2] = "3";
//        strArr[3] = "4";
//        strArr[4] = "5";
        System.out.println("strArr.length = " + strArr.length);
        System.out.println("Arrays.toString(strArr) = " + Arrays.toString(strArr));

        String[] strings = list.toArray(strArr);


        System.out.println("strArr.length = " + strArr.length);
        System.out.println("Arrays.toString(strArr) = " + Arrays.toString(strArr));
        System.out.println("Arrays.toString(strings) = " + Arrays.toString(strings));

        System.out.println("strings = " + strings);
        System.out.println("strArr = " + strArr);

    }
}
