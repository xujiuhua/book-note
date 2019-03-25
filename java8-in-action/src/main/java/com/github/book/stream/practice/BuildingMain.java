package com.github.book.stream.practice;

import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BuildingMain {
    public static void main(String[] args) {
        testIterate();

        testGenerate();
    }

    /**
     * iterate
     */
    private static void testIterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void testGenerate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        Stream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

    }
}
