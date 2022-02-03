public interface KWHashMap<K,V> {
    /**
     * Returns The Value of Key
     * @param key Object
     * @return V
     */
    V get(Object key);
    /**
     * Checks Hash
     * @return boolen
     */
    boolean isEmpty();
    /**
     * Puts into Hash 
     * @param key K
     * @param value V
     * @return V
     */
    V put(K key, V value);
    /**
     * Removes From Hash
     * @param key Object
     * @return V
     */
    V remove(Object key);
    /**
     * Returns Size
     * @return int
     */
    int size();
}
