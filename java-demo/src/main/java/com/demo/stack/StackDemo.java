package com.demo.stack;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        // 栈的基本使用
        Stack<String> stack = new Stack<>();
        stack.add("Tom");
        stack.add("Jack");
        stack.add("Steve");

        while (stack.size() > 0) {
            System.out.println(stack.pop());
            //    Steve Jack Tom
        }
    }
}
