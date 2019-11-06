package com.custom.cache.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.custom.cache.CustomCache;

class FileSystemCacheImpl implements CustomCache {
	private int capacity;
    private Path tempDirectory;
    private  Map<Object, String> fileSystemCache;

    // initialize the values
    FileSystemCacheImpl(int capacity) {
        try {
        	this.capacity = capacity;
        	this.fileSystemCache = new HashMap<Object, String>(capacity);
			this.tempDirectory = Files.createTempDirectory("fileSystemCache");
		} catch (IOException e) {
			System.out.println("error in creating the cache  "+ e.getMessage());
		}
    }

    // read the values from cache
    @Override
    public Object get(String key) {
    	Object value =null;
        if (isDataPresentInCache(key)) {
            String fileName = fileSystemCache.get(key);
            FileInputStream in = null;
            ObjectInputStream out = null;
            try {
            	 fileName = tempDirectory + File.separator + fileName;
            	 in = new FileInputStream(new File(fileName));
            	 out = new ObjectInputStream(in);
            	 value =  (String) out.readObject();
            	 in.close();
            	 out.close();
            } catch (ClassNotFoundException | IOException e) {
            	System.out.println("Unable to read the values from cache. FileName : "+fileName + " Error :"+e.getMessage());
            }finally{
            	if(in != null && out !=null){
            		try {
						in.close();
						out.close();
					} catch (IOException e) {
						System.out.println("Error in closing the streams");
					}
            	}
            }
        }
        return value;
    }
    //check data is present in cache or not
    public boolean isDataPresentInCache(Object key) {
        return fileSystemCache.containsKey(key);
    }
    
    //store the data into cache
    @Override
    public void put(String key, Object value) {
        File tmpFile = null;
        ObjectOutputStream out = null;
		try {
				tmpFile = Files.createTempFile(tempDirectory, "", "").toFile();
				out = new ObjectOutputStream(new FileOutputStream(tmpFile));
				out.writeObject(value);
				out.flush();
	            fileSystemCache.put(key, tmpFile.getName());
	            out.close();
		} catch (IOException e1) {
			System.out.println("Unable to put the data into cache file "+ e1.getMessage());
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("Error in closing the streams");
				}
			}
		}

    }
    
    //check capacity is available or not
    public boolean isEmpty() {
        return fileSystemCache.size() < this.capacity;
    }

    //delete the data from cache
    @Override
    public boolean delete(String key){
    	if(isDataPresentInCache(key)){
    		fileSystemCache.remove(key);
    		return true;
    	}
    	System.out.println("data is not present in cache");
    	return false;
	}
	
}
