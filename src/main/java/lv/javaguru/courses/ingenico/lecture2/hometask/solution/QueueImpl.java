package lv.javaguru.courses.ingenico.lecture2.hometask.solution;

import java.util.Iterator;
import java.util.function.Predicate;

public class QueueImpl<E> implements Queue<E> {

    private Node head;

    private Node last;

    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        addNode(new Node(element));
    }

    private void addNode(Node node){
        Node prev = this.last;
        this.last = node;
        if (prev == null) {
            head = node;
        } else {
            prev.next = node;
        }
    }

    @Override
    public E poll() {
        if (head == null) {
            return null;
        }
        Node first = head;
        head = head.next;
        return first.item;
    }

    @Override
    public E element() {
        return head != null ? head.item : null;
    }

    public void addAll(Queue<? extends E> queue) {
        if (queue instanceof QueueImpl){
            @SuppressWarnings("unchecked")
            QueueImpl<E> q = (QueueImpl<E>) queue;
            addNode(q.head);
        } else {
            for (E e : queue) {
                add(e);
            }
        }
    }

    @Override
    public boolean contains(Predicate<? super E> predicate) {
        for (E e : this) {
            if (predicate.test(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {

        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                return null;
            }
            E item = node.item;
            node = node.next;
            return item;
        }
    }

    private class Node {
        E item;

        Node next;

        Node(E item) {
            this.item = item;
        }
    }
}
