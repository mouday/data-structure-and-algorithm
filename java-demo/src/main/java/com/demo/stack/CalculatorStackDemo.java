package com.demo.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorStackDemo {
    public static void main(String[] args) {
        CalculatorStack<Integer> numberStack = new CalculatorStack<>();
        CalculatorStack<String> operatorStack = new CalculatorStack<>();

        String expression = "3+2*6-2";

        // 遍历表达式
        int index = 0;

        while (index < expression.length()) {
            String op = expression.substring(index, index + 1);
            System.out.println(op);

            // 处理操作符
            if (CalculatorUtil.isOperator(op)) {
                // 操作符栈为空直接入栈
                if (operatorStack.isEmpty()) {
                    operatorStack.push(op);
                }
                // 如果当前操作符的优先级小于或等于栈中的操作符，
                // 就从数栈中pop弹出两个数，从符号栈中弹出一个操作符，进行运算，
                // 计算结果入数栈，将当前操作符入符号栈
                else if (CalculatorUtil.priority(op) <= CalculatorUtil.priority(operatorStack.peek())) {
                    int num1 = numberStack.pop();
                    int num2 = numberStack.pop();
                    String stackOp = operatorStack.pop();
                    int ret = CalculatorUtil.calculate(num2, stackOp, num1);
                    numberStack.push(ret);
                    operatorStack.push(op);
                }
                // 如果当前操作符的优先级大于栈中的操作符，直接入符号栈
                else {
                    operatorStack.push(op);
                }

            }
            // 处理数字，数组直接入栈
            else {
                numberStack.push(Integer.parseInt(op));
            }

            index++;
        }

        // 清空符号栈
        while (!operatorStack.isEmpty()){
            int num1 = numberStack.pop();
            int num2 = numberStack.pop();
            String stackOp = operatorStack.pop();
            int ret = CalculatorUtil.calculate(num2, stackOp, num1);
            numberStack.push(ret);
        }

        System.out.println(numberStack.pop());
    }
}

class CalculatorUtil {
    // 判断是否为操作符，只支持简单的四则运算
    public static boolean isOperator(String c) {
        List<String> list = Arrays.asList("+", "-", "*", "/");
        return list.indexOf(c) > -1;
    }

    // 判断优先级
    public static int priority(String operator) {
        Map<String, Integer> map = new HashMap<>();
        map.put("*", 1);
        map.put("/", 1);
        map.put("+", 0);
        map.put("-", 0);

        return map.getOrDefault(operator, -1);
    }

    // 计算
    public static int calculate(int num1, String operator, int num2) {
        int ret;

        switch (operator) {
            case "+":
                ret = num1 + num2;
                break;
            case "-":
                ret = num1 - num2;
                break;
            case "*":
                ret = num1 * num2;
                break;
            case "/":
                ret = num1 / num2;
                break;
            default:
                throw new RuntimeException("don't parse operator");
        }

        return ret;
    }
}

class CalculatorStack<T> {
    private CalculatorNode<T> top;

    // 链表无限扩充不会满
    // public boolean isFull(){}

    // 判空
    public boolean isEmpty() {
        return top == null;
    }

    // 出栈
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        T data = top.data;
        top = top.next;
        System.out.println("pop: " + data);
        return data;
    }

    // 入栈
    public void push(T data) {
        CalculatorNode<T> node = new CalculatorNode<>(data);
        node.next = top;
        top = node;
        System.out.println("push: " + data);
    }

    // 栈顶数据
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return top.data;
        }
    }

    // 统计栈内数据个数
    public int length() {
        // 显示栈数据

        CalculatorNode<T> temp = top;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;

    }

    // 显示栈数据
    public void list() {
        CalculatorNode<T> temp = top;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class CalculatorNode<T> {
    public T data;
    public CalculatorNode<T> next;

    public CalculatorNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "data=" + data +
                '}';
    }
}