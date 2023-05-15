package com.study.extend.test;

public interface Test01 {
    Test02 t = new Test02();
    default void f(){
        System.out.println("true = " + true);
    }
    static void ff(){

    }
}
