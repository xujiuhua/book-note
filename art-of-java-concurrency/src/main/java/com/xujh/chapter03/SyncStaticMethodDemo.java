package com.xujh.chapter03;

/**
 * <p>
 * synchronized
 * 某个对象实例内，synchronized method 可以防止多个线程同时访问这个对象的synchronized方法
 * （如果一个对象有多个synchronized方法，只要一个线 程访问了其中的一个synchronized方法，其它线程不能同时访问这个对象中任何一个synchronized方法）
 *
 * 这时，不同的对象实例的 synchronized方法是不相干扰的。也就是说，其它线程照样可以同时访问相同类的另一个对象实例中的synchronized方法；
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncStaticMethodDemo {

    public static void main(String[] args) {

        SyncStaticMethodDemo example = new SyncStaticMethodDemo();
        new Thread(example::method1).start();
        new Thread(example::method2).start();
        new Thread(example::method3).start();

        SyncStaticMethodDemo example2 = new SyncStaticMethodDemo();
        // 两个实例不受影响
        new Thread(example2::method1).start();
        new Thread(example2::method3).start();

    }

    private synchronized void method1() {
        System.out.println("method1 start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1 end");
    }

    private void method2() {
        System.out.println("method2 start...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 end");
    }

    private synchronized void method3() {
        System.out.println("method3 start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method3 end");
    }

}
