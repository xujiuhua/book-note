package com.xujh.recursion;

import java.util.Arrays;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SwapNodes {

    public static void main(String[] args) {
        ListNode listNode = init();
        System.out.println(listNode);
        System.out.println(helper(listNode));
    }

    private static ListNode helper(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode next = head.next;

        if (next == null) {
            return head;
        }

        ListNode nNext = next.next;

        head.next = null;
        next.next = head;

        if (nNext == null) {
            return next;
        }

        head.next = helper(nNext);
        return next;

    }

    private static ListNode init() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;

        return a;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
