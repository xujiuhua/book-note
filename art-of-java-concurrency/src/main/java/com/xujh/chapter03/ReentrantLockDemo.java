package com.xujh.chapter03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ReentrantLockDemo {

    private int a = 0;
    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        ReentrantLockDemo demo = new ReentrantLockDemo();
        new Thread(demo::writer).start();
//        new Thread(demo::reader).start();

    }

    public void writer() {
        System.out.println("writer lock");
        lock.lock();
        System.out.println("writer execute");
        try {
            a++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
            System.out.println("writer unlock");
        }
    }

    public void reader() {
        System.out.println("reader lock");
        lock.lock();
        System.out.println("reader execute");
        try {
            int i = a;
            System.out.println(i);
        } finally {
            lock.unlock();
            System.out.println("reader unlock");
        }
    }

}
