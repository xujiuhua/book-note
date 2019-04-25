package com.xujh.chapter03;

/**
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DoubleCheckedLocking { //1
    private static Instance instance; //2

    public static Instance getInstance() { //3
        if (instance == null) { //4:第一次检验
            synchronized (DoubleCheckedLocking.class) { //5:加锁
                if (instance == null) //6:第二次检验
                    instance = new Instance(); //7:问题根源出在这里
            } //8
        } //9
        return instance; //10
    } //11

    static class Instance {
    }
}
