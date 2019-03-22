package com.github.book.stream.map;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MapMain {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Hello", "World");
        words.forEach(word -> {
            String[] split = word.split("");
            Stream<String> stream1 = Arrays.stream(split);
            List<String> collect = stream1.collect(Collectors.toList());
//            for (String s : split) {
//                System.out.println(s);
//            }
        });

        Stream<String[]> stream = words.stream().map(word -> word.split(""));
//                .distinct()
//                .collect(Collectors.toList())
//                .forEach(System.out::println);

        Stream<Stream<String>> streamStream = stream.map(Arrays::stream);


        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> stream1 = Arrays.stream(arrayOfWords);
        List<String> collect = stream1.collect(Collectors.toList());

        words.stream().map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().map(i -> i*i)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5);

        List<int[]> collect1 = a.stream().flatMap(i -> b.stream()
                .map(j -> new int[]{i, j})
        ).collect(Collectors.toList());

        List<Integer> list = Arrays.asList(4, 5, 3, 9);
        Optional<Integer> reduce = list.stream().reduce(Integer::max);
        System.out.println(reduce.get());

    }

}
