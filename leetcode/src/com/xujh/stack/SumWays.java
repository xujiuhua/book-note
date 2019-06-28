package com.xujh.stack;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SumWays {

    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        helper(nums, 0, S);
        return count;
    }

    /**
     * 递归
     * @param nums
     * @param idx
     * @param S
     */
    public void helper(int[] nums, int idx, int S) {

        if (idx == nums.length) {
            if (S == 0) {
                count++;
            }
            return;
        }
        int num = nums[idx];
        helper(nums, idx+1, S-num);
        helper(nums, idx+1, S+num);
    }

    public static void main(String[] args) {
        SumWays ways = new SumWays();
        int[] a = {1,1,1,1,1};
        int count = ways.findTargetSumWays(a, 3);
        System.out.println(count);


    }
}
