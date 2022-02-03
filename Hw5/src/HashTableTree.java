import java.util.*;
public class HashTableTree<K extends Comparable<K>,V> implements KWHashMap<K,V>{
    /** The table */
    private TreeSet<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K extends Comparable<K> , V> implements Comparable<Entry<K,V>> {
        /** The key */
        private final K key;
        /** The value */  
        private V value;
        /** Creates a new key‐value pair.
         * @param key The key
         * @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /** Retrieves the key.
         *@return The key
        */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
         *@return The value
        */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         *@param val The new value
        *@return The old value
        */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
        public String toString() {
            return key + "=" + value;
        }
        /**CompareTo */
        @Override
        public int compareTo(HashTableTree.Entry<K, V> o) {
            return this.key.compareTo(o.getKey());
        }
    }
    // Constructor
    @SuppressWarnings("unchecked")
    public HashTableTree() {
        table = new TreeSet[CAPACITY];
    }

    /** Method get for class HashtableTree.
        @param key The key being sought
        @return The value associated with this key if found;
        otherwise, null
    */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        // assert: key is not in the table.
        return null;
    }
    /** Method put for class HashtableTree.
    @param key The key of item being inserted
    @param value The value for this key
    @return The old value associated with this key if
    found; otherwise, null
    */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            // Create a new TreeSet at table[index].
            table[index] = new TreeSet<>();
        }
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }
        table[index].add(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }
    /**
     * Method to rehash table.
     * Allocates a new hash table that is double the size and has an odd length.
     * Reinsert each table entry in the new hash table.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        TreeSet<Entry<K,V>>[] oldTable = table;
        table = new TreeSet[oldTable.length * 2 + 1];
        numKeys = 0;
        for (TreeSet<Entry<K, V>> list : oldTable) {
            if (list != null) {
                for (Entry<K,V> entry : list) {
                    put(entry.getKey(), entry.getValue());
                    numKeys++;
                }
            }
        }
    }
    /*
     * Removes the mapping for a key from this map if it is present.
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; // key is not in table
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                table[index].remove(entry);
                numKeys--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return value;
            }
        }
        return null;
    }
    /**
     * Returns Size
     */
    @Override
    public int size() {
        return numKeys;
    }
    /** Returns true if empty */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }
    /**toString */
    public String toString() {
		StringBuilder str = new StringBuilder("[");
		for (TreeSet<Entry<K, V>> l : table)
			if (l != null) {
				str.append(l);
				str.append(" ");
			}
		str.append("]");
		return str.toString();
	}
}
