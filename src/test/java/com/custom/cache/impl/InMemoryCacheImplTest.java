package com.custom.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.custom.cache.impl.InMemoryCacheImpl;

public class InMemoryCacheImplTest {
	private String value = new String("inMemoryCache");

    private InMemoryCacheImpl inMemoryCache;

    @Before
    public void init() {
        inMemoryCache = new InMemoryCacheImpl(10);
    }

    @Test
    public void shouldNotGetObjectFromCacheIfNotExistsTest() {
        inMemoryCache.put("1", value);
        assertEquals(value, inMemoryCache.get("1"));
        assertNull(inMemoryCache.get("123"));
    }


    @Test
    public void isDataPresent() {
        assertFalse(inMemoryCache.isDataPresentCache("2"));
        inMemoryCache.put("2", value);
        assertTrue(inMemoryCache.isDataPresentCache("2"));
    }

    @Test
    public void isEmpty() {
        IntStream.range(0, 9).forEach(i -> inMemoryCache.put(i+"", "sampleData " + i));

        assertTrue(inMemoryCache.isEmpty());
        inMemoryCache.put("11", "sampleData");
        assertFalse(inMemoryCache.isEmpty());
    }
    
    @Test
    public void delete(){
    	inMemoryCache = new InMemoryCacheImpl(10);
    	inMemoryCache.put("1", value);
    	assertTrue(inMemoryCache.delete("1"));
    }
}