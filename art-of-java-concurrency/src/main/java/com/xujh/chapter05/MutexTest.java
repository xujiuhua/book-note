package com.xujh.chapter05;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MutexTest {
    private volatile Mutex mutex = new Mutex();

    private final Lock lock = new ReentrantLock();
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        MutexTest mutexTest = new MutexTest();

        new Thread(mutexTest::testLock).start();

        new Thread(mutexTest::testLock).start();


        new Thread(mutexTest::insert).start();

        new Thread(mutexTest::insert).start();

    }


    public void testLock() {
        mutex.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " obtain lock success.");
        } finally {
            mutex.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock success.");
        }
    }

    public void insert() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放了锁");
            lock.unlock();
        }
    }

}
