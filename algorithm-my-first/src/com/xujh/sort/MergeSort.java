package com.xujh.sort;

import java.util.Arrays;

/**
 * <p>
 * 归并排序：分成长度相同的两个子序列，当无法继续往下分时，就对子序进行归并
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MergeSort {

    private final static int[] DATA = new int[]{5, 9, 3, 1, 2, 8, 4, 7, 6};
    private static int c = 0;

    private static void mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort(data, l, mid);
        sort(data, mid + 1, r);
        merge(data, l, mid, r);

    }

    private static void merge(int[] arr, int l, int mid, int r) {
        System.out.println("----------------------------------" + c++);
        System.out.println("M-->" + mid);
        System.out.println("L-->" + l + Arrays.toString(Arrays.copyOfRange(arr, l, mid+1)));
        System.out.println("R-->" + r + Arrays.toString(Arrays.copyOfRange(arr, mid+1, r+1)));
        int[] temp = new int[r - l + 1];
        int k = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            temp[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            temp[k++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[k++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (int i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        mergeSort(DATA);
        System.out.println(Arrays.toString(DATA));
    }
}
