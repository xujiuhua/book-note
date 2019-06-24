package com.xujh.recursion;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Main {

    private static int helper_non_tail_recursion(int start, int [] ls) {
        if (start >= ls.length) {
            return 0;
        }
        // not a tail recursion because it does some computation after the recursive call returned.
        return ls[start] + helper_non_tail_recursion(start+1, ls);
    }

    public static int sum_non_tail_recursion(int [] ls) {
        if (ls == null || ls.length == 0) {
            return 0;
        }
        return helper_non_tail_recursion(0, ls);
    }

    //---------------------------------------------

    private static int helper_tail_recursion(int start, int [] ls, int acc) {
        if (start >= ls.length) {
            return acc;
        }
        // this is a tail recursion because the final instruction is the recursive call.
        return helper_tail_recursion(start+1, ls, acc+ls[start]);
    }

    public static int sum_tail_recursion(int [] ls) {
        if (ls == null || ls.length == 0) {
            return 0;
        }
        return helper_tail_recursion(0, ls, 0);
    }

    public static void main(String[] args) {
        int[] ls = new int[]{1, 2, 3, 4};
        System.out.println(sum_non_tail_recursion(ls));
        System.out.println(sum_tail_recursion(ls));
    }
}
