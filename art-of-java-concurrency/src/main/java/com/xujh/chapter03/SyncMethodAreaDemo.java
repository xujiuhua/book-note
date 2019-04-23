package com.xujh.chapter03;

/**
 * <p>同步方法块，synchronized 同步的是对象
 *
 * 只有当第一个线程的同步块执行完毕后，第二个线程的同步块才执行
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncMethodAreaDemo {

    public static void main(String[] args) {

        SyncMethodAreaDemo demo = new SyncMethodAreaDemo();
        new Thread(demo::method1).start();
        new Thread(demo::method2).start();

//        SyncMethodAreaDemo demo2 = new SyncMethodAreaDemo();
//        new Thread(demo2::method1).start();

    }

    public void method1() {
        System.out.println(Thread.currentThread().getName() + " method1 start...");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " method1 execute");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " method1 end");
    }

    public void method2() {
        System.out.println(Thread.currentThread().getName() + " method2 start...");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " method2 execute");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " method2 end");
    }

}
