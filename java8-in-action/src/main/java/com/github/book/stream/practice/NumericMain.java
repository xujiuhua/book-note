package com.github.book.stream.practice;

import com.github.book.stream.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 * <p>
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

//        testRange();

        testTuple();

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

    /**
     * 3, 4, 5
     * 5, 12, 13
     * .....
     */
    private static void testTuple() {
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                )
                .limit(5)
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }

}
