package com.xujh.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/1660/
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class PascalTriangle2 {

    /**
     * Note that the row index starts from 0.
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list = new ArrayList<>();
        return helper(rowIndex, list);
    }

    public List<Integer> helper(int rowIndex, List<List<Integer>> list) {
        if (rowIndex == 0) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            return row;
        } else {
            List<Integer> prevRow = helper(rowIndex - 1, list);
            list.add(prevRow);
            List<Integer> row = new ArrayList<>();
            row.add(0, 1);
            for (int i = 0; i < rowIndex - 1; i++) {
                row.add(list.get(rowIndex - 1).get(i) + list.get(rowIndex - 1).get(i + 1));
            }
            row.add(rowIndex, 1);
            return row;
        }
    }

    public static void main(String[] args) {

        PascalTriangle2 pascalTriangle2 = new PascalTriangle2();
        List<Integer> row = pascalTriangle2.getRow(3);
        System.out.println(row);
    }

}
