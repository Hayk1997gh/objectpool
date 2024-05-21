package org.example.pool.implementations;

import java.util.UUID;

public class StringPool extends AbstractObjectPool<String> {

    public StringPool(int initialSize, int maxSize) {
        super(initialSize, maxSize);
    }

    @Override
    protected String generateObject() {
        return UUID.randomUUID().toString();
    }
}
