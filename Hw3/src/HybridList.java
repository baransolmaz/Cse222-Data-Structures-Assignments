import java.util.*;
public class HybridList<E>{
    private final int MAX_NUMBER= 15;
    protected KWLinkedList<KWArrayList <E> > list;
    private int size;


    public HybridList() {
        list= new KWLinkedList<KWArrayList <E> >();
        list.add(new KWArrayList<E>());
        size=0;
    }
    public void add(E obj){
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp = new KWArrayList<E>();
        while (itr.hasNext()) {
            temp = itr.next();
            if (temp.size()<MAX_NUMBER) {
               temp.add(obj);
               size++;
               return;
            }
        }
        temp = new KWArrayList<E>();
        temp.add(obj);
        list.add(temp);
        size++;
    }
    public E remove(E obj){
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp;
        E data=null;
        while (itr.hasNext()) {
            temp = itr.next();
            for (int i = 0; i < temp.size(); i++)
                if (temp.get(i)== obj){
                    data= temp.remove(i);
                    if (temp.size()==0) 
                        itr.remove();
                }
        }
        size--;
        return data;
    }
    public E remove(int index){
        int ll=index/MAX_NUMBER;
        if (index<0 || ll>= list.size()) 
            throw new NoSuchElementException();
        int al= index%MAX_NUMBER;
        
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp=itr.next();
        for (int i = 0; i < ll; i++)
            temp = itr.next();
        size--;
        E data =temp.remove(al);
        if (temp.size()==0) 
            itr.remove();
        return data;
    }
    public E get(int index) {
        if (index<0 ||index>=size) 
            throw new NoSuchElementException();
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp= itr.next();
        while (index>=temp.size() && itr.hasNext()) {
            index-=temp.size();
            temp= itr.next();
        }
        if (temp!=null)
            return temp.get(index);
        return null;
    }
    public E set(int index, E newValue) {
        int ll=index/MAX_NUMBER;
        if (index<0 || ll> list.size()) 
            throw new NoSuchElementException();
        int al= index%MAX_NUMBER;
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp= itr.next();
        for (int i = 0; i < ll; i++){
            temp = itr.next();
        }
        return temp.set(al,newValue);
    }
    public void seeAll() {
        ListIterator<KWArrayList <E>> itr = list.listIterator();
        KWArrayList<E> temp;
        while (itr.hasNext()) {
            temp = itr.next();
            for (int i = 0; i < temp.size(); i++) {
                System.out.println(temp.get(i).toString()+" ");
            }
        }
    }
    public int size() {
        return size;
    }
}