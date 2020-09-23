package com.demo.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        Scanner scanner = new Scanner(System.in);
        String key = ""; // 接收输入值
        boolean loop = true; // 控制退出菜单


        while (loop) {
            System.out.println("show: 显示栈信息");
            System.out.println("exit: 退出程序");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");

            key = scanner.nextLine();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    int data = scanner.nextInt();
                    stack.push(data);
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("bye~");

    }
}

class ArrayStack {
    private int maxSize;  // 栈容量
    private int top = -1; // 栈顶指针
    private int[] arr;    // 栈容器

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
    }

    // 判满
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    //判空
    public boolean isEmpty() {
        return this.top == -1;
    }

    // 入栈
    public boolean push(int data) {
        if (this.isFull()) {
            return false;
        }
        this.top++;
        this.arr[this.top] = data;
        return true;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int data = arr[top];
        top--;
        return data;
    }

    // 显示栈内数据
    public void list() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return;
        }

        int index = top;
        while (index > -1) {
            System.out.printf("arr[%d] = %d\n", index, arr[index]);
            index--;
        }

    }

}
