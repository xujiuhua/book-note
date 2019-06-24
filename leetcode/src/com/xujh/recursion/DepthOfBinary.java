package com.xujh.recursion;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DepthOfBinary {

    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int d) {

        if(root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        d++;

        if(left == null && right == null) {
            return d;
        }

        if(left == null) {
            return helper(right, d);
        }
        if(right == null) {
            return helper(left, d);
        }


        int ld = helper(left, d);
        int rd = helper(right, d);

        return ld > rd ? ld : rd;

    }

    public int maxDepth2(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }

    public TreeNode init() {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;

        TreeNode rightP = left;

        left = new TreeNode(15);
        right = new TreeNode(7);

        rightP.left = left;
        rightP.right = right;

        return root;
    }

    public static void main(String[] args) {
        DepthOfBinary depthOfBinary = new DepthOfBinary();
        TreeNode root = depthOfBinary.init();
        int depth = depthOfBinary.maxDepth(root);
        System.out.println(depth);

        int depth2 = depthOfBinary.maxDepth2(root);
        System.out.println(depth2);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
