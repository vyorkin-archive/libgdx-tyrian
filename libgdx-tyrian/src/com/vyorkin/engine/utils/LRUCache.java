package com.vyorkin.engine.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    public interface CacheEntryRemovedListener<K, V> {
        void notifyEntryRemoved(K key, V value );
    }
    
    private final static float LOAD_FACTOR = .75F;

    private Map<K,V> cache;
    private CacheEntryRemovedListener<K,V> entryRemovedListener;

    public LRUCache(final int maxEntries) {
        cache = new LinkedHashMap<K,V>(maxEntries + 1, LOAD_FACTOR, true) {
			private static final long serialVersionUID = -1637553867983683293L;

			public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                if (size() > maxEntries) {
                    if (entryRemovedListener != null) {
                        entryRemovedListener.notifyEntryRemoved(
                    		eldest.getKey(), eldest.getValue());
                    }
                    return true;
                }
                return false;
            }
        };
    }

    public void add(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public Collection<V> retrieveAll() {
        return cache.values();
    }

    public void setEntryRemovedListener(
        CacheEntryRemovedListener<K,V> entryRemovedListener) {
        this.entryRemovedListener = entryRemovedListener;
    }
}