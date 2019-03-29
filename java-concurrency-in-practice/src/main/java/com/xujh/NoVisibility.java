package com.xujh;

/**
 * <p>
 *     情况1：对于读线程，可能一直保持循环，因为无法感知主线程ready的值
 *     情况2：可能输出0，因为指令重排序，在对number赋值之前，主线程已经写入ready
 *     情况3：可能输出100
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class NoVisibility {
    private static volatile boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 100;
        ready = true;
    }
}
