package com.xujh.stack;

import java.util.Stack;

/**
 * <p>
 * 有效的括号
 * https://leetcode-cn.com/explore/learn/card/queue-stack/218/stack-last-in-first-out-data-structure/878/
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ValidSymbol {


    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        if (s == null || "".equals(s)) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidSymbol symbol = new ValidSymbol();
        boolean valid = symbol.isValid("{[()]}");
        System.out.println(valid);
        boolean valid2 = symbol.isValid("{[(})]}");
        System.out.println(valid2);
    }
}
