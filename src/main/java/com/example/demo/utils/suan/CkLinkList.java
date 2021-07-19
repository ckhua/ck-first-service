package com.example.demo.utils.suan;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @Description 类注释
 * @Date 2021/4/17 13:22
 * @Author chen kang hua
 * @Version 1.0
 **/
public class CkLinkList<E> {

    int size;
    Node<E> first;
    Node<E> last;

    public static void main(String[] args) {

        String avk = new String("avk");
        String avk1 = new String("avk");

        int code = avk.hashCode();
        int code1 = avk1.hashCode();
        System.out.println(code + "  " + code1);

        System.out.println(avk.equals(avk1));
        System.out.println(avk == avk1);

        HashMap<Object, Object> objectHashMap = Maps.newHashMap();
    }

    @Override
    public String toString() {
        Node<E> next = first;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (next != null) {
                sb.append(next.toString() + ";");
                next = next.next;
            }
        }
        return sb.toString();
    }

    private Node<E> getNode(int index) {

        if (index == 0) {
            return first;
        }

        if (index == size) {
            return last;
        }

        if (size >> 1 > index) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    ;

    void add(int index, E e) {

        if (size == index) {
            Node<E> last = this.last;
            Node node = new Node(last, e, null);
            this.last = node;
            if (last == null) {
                first = node;
            } else {
                last.next = node;
            }
        }

        Node<E> node = getNode(index);

        Node<E> prev1 = node.prev;

        if (prev1 == null) {
            Node newFirst = new Node(null, e, node);
            first = newFirst;
            prev1 = newFirst;
        }
        if (index == 0) {

        } else {
            Node<E> prev = node.prev;
            Node newFirst = new Node(prev, e, node);
            prev1.next = newFirst;
        }
        size++;
    }

    private static class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "item=" + item;
        }
    }
}
