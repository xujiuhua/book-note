package com.xujh.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        // 休眠5s,让两个线程充分运行
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        // false 中断标识位被清除
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        // true
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

        SleepUtils.second(2);

    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
