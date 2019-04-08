package com.xujh.cacheinit;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Initial cache attempt using HashMap and synchronization
 *
 * 保守方法：同步整个compute方法；线程安全了，但是有明显的伸缩性问题
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Memoizer1 <A, V> implements Computable<A, V>{

    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}


interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction
        implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        // after deep thought...
        return new BigInteger(arg);
    }
}

