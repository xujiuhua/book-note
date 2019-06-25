package com.xujh.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MyQueue {

    private List<Integer> data;
    private int startPoint;

    public MyQueue() {
        this.data = new ArrayList<>();
        this.startPoint = 0;
    }

    /**
     * Insert
     * @param x
     * @return
     */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    }

    /**
     * Delete
     *
     * @return
     */
    public boolean deQueue() {
        if (this.isEmpty()) {
            return false;
        }
        startPoint++;
        return true;
    }

    /** Checks whether the queue is empty or not. */
    public boolean isEmpty() {
        return startPoint >= data.size();
    }

    /** Get the front item from the queue. */
    public int front() {
        return data.get(startPoint);
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(4);
        if (!q.isEmpty()) {
            System.out.println(q.front());
        }
        q.deQueue();
        if (!q.isEmpty()) {
            System.out.println(q.front());
        }
        q.deQueue();
        if (!q.isEmpty() ) {
            System.out.println(q.front());
        }
    }
}
