package com.xujh.chapter08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ExchangerTest {
    private static Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService service = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        service.execute(() -> {
            String a = "A岗位输入的数据";
            try {
                TimeUnit.SECONDS.sleep(3);
                exchanger.exchange(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            String b = "B岗位输入的数据";
            String exchange = null;
            try {
                exchange = exchanger.exchange(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("数据是否一致:" + exchange.equals(b));
            System.out.println("a: " + exchange);
            System.out.println("b: " + b);
        });

        service.shutdown();
    }
}
