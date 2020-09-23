package com.demo.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        stack.list();

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.list();
    }
}


class LinkedListStack {
    private StackNode top;

    // 链表无限扩充不会满
    // public boolean isFull(){}

    // 判空
    public boolean isEmpty() {
        return top == null;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    // 入栈
    public void add(int data) {
        StackNode node = new StackNode(data);
        node.next = top;
        top = node;
    }

    // 显示栈数据
    public void list() {
        StackNode temp = top;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class StackNode {
    public int data;
    public StackNode next;

    public StackNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "data=" + data +
                '}';
    }
}