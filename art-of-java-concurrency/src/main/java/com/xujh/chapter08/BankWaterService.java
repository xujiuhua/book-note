package com.xujh.chapter08;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *     应用场景：银行流水处理服务类
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BankWaterService implements Runnable{

    private CyclicBarrier c = new CyclicBarrier(4, this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                map.put(Thread.currentThread().getName(), 1);
                System.out.println(Thread.currentThread().getName());
                // 计算完成，插入一个屏障
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        System.out.println("run...");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // 汇总计算结果值
        map.forEachValue(Integer.MAX_VALUE, atomicInteger::addAndGet);

        map.put("result", atomicInteger.get());
        System.out.println(atomicInteger.get());
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
