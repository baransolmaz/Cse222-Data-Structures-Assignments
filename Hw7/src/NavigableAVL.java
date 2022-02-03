import java.util.*;

public class NavigableAVL<E extends Comparable<E>> extends AVLTree<E>{
    /**
     * Inserts Element
     * @param item E
     * @return E
     */
    public E insert(E item) {
        this.add(item);
        return item;
    }
    /**
     * Returns Iterator
     * @return Iterator<E>
     */
    public Iterator<E> iterator() { 
        return new AVLIter();
    }
    /**
     * Returns Set of Elements That Lower Than Item
     * @param item E
     * @return ArrayList<E>
     */
    public ArrayList<E> headSet(E item){
        ArrayList<E> array = new ArrayList<>();
        Iterator<E> iter= iterator();
        while (iter.hasNext()) {
            E data=iter.next();
            if (data.compareTo(item)<0) {
                array.add(data);
            }
        }
        return array;
    }
        /**
     * Returns Set of Elements That Equal or Higher Than Item
     * @param item E
     * @return ArrayList<E>
     */
    public ArrayList<E> tailSet(E item){
        ArrayList<E> array = new ArrayList<>();
        Iterator<E> iter= iterator();
        while (iter.hasNext()) {
            E data=iter.next();
            if (data.compareTo(item)>=0) {
                array.add(data);
            }
        }
        return array;
    }
    /**Private Inner Class For Iterator */
    private class AVLIter implements Iterator<E>{
        ArrayList<E> datas=new ArrayList<E>();
        int counter=0;
        /**Iterator Constructor */
        AVLIter(){
            fillArray(root,datas);
        }
        /**
         * Fills Array In-Order Lower To Higher
         * @param localroot BinaryTree.Node<E>
         * @param datas ArrayList<E>
         */
        private void fillArray(BinaryTree.Node<E> localroot, ArrayList<E> datas) {
            if (localroot == null)
                return;
                
            if (localroot.left!=null) 
                fillArray(localroot.left, datas);
    
            datas.add(localroot.data);
            if (localroot.right!=null)
                fillArray(localroot.right, datas);

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