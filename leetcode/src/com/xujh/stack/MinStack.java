package com.xujh.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MinStack {
    Stack<Integer> s = new Stack<>();
    Stack<Integer> min = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        s.push(x);
        if (min.isEmpty()) {
            min.push(x);
        } else {
            if (min.peek() > x) {
                min.push(x);
            }
        }
    }

    public void pop() {
        Integer top = s.pop();
        if (!min.isEmpty()) {
            if(top.compareTo(min.peek()) == 0){
                min.pop();
            }
        }
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return min.peek();
    }

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
