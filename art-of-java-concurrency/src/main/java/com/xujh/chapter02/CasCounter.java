package com.xujh.chapter02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class CasCounter {
    private Unsafe unsafe;
    private volatile long counter = 0;
    private long offset;

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public CasCounter() throws Exception {
        unsafe = getUnsafe();
        offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
    }

    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    public long getCounter() {
        return counter;
    }
}
