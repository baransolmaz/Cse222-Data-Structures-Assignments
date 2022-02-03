import java.util.*;
public class MyHeap<E extends Comparable<E>> implements Iterable<E>,Comparable<MyHeap<E>>{
    protected ArrayList<E> heap;
    /**Constructor */
    MyHeap(){
        heap= new ArrayList<E>();
    }
    /**Constructor */
    MyHeap(E e){
        heap= new ArrayList<E>();
        heap.add(e);
    }
    /**Makes Max Heap */
    protected void sortHeap() {
        for (int i = heap.size()-1; i>=0; i--)
            if (heap.get(i).compareTo(heap.get((i-1)/2))>0)
                swap(i, (i-1)/2);
    }
    /**
     * Returns Size
     */
    public int size() {
		return heap.size();
	}
    /**
     * Returns Peak Data Of Heap
     */
    public E peek() {
        if( heap.size() == 0 )
			throw new NoSuchElementException();
		return heap.get(0);
    }
    /**
     * Returns and Removes Peak Data Of Heap
     */
    public E poll() {
		if( heap.size()== 0)
			return null;
		E temp = heap.get(0);
        swap(0, heap.size()-1);
		heap.remove(heap.size()-1);
        sortHeap();
		return temp;
	}
    /**
     * Adds To Heap
     * @param e E
     */
    public boolean add(E e) {
        heap.add(e);
        sortHeap();
        return true;
    }
    /**
     * Checks For an Element
     * @param e E
     */
    public Boolean contains(E e) {
        for (int i = 0; i < heap.size(); i++)
            if(heap.get(i).compareTo(e)==0)
                return true;
        return false;
    }
    /**
     * Returns The Data If It Is Exist
     * @param e E
     */
    public E findElement(E e) {
        for (int i = 0; i < heap.size(); i++)
            if(heap.get(i).compareTo(e)==0)
                return heap.get(i);
        return null;
    }
    /**
     * Removes The Peak
     */
    public E remove() {
        if( heap.size() == 0 )
			throw new NoSuchElementException();

		return poll();
    }
    /**
     * Merges With Other Heap
     * @param other MyHeap<E> 
     */
    public Boolean mergeHeap(MyHeap<E> other) {
        if (other== null || other.size()==0)
            return false;
    
        MyHeap<E> temp = other;
        while (temp.size()!=0)
            this.add(temp.poll());
    
        return true;
    }
    /**
     * Removes Nth Largest Element
     * @param n int
     */
    public E removeNthLargest(int n) {
        if (n<0 || n> heap.size()) 
            throw new IndexOutOfBoundsException();
        if (n==1)
            return heap.remove(0);
        
        E max=heap.get(0) ,temp=min();
        E max2= temp;
        int k=0;//location of element
        for (int i = 0; i < n-1; i++){
            max2=temp;
            for (int j = 0; j < heap.size(); j++)
                if(heap.get(j).compareTo(max)<0 && max2.compareTo(heap.get(j))<0){
                    max2=heap.get(j); k=j;
                }
            max=max2;
        }
        swap(k, heap.size()-1);
        heap.remove(heap.size()-1);
        sortHeap();
        return max2;
    }
    /**
     * Swaps 2 value
     * @param index1 int
     * @param index2 int
     */
    private void swap(int index1,int index2){
        E temp=heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
    /**To String */
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < heap.size(); i++)
            str.append(heap.get(i)+" ");
        return str.toString();
    }
    /**
     * Returns The Min Value of Heap
     */
    private E min(){
        E temp=heap.get(0);
        for (int i = 0; i < heap.size(); i++)
            if (temp.compareTo(heap.get(i))>0) 
                temp=heap.get(i);
        
        return temp;
    }
    /**
     * Returns HeapIter Of Heap 
     */
    public HeapIter heapIterator(){
        return new HeapIter();
    }
    /**Protected Inner Class Of MyHeap<E> */
    protected class HeapIter implements Iterator<E>{
        
        private int last = -1;
        private int location = 0;
        private int iterSize;
        /**Constructor */
        HeapIter(){
            iterSize=heap.size();
        }
        /**HasNext Method Of HeapIter */
        public boolean hasNext(){
            if (iterSize != location){
                return true;
            }else
                return false;
        }
        /**Next Method Of HeapIter */
        public E next(){
            if(!hasNext()) 
                throw new NoSuchElementException();

            last++;
            return  heap.get(location++);
        }
        /**Extra Set Method Of HeapIter */
        public E set(E item) {
            if(last < 0 ) 
                throw new IndexOutOfBoundsException();

            E temp = heap.get(last);
            heap.set(last,item);
            sortHeap();
            return temp;
        }
    }
    /**Oveerride Of Iterator */
    @Override
    public Iterator<E> iterator() {
        return new HeapIter();
    }
    /**
     * Override Of CompareTo
     * @param o MyHeap<E>
     */
    @Override
    public int compareTo(MyHeap<E> o) {     
        return heap.get(0).compareTo(o.peek());
    }
}
