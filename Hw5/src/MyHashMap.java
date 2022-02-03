import java.util.*;
public class MyHashMap<K,V> extends HashMap<K,V>{
    /**
     * Returns MapIterator
     * @param key K
     * @return MapIterator
     */
    public MapIterator iterator(K key){
        return new MapIterator(key);
    }
    /**
     * Returns MapIterator
     * @return MapIterator
     */
    public MapIterator iterator(){
        return new MapIterator();
    }
    
    @SuppressWarnings("unchecked")
    public class MapIterator implements Iterator<K>{
        private K[] keys = (K[]) keySet().toArray();
        private int size=keys.length;
        private K next=keys[0];
        private K prev=keys[size-1];
        private int counter=0;
        private int start=0;
        private int loc=0;
        /**
         * Constructor With Parameter
         * @param key K
         */
        public MapIterator (K key) {
            for (int i = 0; i < size; i++) 
                if (keys[i].equals(key)){
                    next=keys[i];
                    prev=keys[(i-1)%size];
                    start=i;
                    loc=i;
                    return;
                }
        }
        /**Constructor */
        public MapIterator () {
        }
        /**HasNext */
        @Override
        public boolean hasNext() {
            if (counter!=size) {
                return true;
            }
            return false;
        }
        /**Returns Next Element and moves */
        @Override
        public K next() {
            K temp=keys[start];
            if (hasNext()) {
                temp=next;
                next=keys[(loc+1)%size];
                prev=keys[(loc)%size];
                loc=(loc+1)%size;
                counter++;
                return temp;
            }
            
            return temp;
        }
        /**Returns Previous element */
        public K prev() {
            K temp=keys[(start-1)%size];
            temp=prev;
            next=keys[loc%size];
            prev=keys[(loc-1+size)%size];
            //loc=(loc-1)%size;
            return temp;
        }
    }
}
