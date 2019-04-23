package com.xujh.chapter03;

/**
 * <p>
 *     某个类的范围，synchronized 防止多个线程同时访问这个类中的synchronized static 方法。它可以对类的所有对象实例起作用。
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncStaticClassDemo {

    public static void main(String[] args) {

//        new Thread(SyncStaticClassDemo::method1).start();
//        new Thread(SyncStaticClassDemo::method2).start();

        SyncStaticClassDemo demo1 = new SyncStaticClassDemo();
        demo1.method1();
        SyncStaticClassDemo demo2 = new SyncStaticClassDemo();
        demo2.method2();

    }

    public synchronized static void method1() {
        System.out.println("method1 start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1 end");
    }

    public synchronized static void method2() {
        System.out.println("method2 start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 end");
    }

}
