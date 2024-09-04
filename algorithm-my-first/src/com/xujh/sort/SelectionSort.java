package com.xujh.sort;

import java.util.Arrays;

/**
 * <p>
 *     选择排序：从待排序的数据中寻找最小值，将其与序列最左边的数交换.
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SelectionSort {

    private static final int[] DATA = new int[]{5, 9, 3, 1, 2, 8, 4, 7, 6};

    private static int[] sort(int[] data) {
        for (int i = 0, len = data.length; i < len; i++) {
            int minIndex = i;
            for (int j = i+1; j < len; j++) {
                if (data[j] < data[minIndex]) {
                    // 记下目前最小值所在下标
                    minIndex = j;
                }
            }
            // 交换
            if (i != minIndex) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
                System.out.println(i + "  -->  " + Arrays.toString(data));
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int[] sort = sort(DATA);
        System.out.println("result: " + Arrays.toString(sort));
    }
}
