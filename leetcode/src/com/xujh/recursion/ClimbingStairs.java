package com.xujh.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ClimbingStairs {

    Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs(int n) {
        return helper(n);
    }

    public int helper(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result;
        if (n <= 3) {
            result = n;
        } else {
            result = helper(n - 1) + helper(n - 2);
        }
        cache.put(n, result);
        return result;

    }


    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        int step = cs.climbStairs(5);
        System.out.println(step);
    }
}
