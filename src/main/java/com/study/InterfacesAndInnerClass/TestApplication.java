package com.study.InterfacesAndInnerClass;

/**
 * @USER: jys
 * @DATE: 2023/5/22
 * @TIME: 16:27
 * @DAY_NAME_FULL: 星期一
 */
public class TestApplication extends TestTwoApplication {
    static {
        System.out.println("主方法的static代码块开始执行");
    }
    public static void main(String[] args) {
        System.out.println("main方法开始执行");
        System.out.println("BaseTwo.b.name = " + BaseTwo.b.name);
    }
}
