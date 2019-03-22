package com.github.book.lambda.tutorial01;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple a) {
        return a.getWeight() > 150;
    }
}
