package com.github.book.stream.collectors;

import com.github.book.stream.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * reducing
 * summarizing
 * join
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ReducingAndSum {

    public static void main(String[] args) {

        // test1 ==> test2

    }

    private static void test1() {
        // count
        long howManyDishes = Dish.menu.stream()
                .collect(Collectors.counting());

        // max or min
        Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = Dish.menu.stream()
                .collect(maxBy(comparator));

        // sum
        int totalCalories = Dish.menu.stream()
                .collect(summingInt(Dish::getCalories));

        // average
        Double collect1 = Dish.menu.stream()
                .collect(averagingInt(Dish::getCalories));

    }

    private static void test2() {

        // IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                .collect(summarizingInt(Dish::getCalories));

    }

    private static void test3() {
        Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));

        Optional<Integer> collect = Dish.menu.stream()
                .map(Dish::getCalories)
                .collect(reducing((d1, d2) -> d1 + d2));

        Optional<Integer> reduce = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce((d1, d2) -> d1 + d2);
    }


}
