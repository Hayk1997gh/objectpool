package org.example.datastructures.implementations;

import org.example.datastructures.interfaces.Queue;

import java.util.Arrays;

public class Lifo<T> implements Queue<T> {

    private Object[] nodes;
    private int capacity;
    private int currentNodesCount;

    public Lifo() {
        this(10);
    }

    public Lifo(int capacity) {
        this.capacity = capacity;
        nodes = new Object[capacity];
        currentNodesCount = 0;
    }

    @Override
    public T push(T item) {
        if (currentNodesCount == capacity) {
            nodes = Queue.doubleArray(nodes, capacity);
            this.capacity = nodes.length;
        }
        if (currentNodesCount < capacity) {
            nodes[currentNodesCount] = item;
            currentNodesCount++;
        }
        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        T lastItem = null;
        if (currentNodesCount == 0) {
            throw new RuntimeException("Stack is empty!");
        } else {
            if (nodes[currentNodesCount - 1] != null) {
                lastItem = (T) nodes[currentNodesCount - 1];
            }
        }
        return lastItem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        T lastItem = null;
        if (currentNodesCount == 0) {
            throw new RuntimeException("Stack is empty!");
        } else {
            if (nodes[currentNodesCount - 1] != null) {
                lastItem = (T) nodes[currentNodesCount - 1];
                nodes[currentNodesCount - 1] = null;
                currentNodesCount--;
            }
        }
        return lastItem;
    }

    @Override
    public int search(T item) {
        for (int i = currentNodesCount - 1; i >= 0; i--) {
            if (nodes[i].equals(item)) {
                return currentNodesCount - i;
            }
        }
        return -1;
    }

    @Override
    public boolean empty() {
        return currentNodesCount > 0;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "nodes=" + Arrays.toString(nodes) +
                ", capacity=" + capacity +
                ", currentNodesCount=" + currentNodesCount +
                '}';
    }
}
