package com.galaxy.mecury.tree;

import java.util.ArrayList;
import java.util.List;

class LinkedNode {
    LinkedNode next = null;
    int data;

    public LinkedNode(int data) {
        this.data = data;
    }
}
public class MyLinkedList {
    LinkedNode head = null;

    public MyLinkedList(List<Integer> array) {
        for (Integer data : array) {
            if (head == null) {
                head = new LinkedNode(0);
            }
            LinkedNode q = head;
            while (q != null && q.next != null) {
                q = q.next;
            }

            LinkedNode newNode = new LinkedNode(data);
            q.next = newNode;
        }
    }

    public void traversal() {
        if (head != null) {
            LinkedNode p = head.next;
            while (p != null) {
                System.out.println(p.data);
                p = p.next;
            }
        }

    }

    public void reverse() {

    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<Integer>() ;
        array.add(1);
        array.add(2);
        array.add(3);

        MyLinkedList myLinkedList = new MyLinkedList(array);
        myLinkedList.traversal();
    }
}
