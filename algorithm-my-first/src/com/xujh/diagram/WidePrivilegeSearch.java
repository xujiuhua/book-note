package com.xujh.diagram;

import java.util.*;

/**
 * <p>
 * 广度优先搜索：从离起点近的位置开始搜索
 * http://cmsblogs.com/?p=4657
 * https://blog.csdn.net/a8082649/article/details/81395359
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class WidePrivilegeSearch {

    private static Map<String, String[]> map = new HashMap<>();


    public static void main(String[] args) {

        map.put("A", new String[]{"B", "C"});
        map.put("B", new String[]{"E"});
        map.put("C", new String[]{"D", "F"});
        map.put("D", new String[]{"E"});
        map.put("E", new String[]{"H"});
        map.put("F", new String[]{"E", "G"});
        map.put("H", new String[]{});

        // 获取从A到H的最短路径
        Node target = findTarget("A", "H", map);
        printPath(target);

    }

    private static Node findTarget(String start, String target, Map<String, String[]> map) {
        List<String> hasSearchList = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, null));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            String nodeId = node.id;
            if (hasSearchList.contains(nodeId)) {
                continue;
            }
            System.out.println("判断节点:" + nodeId);
            if (target.equals(nodeId)) {
                return node;
            }
            hasSearchList.add(nodeId);
            String[] children = map.get(nodeId);
            Arrays.stream(children)
                    .forEach(a -> queue.offer(new Node(a, node)));
        }
        return null;
    }


    private static void printPath(Node target) {
        if (null != target) {
            System.out.println("找到目标节点:" + target.id);
            List<Node> searchPath = new ArrayList<>();
            searchPath.add(target);
            Node node = target.parent;
            while (null != node) {
                searchPath.add(node);
                node = node.parent;
            }
            String path = "";
            for (int i = searchPath.size() - 1; i >= 0; i--) {
                path += searchPath.get(i).id;
                if (i != 0) {
                    path += "-->";
                }
            }
            System.out.print("步数最短：" + path);
        } else {
            System.out.println("未找到了目标节点");
        }
    }

    static class Node {
        private String id;
        private Node parent;

        Node(String id, Node parent) {
            this.id = id;
            this.parent = parent;
        }
    }

}


