package com.xujh.diagram;

import java.util.*;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DFS {
    public static void main(String[] args) {

        Map<String, String[]> map = new HashMap<>();
        map.put("A", new String[]{"B", "C"});
        map.put("B", new String[]{"E"});
        map.put("C", new String[]{"D", "F"});
        map.put("D", new String[]{"E"});
        map.put("E", new String[]{"H"});
        map.put("F", new String[]{"E", "G"});
        map.put("H", new String[]{});

        // 获取从A到H的路径
        Node target = findTarget("A", "G", map);
        // 打印路径
        printPath(target);
    }

    private static Node findTarget(String start, String target, Map<String, String[]> map) {
        List<String> hasSearchList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(start, null));
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            final String nodeId = node.id;
            if (hasSearchList.contains(nodeId)) {

                String[] children = map.get(nodeId);
                if (Objects.isNull(children) || hasSearchList.containsAll(Arrays.asList(children))) {
                    stack.pop();
                    continue;
                }

                String child = Arrays.stream(children)
                        .filter(a -> !hasSearchList.contains(a))
                        .findFirst()
                        .get();
                stack.push(new Node(child, node));
                continue;
            }

            System.out.println("判断节点:" + nodeId);
            if (target.equals(nodeId)) {
                return node;
            }

            hasSearchList.add(nodeId);
            String[] children = map.get(nodeId);
            if (Objects.nonNull(children) && children.length > 0) {
                for (String child : children) {
                    if (!hasSearchList.contains(child)) {
                        stack.push(new Node(child, node));
                        break;
                    }
                }
            } else {
                stack.pop();
            }
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
            System.out.print("步数：" + path);
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
