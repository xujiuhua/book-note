package com.xujh.chapter04;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Shutdown {

    public static void main(String[] args) throws InterruptedException {

        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }


    private static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {

            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }

        public void cancel () {
            on = false;
        }
    }
}
