package com.xujh;

/**
 * <p>
 * 对象1先锁a，再锁b；对象2先锁b，再锁a
 * 如果1锁住a，2锁住b，那么1就没有办法锁住b，2也没办法锁住a
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DeadLock {

    public static String a = "a";
    public static String b = "b";

    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}

class Lock1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock1 running");
            while (true) {
                synchronized (DeadLock.a) {
                    System.out.println("Lock1 lock a");
                    //获取obj1后先等一会儿，让Lock2有足够的时间锁住obj2
                    Thread.sleep(3000);
                    synchronized (DeadLock.b) {
                        System.out.println("Lock1 lock b");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Lock2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock2 running");
            while (true) {
                synchronized (DeadLock.b) {
                    System.out.println("Lock2 lock b");
                    Thread.sleep(3000);
                    synchronized (DeadLock.a) {
                        System.out.println("Lock2 lock a");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}