import java.util.*;
@SuppressWarnings("unchecked")
public class SkipList<E extends Comparable<E>> implements Iterable<E>{
    private static final double LOG2 = Math.log(2.0);
    private int maxLevel = 4;
    private int maxCap = (int) (Math.pow(2, maxLevel) - 1);
    private int size = 0;
    protected SLNode<E> head;
    /**Constructor */
    public SkipList() {
        head = new SLNode<>(maxLevel, null);
    }

    /**
     * Returns an iterator over elements of type E
     * @return an Iterator.
     */
    public Iterator<E> iterator() {
        return new SkipListIter();
    }
    /**Private Node Class */
    private static class SLNode<E>{
        SLNode<E>[] links;
        E data;
        /**
         * Node Constructor
         * @param m int
         * @param data E
         */
        public SLNode(int m, E data) {
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }
    }
    /**
     * Calculates ...
     * @return int
     */
    private int logRandom(){
        Random rand = new Random();
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }
    /**
     * Checks The Existing Position
     * @param target E
     * @return SLNode<E>[] 
     */
    private SLNode<E>[] search (E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0) {
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }
    /**
     * Finds Element
     * @param target E
     * @return E
     */
    public E find(E target) {
        SLNode<E>[] pred = search(target);
        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0) {
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }
    /**
     * Removes Element
     * @param target E
     * @return E
     */
    public E remove(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0){
            E returnVal = pred[0].links[0].data;
            int level = pred[0].links[0].links.length;
            for(int i = 0; i < level; i++){
                pred[i].links[i] = pred[i].links[i].links[i];
            }
            size--;
            return returnVal;
        }
        else{
            return null;
        }
    }
    /**
     * Adds Element
     * @param item E
     */
    public void add(E item){
        int level = logRandom();
        SLNode<E>[] pred = search(item);
        SLNode<E> newNode = new SLNode<>(level, item);
        for(int i = 0; i < level; i++){
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        size++;
        if (size > maxCap) {
            maxLevel++;
            maxCap = (int) (Math.pow(2, maxLevel) - 1);
            head.links = Arrays.copyOf(head.links, maxLevel);
        }
    }
    /**
     * Returns Size
     * @return int
     */
    public int size(){
        return size;
    }
    /**
     * Checks Existing
     * @param target E
     * @return Boolean
     */
    public boolean contains(E target){
        return find(target) != null;
    }
    /**ToString */
    public String toString() {
        StringBuilder str = new StringBuilder();
        Iterator<E> iterator = new SkipListIter();
        str.append("[");
        while (iterator.hasNext()){
            str.append(iterator.next());
            if (iterator.hasNext()) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }
    /**Private Iterator Class */
    private class SkipListIter implements Iterator<E>{
        private SLNode<E> current = head;
        /** HasNext*/
        @Override
        public boolean hasNext() {
            return current.links[0] != null;
        }
        /**
         * Returns the next element in the iteration
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return (current = current.links[0]).data;
        }
    }
}
