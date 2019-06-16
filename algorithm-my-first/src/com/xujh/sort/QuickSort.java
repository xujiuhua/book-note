package com.xujh.sort;

import java.util.Arrays;

/**
 * <p>
 * 快速排序：每次从序列中随机选择一个基准值，然后把基准值两变的数据排序（基准值除外）
 * https://www.cnblogs.com/MOBIN/p/4681369.html
 * 关键点，定义两个指针位置
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class QuickSort {
    private final static int[] DATA = new int[]{5, 9, 3, 1, 2, 8, 4, 7, 6};

    private static void sort(int[] data, int l, int r) {
        int left = l;
        int right = r;
        if (left > right) {
            return;
        }
        // 选第一个作为基准值
        int pivot = data[left];
        while (left != right) {
            while (right > left && data[right] >= pivot) {
                right--;
            }
            data[left] = data[right];
            while (left < right && data[left] <= pivot) {
                left++;
            }
            data[right] = data[left];
        }
        //基准元素归位
        data[right] = pivot;
        //对基准元素左边的元素进行递归排序
        sort(data, l, left - 1);
        //对基准元素右边的进行递归排序
        sort(data, right + 1, r);

    }

    public static void main(String[] args) {
        sort(DATA, 0, DATA.length - 1);
        System.out.println(Arrays.toString(DATA));
    }

}
