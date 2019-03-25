package com.github.book.stream.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MappingMain {

    public static void main(String[] args) {

        testMap();

        testFlatMap1();

        testFlatMap2();

    }

    private static void testMap() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()
                .map(i -> i * i)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * expect out: ["H", "e", "l", "o", "W", "r", "d"].
     */
    private static void testFlatMap1() {
        // incorrect
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> incorrect1 = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(incorrect1);

        // incorrect
        List<Stream<String>> incorrect2 = words.stream()
                .map(wold -> wold.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(incorrect2);

        // correct
        List<String> correct = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(correct);
    }

    /**
     * given a list [1, 2, 3] and a list [3, 4]
     * you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
     */
    private static void testFlatMap2() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(3, 4);

        a.stream()
                .flatMap(i -> b.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList())
                .forEach(c -> System.out.println(Arrays.toString(c)));


    }

}
