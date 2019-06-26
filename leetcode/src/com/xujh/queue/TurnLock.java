package com.xujh.queue;

import java.util.*;

/**
 * <p>
 * 打开转盘锁
 * https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/873/
 * <p>
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class TurnLock {


    /**
     * 单向搜索
     *
     * @param deadends
     * @param target
     * @return
     */
    private int helper(String[] deadends, String target) {
        List<String> visited = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {

                final String poll = q.poll();

                // 已遍历过
                if (visited.contains(poll)) {
                    continue;
                }

                // 找到目标
                if (Objects.equals(poll, target)) {
                    System.out.println("Find Target.");
                    System.out.println(visited);
                    return count;
                }

                visited.add(poll);

                // 开始遍历所有邻近锁
                findNeibors(Objects.requireNonNull(poll)).forEach(a -> {
                    // 死亡锁限制
                    final boolean dead = Arrays.asList(deadends).contains(poll);
                    if (!dead) {
                        q.offer(a);

                    }
                });
            }

            count++;

        }
        System.out.println("Not Find Target.");
        return -1;
    }

    /**
     * 遍历当前锁的所有邻近锁
     */
    private List<String> findNeibors(String currLock) {
        int size = currLock.length();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // 执行+1操作
            String temp = currLock;
            char[] cs = temp.toCharArray();
            if (cs[i] == '9') {
                cs[i] = '0';
            } else {
                cs[i] = (char) (cs[i] + 1);
            }

            temp = new String(cs);
            result.add(temp);
            // 执行-1操作
            temp = currLock;
            cs = temp.toCharArray();
            if (cs[i] == '0') {
                cs[i] = '9';
            } else {
                cs[i] = (char) (cs[i] - 1);
            }
            temp = new String(cs);
            result.add(temp);
        }
        return result;
    }

    /**
     * 双向搜索
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        String init = "0000";
        if (dead.contains(init) || dead.contains(target)) {
            return -1;
        }

        if (target.equals(init)) {
            return 0;
        }

        Set<String> set1 = new HashSet<>();
        set1.add(init);
        Set<String> set2 = new HashSet<>();
        set2.add(target);
        int count = 0;

        while (!set1.isEmpty() && !set2.isEmpty()) {
            // 将最小的集合遍历
            if (set1.size() > set2.size()) {
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            Set<String> set3 = new HashSet<>();
            for (String curLock : set1) {
                List<String> neibors = findNeibors(curLock);
                for (String nextLock : neibors) {
                    // 如果set2中包含了这个Lock,则表示初始与目标在途中相遇到了
                    if (set2.contains(nextLock)) {
                        return count + 1;
                    }
                    if (!dead.contains(nextLock) && !visited.contains(nextLock)) {
                        visited.add(nextLock);
                        set3.add(nextLock);
                    }
                }
            }
            count++;
            set1 = set3;
        }
        return -1;
    }

    public static void main(String[] args) {

        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        TurnLock lock = new TurnLock();
        int step = lock.helper(deadends, target);
        System.out.println(step);

        System.out.println(lock.openLock(deadends, target));

    }
}
