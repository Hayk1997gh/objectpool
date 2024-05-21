package org.example.pool.interfaces;

public interface Pool<T> {

    T get();

    void release(T object);

}