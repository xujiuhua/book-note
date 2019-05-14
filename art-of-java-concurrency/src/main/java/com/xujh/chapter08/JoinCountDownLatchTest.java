package com.xujh.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * <p> plain </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws Exception {

        Thread first = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first complete.");
        });

        Thread second = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second complete.");
        });


        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println("all complete.");

    }
}
