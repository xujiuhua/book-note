package com.github.book.lambda.tutorial01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 *     演示行为函数优点及使用效果
 *     一共通过七步演变而来
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class FilterApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // First: filter green apples
        filterGreenApples(inventory).forEach(System.out::println);

        // 如果要过滤红色，增加方法filterRedApples，这样造成代码的重复，故使用第二种方法
        // Second: 颜色参数化
        filterApplesByColor(inventory, "green").forEach(System.out::println);
        filterApplesByColor(inventory, "red").forEach(System.out::println);

        // 如果要过滤重量
        filterApplesByWeight(inventory, 150).forEach(System.out::println);
        filterApplesByWeight(inventory, 100).forEach(System.out::println);


        // don’t repeat yourself
        // Third: 增加flag标识，但是这样做太差，一定不要这样做


        // 颜色和重量是两种不同行为
        // Fourth:
        filter(inventory, new AppleColorPredicate()).forEach(System.out::println);
        filter(inventory, new AppleWeightPredicate()).forEach(System.out::println);


        // Fifth: 使用匿名内部类
        filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "red".equals(a.getColor());
            }
        }).forEach(System.out::println);


        // Sixth: 使用Lambda
        filter(inventory, (Apple apple) -> "red".equals(apple.getColor())).forEach(System.out::println);


        // ApplePredicate 只能过滤Apple，增加泛型，过滤其他对象: 采用Java8
        // Seventh:
        filter2(inventory, (Apple apple) -> "green".equals(apple.getColor())).forEach(System.out::println);

    }

    private static List<Apple> filter2(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( apple.getWeight() > weight ){ result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}
