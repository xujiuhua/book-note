package com.xujh.chapter05;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Cache {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock r = lock.readLock();
    static Lock w = lock.writeLock();

    public static final Object get(String key) {
        r.lock();
        System.out.println(LocalDateTime.now() + ":" + Thread.currentThread().getName());
        try {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        w.lock();
        System.out.println(LocalDateTime.now() + ":" + Thread.currentThread().getName());
        try {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(() -> Cache.put("a", "hello"), "w-a").start();
        new Thread(() -> Cache.put("b", "world"), "w-b").start();

        // 读锁时其他线程锁定；写锁不影响其他线程
        new Thread(() -> System.out.println(Cache.get("a")), "r-a").start();
        new Thread(() -> System.out.println(Cache.get("b")), "r-b").start();

        new Thread(() -> Cache.put("c", "!"), "w-c").start();

    }
}
