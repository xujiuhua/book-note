package com.xujh.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 贝尔曼 - 福特算法: 在图中请最短路径问题算法
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class BellmanFord {


    public static void main(String[] args) {

        //创建图
        Edge ab = new Edge("A", "B", 2);
        Edge ac = new Edge("A", "C", 4);
        Edge bc = new Edge("B", "C", 3);
        Edge be = new Edge("B", "E", 1);
        Edge ed = new Edge("E", "D", 1);
        Edge dc = new Edge("D", "C", 5);
        Edge bd = new Edge("B", "D", 3);
        Edge db = new Edge("D", "B", 1);

        // 需要按图中的步骤步数顺序建立数组，否则就是另外一幅图了，
        // 从起点A出发，步骤少的排前面
        Edge[] edges = new Edge[]{ab, ac, bc, be, bd, ed, dc, db};

        // 存放到各个节点所需要消耗的时间
        HashMap<String, Integer> costMap = new HashMap<>();
        // 到各个节点对应的父节点
        HashMap<String, String> parentMap = new HashMap<>();

        // 初始化各个节点所消费的，当然也可以再遍历的时候判断下是否为Null
        costMap.put("A", 0);
        costMap.put("B", Integer.MAX_VALUE);
        costMap.put("C", Integer.MAX_VALUE);
        costMap.put("D", Integer.MAX_VALUE);
        costMap.put("E", Integer.MAX_VALUE);

        for (int i = 0; i < costMap.size(); i++) {
            boolean hasChange = false;
            for (int j = 0; j < edges.length; j++) {
                Edge edge = edges[j];
                // 该边起点目前总的路径大小
                int startPCost = costMap.get(edge.getStartP()) == null ? 0 : costMap.get(edge.getStartP());
                // 该边终点目前总的路径大小
                int endPCost = costMap.get(edge.getEndP()) == null ? Integer.MAX_VALUE : costMap.get(edge.getEndP());
                // 如果该边终点目前的路径大小 > 该边起点的路径大小 + 该边权重 ，说明有更短的路径了
                if (endPCost > startPCost + edge.getWeight()) {
                    costMap.put(edge.getEndP(), startPCost + edge.getWeight());
                    parentMap.put(edge.getEndP(), edge.getStartP());
                    hasChange = true;
                }
            }
            // 如果经过一轮循环，没有交换任何值，已经求出解了，提前退出循环
            if (!hasChange) {
                break;
            }
        }

        //再进行一次判断是否存在负环路
        boolean hasRing = false;
        for (int j = 0; j < edges.length; j++) {
            Edge edge = edges[j];
            int startPointCost = costMap.get(edge.getStartP()) == null ? 0 : costMap.get(edge.getStartP());
            int endPointCost = costMap.get(edge.getEndP()) == null ? Integer.MAX_VALUE : costMap.get(edge.getEndP());
            if (endPointCost > (startPointCost + edge.getWeight())) {
                System.out.print("\n图中存在负环路，无法求解\n");
                hasRing = true;
                break;
            }
        }

        if (!hasRing) {
            //打印出到各个节点的最短路径
            for (String key : costMap.keySet()) {
                System.out.print("\n到目标节点" + key + "最低耗费:" + costMap.get(key));
                if (parentMap.containsKey(key)) {
                    List<String> pathList = new ArrayList<>();
                    String parentKey = parentMap.get(key);
                    while (parentKey != null) {
                        pathList.add(0, parentKey);
                        parentKey = parentMap.get(parentKey);
                    }
                    pathList.add(key);
                    String path = "";
                    for (String k : pathList) {
                        path = path.equals("") ? path : path + " --> ";
                        path = path + k;
                    }
                    System.out.print("，路线为" + path);
                }
            }
        }
    }

    static class Edge {

        private String startP;
        private String endP;
        private int weight;

        public Edge(String startP, String endP, int weight) {
            this.startP = startP;
            this.endP = endP;
            this.weight = weight;
        }

        public String getStartP() {
            return startP;
        }

        public String getEndP() {
            return endP;
        }

        public int getWeight() {
            return weight;
        }
    }

}

