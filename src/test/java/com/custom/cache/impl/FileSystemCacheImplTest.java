package com.custom.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.custom.cache.impl.FileSystemCacheImpl;


public class FileSystemCacheImplTest {
    private String value = new String("fileSystemCache");

    private FileSystemCacheImpl fileSystemCache;

    @Before
    public void init() {
        fileSystemCache = new FileSystemCacheImpl(10);
    }

    @Test
    public void checkObjectIsExist() {
        fileSystemCache.put("1", value);
        assertEquals(value, fileSystemCache.get("1"));
        assertNull(fileSystemCache.get("123"));
    }

    @Test
    public void isDataPresentInCache() {
        assertFalse(fileSystemCache.isDataPresentInCache("1"));
        fileSystemCache.put("1", value);
        assertTrue(fileSystemCache.isDataPresentInCache("1"));
    }

    @Test
    public void isEmpty() {
        IntStream.range(0, 9).forEach(i -> fileSystemCache.put(i+"", "sampleData " + i));
        assertTrue(fileSystemCache.isEmpty());
        fileSystemCache.put("11", "sampleData");
        assertFalse(fileSystemCache.isEmpty());
    }
    
    @Test
    public void delete(){
    	fileSystemCache = new FileSystemCacheImpl(10);
    	fileSystemCache.put("1", value);
    	assertTrue(fileSystemCache.delete("1"));
    }

}
