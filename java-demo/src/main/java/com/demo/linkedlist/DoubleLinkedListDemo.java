package com.demo.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        DoubleNode node1 = new DoubleNode(1, "刘备");
        DoubleNode node2 = new DoubleNode(2, "曹操");
        DoubleNode node3 = new DoubleNode(3, "孙权");

        list.add(node1);
        list.add(node2);
        list.add(node3);

        list.delete(node3);

        node2.data = "司马懿";
        list.update(node2);

        list.list();

    }
}

class DoubleLinkedList {
    // 头节点
    private DoubleNode head = new DoubleNode(0, null);

    //添加节点
    public void add(DoubleNode node) {
        DoubleNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
        node.pre = temp;
    }

    // 删除节点
    public void delete(DoubleNode node) {
        DoubleNode temp = head.next;

        while (temp != null) {
            if (temp.id == node.id) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                break;
            }
            temp = temp.next;
        }

    }

    // 查看链表
    public void list() {
        DoubleNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 更新节点
    public void update(DoubleNode node) {
        DoubleNode temp = head.next;

        while (temp != null) {
            if (temp.id == node.id) {
                temp.data = node.data;
            }
            temp = temp.next;
        }
    }
}

class DoubleNode {
    public int id;
    public String data;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
