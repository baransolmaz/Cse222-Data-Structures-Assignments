public class Data<E extends Comparable<E>> implements Comparable< Data<E>> {
    private E data;
    private int count=0;

    Data(E e){
        data =e;
        count++;
    }
    /**
     * Overwrites the Data
     * @param e Data 
     */
    public void changeData(Data<E> e){
        this.data=e.getData();
        this.count=e.getCount();
    }

    /**
     * Returns Data
     */
    public E getData() {
        return data;
    }
    /**
     * Returns Count
     */
    public int getCount() {
        return count;
    }
    /** Increases Count*/
    public int increaseCount() {
        return ++count;
    }
    /**Decreases Count */
    public int decreaseCount() {
        return --count;
    }
    /**
     * Compare To Method
     * @param other Data<E>
     */
    public int compareTo(Data<E> other){
        return this.data.compareTo(other.getData());
    }
    /**To String */
    public String toString() {
        StringBuilder str=new StringBuilder();
        str.append(data).append(",").append(count);
        return str.toString();
    }
    /**
     * Changes Count
     * @param c int
     */
    public void setCount(int c){
        count=c;
    }
}
