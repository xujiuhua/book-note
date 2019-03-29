package com.xujh;

import java.util.Arrays;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }


    public static void main(String[] args) {
        UnsafeStates unsafeStates = new UnsafeStates();
        unsafeStates.states = new String[]{"hello", "world"};

        UnsafeStates unsafeStates2 = new UnsafeStates();
        System.out.println(Arrays.toString(unsafeStates2.states));
    }
}
