package com.xujh;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     不可变对象
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("one");
        stooges.add("two");
        stooges.add("three");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public static void main(String[] args) {

        ThreeStooges threeStooges = new ThreeStooges();
//        threeStooges.stooges = new HashSet<>();

    }
}
