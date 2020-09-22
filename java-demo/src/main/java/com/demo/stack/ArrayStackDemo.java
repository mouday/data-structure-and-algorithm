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
