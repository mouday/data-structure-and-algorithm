package com.demo.linkedlist;

public class JosepfuLinkedListDemo {
    public static void main(String[] args) {
        JosepfuLinkedList list = new JosepfuLinkedList();
        list.addBoys(5);
        list.showList();
        // 5个小孩，从第1个人开始，数2个数
        System.out.println("===出圈===");
        list.boyOut(1, 2);
        // 2->4->1->5->3

    }
}

/**
 * 约瑟夫问题环形链表
 */
class JosepfuLinkedList {
    private BoyNode first; // 头节点，指向第一个节点

    // 新建环形链表，编号从1开始
    public void addBoys(int num) {
        // 入参校验
        if(num < 1){
            System.out.println("require: num>0");
            return;
        }

        BoyNode last = null;  // 尾节点，指向最后一个节点，辅助指针

        BoyNode node;  // 新建的节点

        for (int i = 1; i < num + 1; i++) {
            node = new BoyNode(i);

            if (i == 1) {
                first = node;
                last = node;
            } else {
                last.next = node;
                last = node;
            }

            last.next = first;
        }
    }

    // 显示链表
    public void showList() {

        // 必要的入参校验
        if (first == null) {
            return;
        }

        BoyNode temp = first;

        while (true) {
            System.out.println(temp);

            if (temp.next == first) {
                break;
            }

            temp = temp.next;
        }
    }

    /**
     * 出圈顺序
     *
     * @param startNo  开始编号
     * @param countNum 报数
     */
    public void boyOut(int startNo, int countNum) {
        // 入参校验
        if (first == null || startNo < 1 || countNum < 1) {
            return;
        }

        // 将last.next 指向first
        BoyNode last = first;
        while (last.next != first) {
            last = last.next;
        }

        // 移动到指定的开始位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            last = last.next;
        }

        // 报数出圈
        while (true) {

            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                last = last.next;
            }

            // 出圈
            System.out.println(first);

            first = first.next;
            last.next = first;

            // 只有一个节点退出
            if(first == last){
                break;
            }
        }
        System.out.println(first);


    }
}

class BoyNode {
    public int id;
    public BoyNode next;

    public BoyNode(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BoyNode{" +
                "id=" + id +
                '}';
    }
}