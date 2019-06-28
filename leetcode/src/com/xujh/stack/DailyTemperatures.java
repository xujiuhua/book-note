package com.xujh.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>
 * 每日温度
 * https://leetcode-cn.com/explore/learn/card/queue-stack/218/stack-last-in-first-out-data-structure/879/
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DailyTemperatures {

    public int[] helper(int[] t) {
        if (t == null || t.length == 0) {
            return null;
        }

        int[] res = new int[t.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = t.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && t[i] >= t[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures t = new DailyTemperatures();
        int[] ints = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] helper = t.helper(ints);
        System.out.println(Arrays.toString(helper));
    }

}
