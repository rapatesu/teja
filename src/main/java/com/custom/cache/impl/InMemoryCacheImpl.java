package com.custom.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.custom.cache.CustomCache;


class InMemoryCacheImpl implements CustomCache {
    private final int capacity;
    private final Map<String, Object> InmemoryCache;
    // initialize the values
    InMemoryCacheImpl(int capacity) {
        this.capacity = capacity;
        this.InmemoryCache = new HashMap<String, Object>(capacity);
    }
    // read the values from cache
    @Override
    public Object get(String key) {
        return InmemoryCache.get(key);
    }
    //check data is present in cache or not
    public boolean isDataPresentCache(String key) {
        return InmemoryCache.containsKey(key);
    }
  //store the data into cache
    @Override
    public void put(String key, Object value) {
        InmemoryCache.put(key, value);
    }
  //check capacity is available or not
    public boolean isEmpty() {
        return InmemoryCache.size() < this.capacity;
    }
  //delete the data from cache
    @Override
    public boolean delete(String key){
    	if(isDataPresentCache(key)){
    		InmemoryCache.remove(key);
    		return true;
    	}
    	System.out.println("data is not present in cache");
    	return false;
	}

}
