package com.xujh.chapter03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * <p>
 * 1.变量使用volatile修饰保证内存可见性；
 * 2.CAS算法保证数据的原子性。
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class AtomicDemo {

    private static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("线程1启动");
            while (i.get() == 0) {

            }
            System.out.println("Over");
        }).start();

        try {
            // launch the 2nd thread after 2 seconds for see demo.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("线程2启动");
            i.compareAndSet(0, 1);
        }).start();

    }

}
