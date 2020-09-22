## 栈

计算表达式结果
```
7 * 2 * 2 - 5 + 1 - 5 + 3 -3
```

栈 stack 先入后出（FILO first in last out）的有序列表 

栈的插入和删除只能在线性表的同一端进行

栈顶：变化端(允许插入和删除)
栈底：固定端

出栈pop、入栈push

栈的应用场景

- 子程序的调用
- 递归调用
- 表达式转换（中缀表达式转后缀表达式）与求值
- 二叉树遍历
- 图的深度优先搜索算法depth-first


实现栈的思路

1. 使用数组实现栈
2. 定义一个top表示栈顶，初始化top=-1
3. 入栈操作，top++;stack[top]=data;
4. 出栈操作，data=stack[top];top--;

代码实现栈
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



















