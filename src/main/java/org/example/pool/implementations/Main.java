package org.example.pool.implementations;

public class Main {
    public static void main(String[] args) {

        AbstractObjectPool<String> objectPool = new StringPool(1, 1);

        Thread firstThread = new Thread(objectPool);
        firstThread.setName("Hayko");

        Thread secondThread = new Thread(objectPool);
        secondThread.setName("Aram");

        secondThread.start();
        firstThread.start();

    }
}