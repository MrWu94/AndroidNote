package com.hansheng.simplenet.cache;

/**
 * Created by hansheng on 2016/8/20.
 */
public interface Cache<K,V> {
    public V get(K key);

    public void put(K key,V value);

    public void remove(K key);
}
