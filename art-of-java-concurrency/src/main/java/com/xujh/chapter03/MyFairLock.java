package com.xujh.chapter03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MyFairLock {

    private ReentrantLock lock = new ReentrantLock(true);

    public void testFail() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyFairLock fairLock = new MyFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            fairLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
