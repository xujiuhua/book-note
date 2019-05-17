package com.xujh.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>  </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ReentrantLockTest {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        // 方法1获取锁，并乜有释放；这是方法2也能获取锁
        lock.lock();
        System.out.println("m1");
        m2();
        lock.unlock();
    }

    private static void m2() {
        lock.lock();
        System.out.println("m2");
        lock.unlock();
    }

}
