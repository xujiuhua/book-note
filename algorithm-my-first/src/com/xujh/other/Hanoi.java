package com.xujh.other;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */

import java.util.Scanner;

public class Hanoi {
    /**
     * //移动的次数
     */
    static long count;

    /**
     * 汉诺塔算法,将盘子从a移动到c
     *
     * @param n 盘子数目
     * @param a 柱子标识
     * @param b 柱子标识
     * @param c 柱子标识
     */
    static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.printf("第%d次移动：\t%c->%c\n", ++count, a, c);
        } else {
            hanoi(n - 1, a, c, b);
            System.out.printf("第%d次移动：\t%c->%c\n", ++count, a, c);
            hanoi(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        int n;
        count = 0;
        System.out.println("汉诺塔问题求解！");
        System.out.print("请输入盘子的数量：");
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        hanoi(n, 'A', 'B', 'C');
        System.out.printf("求解完毕！总共需要%d步移动！\n", count);
    }

}
