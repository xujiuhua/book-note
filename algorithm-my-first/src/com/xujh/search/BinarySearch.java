package com.xujh.search;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BinarySearch {

    private static int search(int[] a, int fromIndex, int toIndex, int k) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = a[mid];
            if (k < midVal) {
                high = mid - 1;
            } else if (k > midVal){
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int index = search(a, 0, a.length, 0);
        System.out.println(index);
    }
}
