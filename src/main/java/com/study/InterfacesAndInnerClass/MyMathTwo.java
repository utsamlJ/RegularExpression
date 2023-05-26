package com.study.InterfacesAndInnerClass;

/**
 * @USER: jys
 * @DATE: 2023/5/22
 * @TIME: 16:28
 * @DAY_NAME_FULL: 星期一
 */
public class MyMathTwo extends MyMath{
    private int num = 10;
    private String name = "你好";
    private Base base = new Base() {
        {
            System.out.println("BaseNum = " + num);
        }
    };
    public MyMathTwo(){
        System.out.println("num2 = " + num);
        System.out.println("name2 = " + name);
    }
}
