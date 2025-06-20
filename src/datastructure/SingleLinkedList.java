package datastructure;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList {

    Node head;
    Node tail;
    int size;

    public SingleLinkedList(int data) {
        head = new Node(data);
        size++;
    }

    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size += 1;
    }

    public void append(int data) {
        Node newNode = new Node(data);
        if (tail != null) {
            tail.next = newNode;
        }
        Node oldTail = tail;
        tail = newNode;
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = oldTail;
        size += 1;

    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(data);
        Node leader = this.get(index - 1);
        newNode.next = leader.next;
        leader.next = newNode;
        size += 1;
    }

    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node thisNode = this.get(index);
        Node previous = this.get(index - 1);
        previous.next = thisNode.next;
    }

    public Node get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Node current = head;
        while (count != index) {
            current = current.next;
            count += 1;
        }
        return current;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    void printList() {
        List<Integer> ar = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            ar.add(cur.value);
            cur = cur.next;
        }
        System.out.println(ar);
    }

    public void reverseList() {
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node tmpNext = cur.next;
            cur.next =  prev;
            prev = cur;
            cur = tmpNext;
        }
        this.head = prev;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList(33);
        list.prepend(55);
        list.append(11);
        list.append(44);
        list.insert(2, 99);
        list.remove(3);
        list.printList();
        list.reverseList();

        list.printList();
    }
}
