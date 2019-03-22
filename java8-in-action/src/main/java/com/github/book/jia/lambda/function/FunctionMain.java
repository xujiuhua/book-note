package com.github.book.jia.lambda.function;

import java.util.function.*;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class FunctionMain {
    public static void main(String[] args) {

        // Predicate<T>: T -> boolean
        Predicate<Integer> predicate = x -> x > 0;
        System.out.println(predicate.test(100));

        IntPredicate intPredicate = x -> x > 0;
        System.out.println(intPredicate.test(100));

        // Consumer<T>: T -> void
        Consumer<Integer> consumer = x -> System.out.println(x + 2);
        consumer.accept(100);

        // Function<T, R>: T -> R
        Function<Integer, String> function = a -> String.format("format: %s", a);
        System.out.println(function.apply(100));

        IntFunction<String> intFunction = a -> String.format("format: %s", a + 2);
        System.out.println(intFunction.apply(200));

        // Supplier<T>: () -> T
        Supplier<Integer> supplier = () -> 123;
        System.out.println("supplier: " + supplier.get());

        BooleanSupplier booleanSupplier = () -> 100 > 200;
        System.out.println(booleanSupplier.getAsBoolean());

        // BinaryOperator<T>: (T, T) -> T
        BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
        System.out.println(add.apply(3, 6));

        // other omit

    }
}
