package com.xujh.sort;

import java.util.Arrays;

/**
 * <p>
 * 冒泡排序：从序列右边开始比较两个数字的大小，再根据结果交换这两个数字的位置
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BubbleSort {

    private final static int[] DATA = new int[]{5, 9, 3, 1, 2, 8, 4, 7, 6};

    private static int[] sort(int[] data) {

        int len = data.length;
        if (len <= 1) {
            return data;
        }

        for (int i = 0; i < len; i++) {

            for (int j = len-1; j > i ; j--) {
                int prevIdx = j - 1;
                if (data[j] < data[prevIdx]) {
                    int tmp = data[j];
                    data[j] = data[prevIdx];
                    data[prevIdx] = tmp;
                }
            }
            System.out.println(i + "  -->  " + Arrays.toString(data));
        }
        return data;

    }

    public static void main(String[] args) {
        int[] sort = sort(DATA);
        System.out.println("result: " + Arrays.toString(sort));
    }
}
