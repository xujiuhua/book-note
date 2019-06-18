package com.xujh.sort;

import com.xujh.heap.Heap;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 *     堆排序：循环取出根节点，重构堆
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class HeapSort {
    public static void main(String[] args) {

        Heap heap = new Heap(10);
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            heap.add(random.ints(i*10, i*20).limit(1).sum());
        }
        heap.displayHeap();


        int[] a = new int[9];
        int j = 0;
        while (!heap.isEmpty()) {
            a[j++] = heap.remove().getKey();
        }
        System.out.println(Arrays.toString(a));

    }
}
