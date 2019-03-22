package com.github.book.lambda.uselamdba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p> 使用function: predicate</p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class PredicateMain {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "", "c");

        Predicate<String> notEmpty = s -> !s.isEmpty();

        List<String> result = filter(list, notEmpty);
        System.out.println(result);

    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

}
