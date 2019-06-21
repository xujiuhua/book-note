package com.xujh.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 帕斯卡三角形: 斯卡三角形是排列成三角形的一系列数字。
 * 在帕斯卡三角形中，每一行的最左边和最右边的数字总是 1。
 * 对于其余的每个数字都是前一行中直接位于它上面的两个数字之和。
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                list.add(helper(i, j));
            }

            res.add(list);
        }

        return res;
    }

    public int helper(int row, int column) {
        if (row == 1 || column == row || column == 1) {
            return 1;
        } else {
            return helper(row - 1, column - 1) + helper(row - 1, column);
        }
    }

    /**
     * Awesome
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate2(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        } else if (numRows == 1) {
            List<List<Integer>> triangle = new ArrayList<>();
            List<Integer> row = new ArrayList<>();
            row.add(1);
            triangle.add(row);
            return triangle;
        } else {
            List<List<Integer>> triangle = generate(numRows - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 0; i < numRows - 2; i++) {
                row.add(triangle.get(numRows - 2).get(i) + triangle.get(numRows - 2).get(i + 1));
            }
            row.add(1);
            triangle.add(row);
            return triangle;
        }
    }


    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
//        System.out.println(pascalTriangle.generate(5));
        System.out.println(pascalTriangle.generate2(2));
    }

}
