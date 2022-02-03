import java.util.*;
public class NavigableSkip<E extends Comparable<E>> extends SkipList<E>{
    /**
     * Inserts Element 
     * @param item E
     * @return E
     */
    public E insert(E item){
        if(!this.contains(item))
            this.add(item);
        return item;
    }
    /**
     * Deletes Element
     * @param item E
     * @return E
     */
    public E delete(E item) {
        return this.remove(item);
    }
    /**
     * Returns Descending Iterator
     * @return Iterator<E>
     */
    public Iterator<E> descendingIterator() {
        return new DescendingIter();
    }
    /**Private Inner Class */
    private class DescendingIter implements Iterator<E>{
        ArrayList<E> datas=new ArrayList<E>();
        int counter=0;
        /**Iterator Constructor */
        DescendingIter(){
            Iterator<E> iter=iterator();
            ArrayList<E> temp=new ArrayList<E>();
            while (iter.hasNext())
                temp.add(iter.next());
            for (int i = temp.size()-1; i >= 0; i--) {
                datas.add(temp.get(i));
            }
        }
        /**HasNext */
        @Override
        public boolean hasNext() {
            if (counter!=datas.size()) {
                return true;
            }
            return false;
        }
        /**Returns Next Element and moves */
        @Override
        public E next() {          
            if (hasNext()) {
                E temp=datas.get(counter);
                counter++;
                return temp;
            }
            return null;
        }
    }
    
}