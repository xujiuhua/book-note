package com.xujh.chapter04;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();


    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread() + " flag is true. wait @ " + LocalDateTime.now());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 条件满足，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + LocalDateTime.now());
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock. notify @  " + LocalDateTime.now());
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @  " + LocalDateTime.now());
                SleepUtils.second(5);
            }
        }
    }
}
