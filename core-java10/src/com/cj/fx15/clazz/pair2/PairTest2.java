package com.cj.fx15.clazz.pair2;

import com.cj.fx15.clazz.Pair;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class PairTest2 {

    public static void main(String[] args) {

        LocalDate[] birthdays = {

                LocalDate.of(1945, 11, 12),
                LocalDate.of(1985, 11, 11),
                LocalDate.of(2000, 1, 4),
                LocalDate.of(1789, 1, 4),
        };

        final Pair<LocalDate> minmax = ArrayAlg.minmax(birthdays);
        System.out.println(minmax.getFirst());
        System.out.println(minmax.getSecond());
    }

}

class ArrayAlg {

    public static  <T extends Comparable & Serializable> Pair<T> minmax(T[] a) {

        if (a == null || a.length ==  0) return null;

        T min = a[0];
        T max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }

        return new Pair<>(min, max);

    }

    public static  <T extends Comparable<T> & Serializable> Pair<T> minmax2(T[] a) {

        if (a == null || a.length ==  0) return null;

        T min = a[0];
        T max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }

        return new Pair<>(min, max);

    }

}
