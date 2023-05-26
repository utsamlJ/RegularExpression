package com.study.InterfacesAndInnerClass;

/**
 * @USER: jys
 * @DATE: 2023/5/22
 * @TIME: 16:37
 * @DAY_NAME_FULL: 星期一
 */
public class ClassInnerInterface {
    public static class ClassInnerInterfaceC{}
    public  class ClassInnerInterfaceD{}
    private interface Base2{}
    private Base2 base2;
    public ClassInnerInterface(){
        base2 = new Base2() {
        };
        System.out.println("base2 = " + base2);
    }

    public void ff(){
        class ClassInnerInterfaceA{
            public ClassInnerInterfaceA(){
                System.out.println("this = " + this);
            }
        }
        ClassInnerInterfaceA classInnerInterfaceA = new ClassInnerInterfaceA();
        System.out.println("ff()的classInnerInterfaceA.getClass() = " + classInnerInterfaceA.getClass());
    }
    public void f(){
        class ClassInnerInterfaceA{
            public ClassInnerInterfaceA(){
                System.out.println("this = " + this);
            }
        }
        ClassInnerInterfaceA classInnerInterfaceA = new ClassInnerInterfaceA();
        System.out.println("f()的classInnerInterfaceA.getClass() = " + classInnerInterfaceA.getClass());
    }

    public void fff(){
        class ClassInnerInterfaceB{
            public ClassInnerInterfaceB(){
                System.out.println("this = " + this);
            }
        }
        ClassInnerInterfaceB classInnerInterfaceB = new ClassInnerInterfaceB();
        System.out.println("fff()的classInnerInterfaceB.getClass() = " + classInnerInterfaceB.getClass());
    }

    public Base2 getBase2() {
        return base2;
    }
}
