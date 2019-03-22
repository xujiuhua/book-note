package com.github.book.stream.map;

import com.github.book.stream.Dish;

import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BoxedMain {

    public static void main(String[] args) {
        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);

        Stream  <Integer> boxed = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .boxed()
                .filter(a -> a > 500);
        boxed.forEach(System.out::println);
    }
}
