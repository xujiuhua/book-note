package com.github.book.jia.lambda.tutorial01;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple a) {
        return "green".equals(a.getColor());
    }
}
