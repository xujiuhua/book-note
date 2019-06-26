package com.xujh.queue;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 完全平方数
 * https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/874/
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class NumSquares {

    private int helper(int n) {
        if (n == 0) {
            return 0;
        }

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!q.isEmpty()) {
            Pair<Integer, Integer> front = q.poll();
            int num = front.getKey();
            int step = front.getValue();

            if (num == 0) {
                return step;
            }

            for (int i = 1; num - i * i >= 0; i++) {
                int a = num - i * i;
                if (!visited[a]) {
                    if (a == 0) {
                        return step + 1;
                    }
                    q.offer(new Pair(a, step + 1));
                    visited[a] = true;
                }
            }
        }
        return 0;


    }


    public static void main(String[] args) {
        NumSquares ns = new NumSquares();
        System.out.println(ns.helper(13));
    }
}
