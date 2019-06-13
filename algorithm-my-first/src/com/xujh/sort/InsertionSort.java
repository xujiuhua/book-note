package com.xujh.sort;

import java.util.Arrays;

/**
 * <p>
 *     插入排序：左侧的数据陆续归位，列入扑克
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class InsertionSort {

    private final static int[] DATA = new int[]{5, 9, 3, 1, 2, 8, 4, 7, 6};

    private static int[] sort(int[] data) {

        int len = data.length;
        if (len <= 1) {
            return data;
        }

        for (int i = 1; i < len; i++) {
            int target = data[i];
            int j;
            for (j = i; j>0 && data[j-1]>target; j--) {
                // 把位置预留出来
                data[j] = data[j-1];
            }
            //将需要插入的数放入这个位置
            data[j] = target;

        }

        return data;
    }

    public static void main(String[] args) {
        int[] sort = sort(DATA);
        System.out.println("result: " + Arrays.toString(sort));
    }

}
