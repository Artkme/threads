package subtask1.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentThreadSafeMap<K, V> implements Map<K, V> {
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);

    }

    @Override
    public int size() {
        return map.size();

    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();

    }

    @Override
    public void clear() {
        map.clear();

    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(map.keySet());

    }

    @Override
    public Collection<V> values() {
        return new ArrayList<V>(map.values());

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<Entry<K, V>>(map.entrySet());

    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);

    }

    @Override
    public boolean containsValue(Object val) {
        return map.containsValue(val);

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }
}
