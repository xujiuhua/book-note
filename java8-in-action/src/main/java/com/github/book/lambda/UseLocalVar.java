package com.github.book.lambda;

/**
 * <p>  </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class UseLocalVar {
    public static void main(String[] args) {

        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        new Thread(r).start();

        // error
        // portNumber = 11;


    }
}
