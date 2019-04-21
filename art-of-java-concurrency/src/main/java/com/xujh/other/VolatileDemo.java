package com.xujh.other;

/**
 * <p>
 *     使用volatile修饰flag,启动第一个线程后，延迟2s再启动第二个线程，
 *     当第二个线程修改flag值时，会把只写入主内存，那么第一个线程每次都是从主内存读取flag,
 *     则能终止循环。 但是因为i是在2s后写值，则输出i为0，
 *     如果把i=1放在flag = true前面，则第二个线程刷新主内存时，会把它之前的操作都刷新回主内存，输出i为1
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class VolatileDemo {

    private static int i = 0;
    private static volatile boolean flag = false;

    public static void main(String[] args) {

        new Thread(() -> {
            while (!flag) {

            }
            System.out.println(i);
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            flag = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 1;
        }).start();
    }

}
