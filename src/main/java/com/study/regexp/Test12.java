package com.study.regexp;

import java.util.Arrays;

/**
 * @USER: jys
 * @DATE: 2023/5/5
 * @TIME: 16:34
 * @DAY_NAME_FULL: 星期五
 */
public class Test12 {
    public static void main(String[] args) {
        final int[] arr = {1,2,3};
        System.out.println("arr.length = " + arr.length);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        arr[0] = 2;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
