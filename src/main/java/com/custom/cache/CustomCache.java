package com.custom.cache;

public interface CustomCache {
    Object get(String key);
    void put(String key, Object value);
    boolean delete(String key);
}
