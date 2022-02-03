import java.util.*;
class Main {
    public static void main(String[] args){
        System.out.println("\n-------PART 1-------");
        
        MyHeap<Integer> heap= new MyHeap<Integer>();
        MyHeap<Integer> heap2= new MyHeap<Integer>();
       
        for (int i = 0; i < 10; i++)
            heap.add(i*10);

        for (int i = 0; i < 10; i++)
            heap2.add(i*9);
        
        System.out.println("Heap: "+heap);
        System.out.println("\n1-a) Search For an Existing Element");
        System.out.println("\t.contains(50) : "+heap.contains(50));
        
        System.out.println("\n1-b) Search For an Non-Existing Element");
        System.out.println("\t.contains(5) : "+heap.contains(5));
        
        System.out.println("\nHeap2: "+heap2);
        System.out.println("2) Merge With Another Heap");
        System.out.println("\t.mergeHeap(heap2) : "+heap.mergeHeap(heap2));
        System.out.println("Heap : "+heap);
        
        System.out.println("\n3) Remove Nth Largest Element");
        System.out.println(".removeLargest(5) : "+heap.removeNthLargest(5));
        System.out.println("Heap : "+heap);
        System.out.println(".removeLargest(1) : "+heap.removeNthLargest(1));
        System.out.println("Heap : "+heap);
        
        System.out.println("\n4) Iterator Set");
        MyHeap<Integer>.HeapIter iter =heap.heapIterator();
        for (int i = 0; i < 5; i++) 
            iter.next();
        System.out.println("\titer.set(-100) : "+iter.set(-100)); 
        System.out.println("Heap : "+heap);
        

        System.out.println("\n-------PART 2-------");
        BSTHeapTree<Integer> treeHeap = new BSTHeapTree<Integer>();
        ArrayList<Integer> array= new ArrayList<Integer>();
        Random r = new Random();
        int temp;
        for (int i = 0; i < 3000; i++) {
            temp=r.nextInt(5000);
            array.add(temp);
            treeHeap.add(temp);
        }
        Collections.sort(array);
        //System.out.println("Array:\n"+array);
        //System.out.println("Heap Tree:\n"+treeHeap);

        System.out.println("Find an Existing Element");
        for (int i = 0; i < 50; i++) {
            System.out.print(".find("+array.get(i*15) +") : "+treeHeap.find(array.get(i*15)));
            System.out.println("\t .find("+array.get((i+50)*15) +") : "+treeHeap.find(array.get((i+50)*15)));
        }
        System.out.println("Find a Non-Existing Element");
        for (int i = 0; i < 10; i++) {
            System.out.println(".find("+(i+5000) +") : "+treeHeap.find(5000+i));
        }
    
        System.out.println("Mode Of Array : "+mode(array));
        System.out.println("Mode Of Tree : "+treeHeap.find_mode());

        //System.out.println("Array:\n"+array);
        //System.out.println("Heap Tree:\n"+treeHeap);

        System.out.println("Remove an Existing Element");
        for (int i = 0; i < 50; i++) {
            System.out.print(".remove("+array.get(i*15) +") : "+treeHeap.remove(array.get(i*15)));
            System.out.println("\t .remove("+array.get((i+50)*15) +") : "+treeHeap.remove(array.get((i+50)*15)));
        }
        System.out.println("Remove a Non-Existing Element");
        for (int i = 0; i < 10; i++) {
            System.out.println(".remove("+(i+5000) +") : "+treeHeap.remove(5000+i));
        }

        //System.out.println("Heap Tree:\n"+treeHeap);
                
    }
    public static int mode(ArrayList<Integer> array) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < array.size(); ++i) {
            int count = 0;
            for (int j = 0; j < array.size(); ++j) {
                if (array.get(j).compareTo(array.get(i))==0)
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = array.get(i);
            }
        }
        return maxValue;
    }
}