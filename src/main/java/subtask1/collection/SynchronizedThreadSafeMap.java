package subtask1.collection;

import java.util.*;

public class SynchronizedThreadSafeMap<K, V> implements Map<K, V> {
    private final Map<K, V> map = new HashMap<K, V>();
    private final Object lock = new Object();

    @Override
    public V put(K key, V value) {
        synchronized (lock) {
            return map.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (lock) {
            return map.get(key);
        }
    }

    @Override
    public V remove(Object key) {
        synchronized (lock) {
            return map.remove(key);
        }
    }

    @Override
    public int size() {
        synchronized (lock) {
            return map.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (lock) {
            return map.isEmpty();
        }
    }

    @Override
    public void clear() {
        synchronized (lock) {
            map.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        synchronized (lock) {
            return new HashSet<K>(map.keySet());
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (lock) {
            return new ArrayList<V>(map.values());
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        synchronized (lock) {
            return new HashSet<Entry<K, V>>(map.entrySet());
        }
    }

    @Override
    public boolean containsKey(Object key) {
        synchronized (lock) {
            return map.containsKey(key);
        }
    }

    @Override
    public boolean containsValue(Object val) {
        synchronized (lock) {
            return map.containsValue(val);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (lock) {
            map.putAll(m);
        }
    }
}
