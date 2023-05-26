package com.study.InterfacesAndInnerClass;

import lombok.Getter;
import lombok.Setter;

/**
 * @USER: jys
 * @DATE: 2023/5/23
 * @TIME: 13:19
 * @DAY_NAME_FULL: 星期二
 */
@Getter
@Setter
public class BaseClass {
    public String name;
    public int age;

    static {
        System.out.println("BaseClass被加载");
    }

    public BaseClass(){
        System.out.println("BaseClass 的实例对象被创建，this = " + this);
        name = "你好";
        age = 18;
    }
}
