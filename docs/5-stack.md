## 栈

举例：计算表达式结果

```
7 * 2 * 2 - 5 + 1 - 5 + 3 -3
```

1、基本概念

栈 stack 先入后出（FILO first in last out）的有序列表

栈的插入和删除只能在线性表的同一端进行

栈顶：变化端(允许插入和删除)
栈底：固定端

出栈 pop、入栈 push

2、栈的应用场景

- 子程序的调用
- 递归调用
- 表达式转换（中缀表达式转后缀表达式）与求值
- 二叉树遍历
- 图的深度优先搜索算法 depth-first

3、实现栈的思路

1. 使用数组实现栈
2. 定义一个 top 表示栈顶，初始化 top=-1
3. 入栈操作，top++;stack[top]=data;
4. 出栈操作，data=stack[top];top--;

4、代码实现栈

```java
package com.demo.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());

        stack.list();

    }
}

class ArrayStack{
    private int maxSize;  // 栈容量
    private int top = -1; // 栈顶指针
    private int[] arr;    // 栈容器

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
    }

    // 判满
    public boolean isFull(){
        return this.top == this.maxSize - 1;
    }

    //判空
    public boolean isEmpty(){
        return this.top == -1;
    }

    // 入栈
    public boolean push(int data){
        if(this.isFull()){
            return false;
        }
        this.top++;
        this.arr[this.top] = data;
        return true;
    }

    // 出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        int data = arr[top];
        top--;
        return data;
    }

    // 显示栈内数据
    public void list(){
        if(isEmpty()){
            System.out.println("stack is empty");
            return;
        }

        int index = top;
        while (index > -1){
            System.out.printf("arr[%d] = %d\n", index, arr[index]);
            index--;
        }
    }
}

```

5、交互测试
```java
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
```

6、用链表模拟栈

```java
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
```