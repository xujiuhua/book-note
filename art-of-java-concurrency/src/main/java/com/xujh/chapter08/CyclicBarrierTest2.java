package com.xujh.chapter08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class CyclicBarrierTest2 {

    static CyclicBarrier c ;

    public static void main(String[] args) {

        // 主线程和子线程共2个线程，所有的这些线程都调用c.await()等待。
        // 所有这些线程一直等待，直到c中所有线程都达到barrier时，执行新建c时注册的Runnable任务。
        c = new CyclicBarrier(2, () ->  System.out.println("CyclicBarrier's parties is: "+ c.getParties()));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        try {
            System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
