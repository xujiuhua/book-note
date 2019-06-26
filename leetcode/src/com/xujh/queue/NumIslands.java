package com.xujh.queue;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 非常金典的算法
 * 以每一元素作为起点进行一次深度优先遍历(DFS)，将所遇到的1全部标记为0，
 * 无法进行下去时，计数器加一，代表搜索完一座岛屿，依次类推。
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class NumIslands {

    public int[][] init() {
        int[][] a = new int[4][5];
        for (int i = 0; i < a.length; i++) {
            int[] c = a[i];
            for (int j = 0; j < c.length; j++) {
                a[i][j] = new Random().ints(0, 2).limit(1).sum();
            }
            System.out.println(Arrays.toString(a[i]));
        }
        return a;
    }

    public int helper(int[][] grid) {
        int count = 0;
        if (grid.length == 0) {
            return count;
        }

        for (int i = 0; i < grid.length; i++) {
            int[] c = grid[i];
            for (int j = 0; j < c.length; j++) {
                if (grid[i][j] == 1) {
                    dfsSearch(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfsSearch(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            // down
            dfsSearch(grid, i + 1, j);
            // up
            dfsSearch(grid, i - 1, j);
            // right
            dfsSearch(grid, i, j + 1);
            // left
            dfsSearch(grid, i, j - 1);
        }
    }

    public static void main(String[] args) {
        NumIslands islands = new NumIslands();
        int[][] data = islands.init();
        int count = islands.helper(data);
        System.out.println(count);
    }
}
