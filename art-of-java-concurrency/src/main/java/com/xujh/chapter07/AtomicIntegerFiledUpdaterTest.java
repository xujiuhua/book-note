package com.xujh.chapter07;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class AtomicIntegerFiledUpdaterTest {

    /**
     *  创建原子更新器，并设置需要更新的类和属性
     */
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) throws InterruptedException {

        User user = new User("Tom", 10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.printf("%s: %d%n", Thread.currentThread().getName(), updater.getAndIncrement(user))).start();
        }
        TimeUnit.SECONDS.sleep(2);

        System.out.println(updater.get(user));

    }


    @Data
    static class User {
        private String name;
        /**
         * NOTE: public volatile
         */
        public volatile int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
