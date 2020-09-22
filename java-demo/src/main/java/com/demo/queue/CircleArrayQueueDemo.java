package com.demo.queue;


import java.util.Scanner;

/**
 * 环形数组队列
 */
class CircleArrayQueue {

    private int size;

    private int front = 0; // 指向队首

    private int rear = 0; // 指向队尾下一个元素, 最后一个地址不存值

    private int[] arr;

    public CircleArrayQueue(int size) {
        this.size = size;
        this.arr = new int[this.size];
    }

    public boolean isFull() {
        return (this.rear + 1) % this.size == this.front;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean add(int data) {
        if (this.isFull()) {
            return false;
        }
        this.arr[this.rear] = data;
        this.rear = (this.rear + 1) % this.size;
        return true;
    }

    public int get() {
        if (this.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }
        int value = this.arr[this.front];

        this.front = (this.front + 1) % this.size;
        return value;
    }

    public int head() {
        if (this.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }
        return this.arr[this.front];
    }

    public int total() {
        return (this.size + (this.rear - this.front)) % this.size;
    }

    public void show() {
        for (int i = this.front; i < this.front + this.total(); i++) {
            int point = i % this.size;
            System.out.printf("arr[%d] = %d\n", point, this.arr[point]);
        }
    }
}


public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);

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
