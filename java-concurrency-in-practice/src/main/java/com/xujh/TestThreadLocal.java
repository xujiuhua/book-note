package com.xujh;

/**
 * <p>
 * 1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 * 2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象? 因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；
 * 3）在进行get之前，必须先set，否则会报空指针异常；如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class TestThreadLocal {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal test = new TestThreadLocal();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(() -> {
            test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());
        });
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
