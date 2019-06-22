package com.xujh.recursion;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode init = reverseLinkedList.init();
        ListNode listNode = reverseLinkedList.reverseList(init);
        System.out.println(listNode);
    }

    public ListNode reverseList(ListNode head) {


        ListNode next = head.next;

        if (next == null) {
            return head;
        }


        ListNode c = reverseList(next);
        head.next.next = head;
        head.next = null;


        return c;
    }

    private ListNode init() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
//        ListNode e = new ListNode(5);
//        ListNode f = new ListNode(6);

        a.next = b;
        b.next = c;
        c.next = d;
//        d.next = e;
//        e.next = f;

        return a;
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
}

