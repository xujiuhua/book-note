package com.cj.fx15.func;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class FuncTest {
    public static void main(String[] args) {

        final String m1 = ArrayAlg.<String>getMiddle("hello", "world");
        final String m2 = ArrayAlg.getMiddle("hello", "world");

        final double middle = ArrayAlg.getMiddle(3.14, 33.45, 0.67);

    }
}
