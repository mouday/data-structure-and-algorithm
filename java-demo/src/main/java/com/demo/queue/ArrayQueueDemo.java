package com.demo.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 */
class ArrayQueue {

    private int size; // 队列大小
    private int[] arr; // 队列数组实现

    private int rear = -1; // 队尾
    private int front = -1; // 队首

    public ArrayQueue(int size) {
        this.size = size;
        this.arr = new int[this.size];
    }

    /**
     * 判断对垒是否满
     *
     * @return
     */
    public boolean isFull() {
        return this.size == this.rear + 1;
    }

    /**
     * 判断队列是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    /**
     * 数据入队
     *
     * @param data
     * @return
     */
    public boolean add(int data) {
        if (this.isFull()) {
            return false;
        }

        this.rear++;
        this.arr[this.rear] = data;
        return true;
    }

    /**
     * 数据出队
     *
     * @return
     */
    public int get() {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        this.front++;
        return this.arr[this.front];
    }


    /**
     * 队首元素
     *
     * @return
     */
    public int head() {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        return this.arr[this.front + 1];
    }

    /**
     * 显示队列全部元素
     */
    public void show() {
        for (int i = 0; i < this.size; i++) {
            System.out.printf("arr[%d] = %d\n", i, this.arr[i]);
        }
    }
}

public class ArrayQueueDemo {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);

        // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        char key;

        boolean loop = true;
        while (loop) {
            // 菜单
            System.out.println("s(show): show queue");
            System.out.println("a(add): add data into queue");
            System.out.println("g(get): get data from queue");
            System.out.println("e(exit): exit");
            System.out.println("h(head): get head of queue");

            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    try {
                        System.out.println(queue.get());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(queue.head());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
        System.out.println("已退出");

    }
}
