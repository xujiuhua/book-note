package com.xujh.chapter03;

/**
 * <p>
 *
 * 1. 类变量：必须要在静态初始化块中指定初始值或者声明该类变量时指定初始值，而且只能在这两个地方之一进行指定；
 * 2. 实例变量：必要要在非静态初始化块，声明该实例变量或者在构造器中指定初始值，而且只能在这三个地方进行指定。
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class FinalMemberVariable {

    private final int i = 6;
    private final String s;
    private final static boolean b;
    private final double d;

    // 未在任何地方赋值，错误
    // private final char c;

    {
        s = "实例变量可以在初始化块赋值";
    }

    static {
        // 静态变量可以在静态初始化块赋值
        b = true;

        // 非静态变量不能在静态初始块中赋值
        // s = "";
    }

    private FinalMemberVariable() {
        // 实例变量可以在初始化块中赋值
        d = 1.0D;

        // 已经赋值不能在修改
        // i = 10;
    }

    private void a() {
        // 实例方法不能为final修饰的变量赋值
        // c = 'a';
    }
}
