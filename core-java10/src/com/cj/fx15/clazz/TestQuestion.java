package com.cj.fx15.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class TestQuestion {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();

        method(a);
        method(b);
    }

    public static void method(ArrayList<?> m) {
        for (Object o : m) {
            System.out.println(o);
        }
    }
}
