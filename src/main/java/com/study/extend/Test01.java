package com.study.extend;

/**
 * @USER: jys
 * @DATE: 2023/5/14
 * @TIME: 15:52
 * @DAY_NAME_FULL: 星期日
 */
abstract class Super{
    static {
        System.out.println("false = " + false);
    }
}
//测试Git分支2
public class Test01 extends Super {
    static {
        System.out.println("true = " + true);
    }
    public static void main(String[] args) {
        System.out.println("args = " + args.toString());
        B b = new B("saf");
        System.out.println("b.b = " + b.b);
    }
}

class A {
    int a;
    A(){
        a=2;
        fff();
    }
    protected void fff(){
        System.out.println("a = " + a);
    }
//    A(int a) {
//        this.a = a;
//    }
//    public final void f(){
//        System.out.println("a = " + a);
//    }
//    public static A ff(){
//        return new A(1);
//    }
}

class B extends A{
    int b = a;
    String str;
    public void fff(){
        System.out.println("b = " + b);
    }
//    B(String str,int a){
//        super(1);
////        this(str);
//    }
    B(String str){
        super();
        System.out.println("b = " + b);
        this.str = str;
//        f();
    }
}

