package org.example.pool.implementations;

import org.example.datastructures.implementations.Lifo;
import org.example.datastructures.interfaces.Queue;
import org.example.pool.interfaces.Pool;

public abstract class AbstractObjectPool<T> implements Pool<T>, Runnable {

    private final Queue<T> pool;

    private final int maxCapacity;
    private int initialCapacity;
    private int generatedObjects;

    public AbstractObjectPool(int initialCapacity, int maxCapacity) {
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Initial capacity is less than 0");
        }
        if (maxCapacity <= 0){
            throw new IllegalArgumentException("Max capacity is less than/equal to 0");
        }
        if (initialCapacity > maxCapacity){
            throw new IllegalArgumentException("Initial capacity is more than Max one");
        }
        this.maxCapacity = maxCapacity;
        this.initialCapacity = this.generatedObjects = initialCapacity;
        pool = new Lifo<>(initialCapacity);
        fillPool(initialCapacity);
        System.out.printf("%s - The pool is created with a capacity of %d : objectsInPool -> %d", Thread.currentThread().getName(), initialCapacity, this.initialCapacity);
        System.out.println(" - " + pool);
    }

    @Override
    public synchronized T get() {
        if (initialCapacity == 0) {
            if (generatedObjects < maxCapacity) {
                System.out.printf("%s - Getting object from the pool : objectsInPool -> %d", Thread.currentThread().getName(), initialCapacity);
                System.out.println(" - " + pool);
                generatedObjects++;
                return generateObject();
            } else {
                System.out.printf("%s - I'm waiting!", Thread.currentThread().getName());
                System.out.println(" - " + pool);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        initialCapacity--;
        System.out.printf("%s - Getting object from the pool : objectsInPool -> %d", Thread.currentThread().getName(), initialCapacity);
        System.out.println(" - " + pool);
        return pool.pop();
    }

    @Override
    public synchronized void release(T object) {
        pool.push(object);
        initialCapacity++;
        System.out.printf("%s - Putting the taken object to the pool", Thread.currentThread().getName());
        System.out.println(" - " + pool);
        this.notifyAll();
    }

    private void fillPool(int size) {
        for (int i = 0; i < size; i++) {
            pool.push(generateObject());
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            T object = get();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            release(object);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract T generateObject();
}
