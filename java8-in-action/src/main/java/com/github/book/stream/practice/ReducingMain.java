package com.github.book.stream.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * An initial value
 * A lambda to combine two stream elements and produce a new value
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ReducingMain {

    public static void main(String[] args) {

//        test1();

//        test2();

//        test3();

    }

    /**
     * calculate
     */
    private static void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum2);

        Optional<Integer> sum3 = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sum3.orElse(100));

        // quickly
        int sum4 = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println(sum4);

    }

    /**
     * min max
     */
    private static void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.get());
    }

    /**
     * count
     */
    private static void test3() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        long count = numbers.stream().count();
        System.out.println(count);
    }

}
