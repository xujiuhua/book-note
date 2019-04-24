package com.xujh.chapter03;

/**
 * <p>
 *
 * 写final域的重排序规则禁止对final域的写重排序到构造函数之外，这个规则的实现主要包含了两个方面：
 * JMM禁止编译器把final域的写重排序到构造函数之外；
 * 编译器会在final域写之后，构造函数return之前，插入一个storestore屏障（关于内存屏障可以看这篇文章）。这个屏障可以禁止处理器把final域的写重排序到构造函数之外。
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class FinalExample {

    int i;
    final int j;

    static FinalExample obj;

    public FinalExample() {
        i = 1;
        j = 2;
    }

    public static void writer() {
        obj = new FinalExample();
    }

    public static void reader() {
        // obj 可能是一个不完整的对象，但是j肯定有初始化，i不一定初始化赋值完成
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
        // a值有可能是未初始化的值0
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }

    public static void main(String[] args) {

        new Thread(FinalExample::writer).start();
        new Thread(FinalExample::reader).start();


    }
}
