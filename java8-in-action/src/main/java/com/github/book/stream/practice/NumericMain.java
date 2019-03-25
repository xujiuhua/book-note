package com.github.book.stream.practice;

import com.github.book.stream.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 *
 * avoid cost: boxing and unboxing
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class NumericMain {

    public static void main(String[] args) {

//        testMapToNumeric();

//        testNumericToMap();

//        testOptInt();

        testRange();

    }

    private static void testMapToNumeric() {

        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);

    }

    private static void testNumericToMap() {
        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
        System.out.println(boxed.reduce(Integer::max));
    }

    private static void testOptInt() {
        OptionalInt max = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(max);
    }

    private static void testRange() {
        IntStream intStream = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);

        System.out.println(intStream.count());
    }

}
