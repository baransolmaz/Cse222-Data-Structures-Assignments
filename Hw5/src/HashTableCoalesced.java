public class HashTableCoalesced<K, V> implements KWHashMap<K, V> {
    // Data Fields
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 20;
    private int numKeys;
    private double LOAD_THRESHOLD = 0.8;

    private static class Entry<K, V> {
        /** The key */
        private final K key;
        /** The value */
        private V value;
        /** The Next Key */
        private Entry<K, V> next = null;
        private int indexNext;

        /**
         * Creates a new key‐value pair.
         * 
         * @param key   The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * 
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * 
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * 
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public String toString() {
            return key + "=" + value;
        }

    }

    // Constructor
    @SuppressWarnings("unchecked")
    public HashTableCoalesced() {
        table = new Entry[START_CAPACITY];
    }

    @Override
    /**
     * Checks if it is Empty
     * @return boolean
     */
    public boolean isEmpty() {
        return numKeys == 0;
    }
    /**
     * Puts in Table
     * @param key K
     * @param value V
     * @return V
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;

        if (index < 0)
            index += table.length;

        if (table[index] == null) {
            table[index] = new Entry<K, V>(key, value);
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length))
                rehash();
            return null;
        }
        int newIndex = index;
        while (table[newIndex]!=null) {
            if (table[newIndex] != null && table[newIndex].getKey().equals(key)) {
                V oldVal = table[newIndex].getValue();
                table[newIndex].setValue(value);
                return oldVal;
            }
            if (table[newIndex].next!=null){
                newIndex=table[newIndex].indexNext;
            }else
                break;
        }

        Entry<K, V> entry = table[index];
        int time = 0;
        newIndex = index;
        while (entry.next != null) {
            newIndex = (index + (time * time)+table.length) % table.length;
            time++;
            entry = entry.next;
        }
        while (table[newIndex] != null) {
            newIndex = ((index + (time * time))+table.length) % table.length;
            if (newIndex < 0)
                newIndex += table.length;
            time++;
        }
        table[newIndex] = new Entry<K, V>(key, value);
        entry.next = table[newIndex];
        entry.indexNext = newIndex;
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }
    /**
     * Delets The Key
     * @param key Object
     * @return V
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;

        if (index < 0)
            index += table.length;

        if (table[index] == null) {
            return null;
        }
        int nextIndex=index;
        int prevIndex=index;
        while (table[nextIndex]!=null) {
            if(table[nextIndex].key.equals(key)){
                int temp2;
                V temp = table[nextIndex].value;
                table[prevIndex].next=table[nextIndex].next;
                while (table[nextIndex].next!=null) {
                    temp2=table[nextIndex].indexNext;
                    table[nextIndex]=table[nextIndex].next;
                    nextIndex=temp2;
                }
                numKeys--;
                table[nextIndex]=null;
                return temp;
            }
            prevIndex=nextIndex;
            if (table[nextIndex].next!=null) {
                nextIndex=table[nextIndex].indexNext;
            }else
                return null;
        } 
        return null;
    }

    /**
     * Returns Size
     * @return int
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**ToString */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Hash\nValue\tKey\tNext\n\n");
        for (int i = 0; i < table.length; i++) {
            str.append(" " + i).append("\t");
            if (table[i] == null) {
                str.append(" ").append("\tNull\n");
            } else {
                str.append(table[i]).append("\t");
                if (table[i].next == null) {
                    str.append("Null").append("\n");
                } else
                    str.append(table[i].indexNext).append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Method get for class HashtableOpen.
     * 
     * @param key The key being sought
     * @return the value associated with this key if found; otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;

            int nextIndex=index;
        while (table[nextIndex]!=null) {
            
            if(table[nextIndex].key.equals(key))
                return table[nextIndex].getValue();
            
            if (table[nextIndex].next!=null){
                nextIndex=table[nextIndex].indexNext;
            }else
                return null;
        }
        return null;

    }

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[(2 * oldTable.length) + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null)) {
                // Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }
}