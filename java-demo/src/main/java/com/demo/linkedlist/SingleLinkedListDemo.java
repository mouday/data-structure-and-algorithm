package com.demo.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // SingleLinkedList linkedList = new SingleLinkedList();
        //


        // 无序插入
        // linkedList.add(node1);
        // linkedList.add(node3);
        // linkedList.add(node2);

        // 有序插入
        SingleLinkedList linkedList1 = new SingleLinkedList();
        Node node1 = new Node(1, "张飞");
        Node node2 = new Node(2, "刘备");
        Node node3 = new Node(3, "关羽");

        linkedList1.addByOrder(node1);
        linkedList1.addByOrder(node3);
        linkedList1.addByOrder(node2);

        SingleLinkedList linkedList2 = new SingleLinkedList();
        Node node4 = new Node(2, "周瑜");
        Node node5 = new Node(4, "孙策");
        Node node6 = new Node(6, "鲁肃");

        linkedList2.addByOrder(node4);
        linkedList2.addByOrder(node5);
        linkedList2.addByOrder(node6);

        linkedList1.concat(linkedList2);

        linkedList1.list();

        // linkedList.delete(node1);
        //
        // linkedList.list();
        //
        // System.out.println(linkedList.length());
        //
        // System.out.println(linkedList.findLastIndexNode(0));

    }
}


class SingleLinkedList {
    // 头节点不存实际数据
    private Node head = new Node(0, null);

    /**
     * 获取头节点
     */
    public Node getHead() {
        return head;
    }

    /**
     * 链表最后添加节点
     */
    public void add(Node node) {
        Node temp = head;

        // 查找链表尾部
        while (temp.next != null) {
            // 指针后移
            temp = temp.next;
        }

        // 最后一个节点
        temp.next = node;
    }

    /**
     * 按照大小排序
     */
    public boolean addByOrder(Node node) {
        Node temp = head;

        boolean flag = false; // 是否存在该节点

        while (temp.next != null) {
            // 判断数据是否存在
            if (temp.next.id == node.id) {
                flag = true;
                break;
            }
            // 找到数据插入位置
            else if (temp.next.id > node.id) {
                break;
            }
            temp = temp.next;
        }

        if (!flag) {
            // 修改指针指向
            node.next = temp.next;
            temp.next = node;
        }
        return !flag;
    }

    /**
     * 查找到指向节点的前一个节点
     * BeforeNode.next -> Node
     */
    public Node findBeforeNode(Node node) {
        Node temp = head;

        while (temp.next != null) {
            if (node.id == temp.next.id) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    /**
     * 查找节点
     */
    public Node findNode(Node node) {
        Node beforeNode = findBeforeNode(node);

        if (beforeNode != null) {
            return beforeNode.next;
        } else {
            return null;
        }
    }

    /**
     * 修改节点
     */
    public boolean update(Node node) {
        Node beforeNode = findBeforeNode(node);

        if (beforeNode != null) {
            beforeNode.next.data = node.data;
            return true;
        } else {
            return false;
        }

    }

    /**
     * 删除节点 GC会自动清除没有被引用的对象
     */
    public boolean delete(Node node) {
        Node beforeNode = findBeforeNode(node);

        if (beforeNode != null) {
            beforeNode.next = beforeNode.next.next;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 显示链表
     */
    public void list() {

        Node temp = head;

        // 遍历
        while (temp.next != null) {
            // 指针后移
            temp = temp.next;

            System.out.println(temp);
        }
    }

    /**
     * 统计节点个数
     */
    public int length() {
        Node temp = head;
        int len = 0;

        while (temp.next != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    /**
     * 从链表尾部开始获取节点, index从0开始
     * 0 <= index < size
     */
    public Node findLastIndexNode(int index) {
        int size = this.length();

        // 如果链表长度为0
        if (size == 0) {
            return null;
        }

        // index校验
        if (index < 0 || index >= size) {
            return null;
        }

        Node temp = head;

        //  head -> 1 -> 2 -> 3
        //  last    2    1    0

        // size index size-index
        //  3     0      3
        //  3     1      2
        //  3     2      1
        //  3     3      0  (null)

        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表
     * head->1->2->3
     * <p>
     * reverseHead
     * reverseHead->1
     * reverseHead->2->1
     * reverseHead->3->2->1
     */
    public void reverse() {

        // 如果链表长度为0或1则直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义临时节点
        Node reverseHead = new Node(0, null);
        Node current = head.next;
        Node next;

        while (current != null) {
            // 保存下一个节点
            next = current.next;

            // 修改指向
            current.next = reverseHead.next;
            reverseHead.next = current;

            // 后移
            current = next;
        }

        // 改变头接点指向
        head = reverseHead;
    }

    /**
     * 逆序打印
     */
    public void reverseList() {
        // 如果是空链表就直接返回
        if (head.next == null) {
            return;
        }

        Node current = head.next;
        Stack<Node> stack = new Stack<>();

        // 将节点压入栈中
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // 从栈中取出数据，先入后出，实现逆序输出
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序链表,合并之后依然有序
     * <p>
     * head->1->2->3
     * otherHead->2->4->6
     * <p>
     * newHead->1
     */
    public void concat(SingleLinkedList other) {
        if (other == null) {
            return;
        }

        // 新的头节点, 借助辅助链表
        Node newHead = new Node(0, null);
        Node newCurrent = newHead;

        Node current = head.next;
        Node otherCurrent = other.getHead().next;

        while (true) {
            // 4种组合
            // current == null  && otherCurrent == null break
            // current == null  && otherCurrent != null move left
            // current != null  && otherCurrent == null more right
            // current != null  && otherCurrent != null compare

            // 结束循环
            if (current == null && otherCurrent == null) {
                break;
            }
            // 其中一个是null, 另一个就直接后移追加
            else if (current == null) {
                // 保存下一个节点信息，并将当前节点下一个节点指向空
                newCurrent.next = otherCurrent;
                otherCurrent = otherCurrent.next;
            }
            // 另一个是null
            else if (otherCurrent == null) {
                newCurrent.next = current;
                current = current.next;
            }
            // 两个都不是null则比较大小
            else {
                if (current.id < otherCurrent.id) {

                    newCurrent.next = current;
                    current = current.next;
                } else {
                    newCurrent.next = otherCurrent;
                    otherCurrent = otherCurrent.next;
                }
            }
            // 新链表指针后移
            newCurrent = newCurrent.next;
        }

        // 修改指针指向
        head = newHead;
    }
}


class Node {
    public int id;
    public String data;
    public Node next;

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}