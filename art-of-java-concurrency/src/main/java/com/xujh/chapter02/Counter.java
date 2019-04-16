package com.xujh.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 *     CAS 实现原子操作的三大问题
 *     1. ABA问题: 解决方法AtomicStampedReference.compareAndSet
 *     2. 循环时间长，开销大
 *     3. 只能保证一个变量的原子性: 解决方法AtomicReference
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Counter {

    private int i = 0;

    private AtomicInteger atomicI = new AtomicInteger(0);

    public static void main(String[] args) {

        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.count();
                    counter.safeCount();
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        // 等待所有线程执行完毕
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);

    }

    private void safeCount() {
        for (; ; ) {
            int i = atomicI.get();
            boolean b = atomicI.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
