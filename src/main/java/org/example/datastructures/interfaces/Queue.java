package org.example.datastructures.interfaces;

public interface Queue<T> {

    T push(T item);

    T peek();

    T pop();

    int search(T item);

    boolean empty();

    static Object[] doubleArray(Object[] array, int length) {
        Object[] doubledArray = new Object[length * 2];
        System.arraycopy(array, 0, doubledArray, 0, array.length);
        return doubledArray;
    }
}
