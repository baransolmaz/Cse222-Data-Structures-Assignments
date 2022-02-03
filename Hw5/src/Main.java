import java.util.*;
public class Main {
    public static void main(String[] args) {
/////////////////////////////////////////////////////////////////////////////////////        
        try {
            System.out.println("--------- Part 1 ---------");
            MyHashMap<Integer,String> hashMap= new MyHashMap<Integer,String>();
            
            for (int i = 0; i < 10; i++) {
                System.out.println(".put("+i+",\""+i+"\") "+hashMap.put(i, ""+i+"" ));
            }
            System.out.println(hashMap);
            System.out.println(".iterator(5)");
            MyHashMap<Integer,String>.MapIterator iter= hashMap.iterator(5);

            while (iter.hasNext()) {
                System.out.print(".next() "+iter.next()+"\t");
                System.out.println(".prev() "+iter.prev()+"\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
//////////////////////////////////////////////////////////////////////////////////////        
        System.out.println("--------- Part 2 ---------");
        System.out.println("\n1) Chaing by Using Linked List");
        System.out.println("\n1-a) Small Data Size :15 ");
        int size=15;
        long timeSmall,timeSmall2;
        try{
            HashTableLinked<Integer,String> hashLinkedSmall= new HashTableLinked<Integer,String>();
            
            timeSmall= System.nanoTime();
            for (int i = 0; i < size; i++){
                System.out.println(".put("+i*2+",\""+i+"\") "+hashLinkedSmall.put(i*2, ""+i+"" ));
            }
            timeSmall2= System.nanoTime();
            System.out.println("Time : "+(timeSmall2-timeSmall) / 1000000F+" ms.");
            
            System.out.println(hashLinkedSmall);
            timeSmall= System.nanoTime();
            for (int i = 0; i < size/2; i++){
                System.out.println(".remove("+i*2+") "+hashLinkedSmall.remove(i*2));
            }
            timeSmall2= System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");
            
            System.out.println(hashLinkedSmall);
            timeSmall= System.nanoTime();
            for (int i = size/2; i < size; i+=(size/10)){
                System.out.println(".put("+i*2+",\""+i*(size/5)+"\") "+hashLinkedSmall.put(i*2, ""+i*(size/5)+"" ));
            }
            timeSmall2= System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");
            
            System.out.println(hashLinkedSmall);
            timeSmall= System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=(size/10)){
                System.out.println(".get("+i*2+") "+hashLinkedSmall.get(i*2));
            }
            timeSmall2= System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashLinkedSmall);
            timeSmall= System.nanoTime();
            for (int i = -10; i < 0; i++){
                System.out.println(".get("+i+") "+hashLinkedSmall.get(i));
            }
            timeSmall2= System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
        }
//////        
        System.out.println("\n1-b) Medium Data Size :1000 ");
        size=1000;
        long timeMedium,timeMedium2;
        try{
            HashTableLinked<Integer,String> hashLinkedMedium= new HashTableLinked<Integer,String>();
            System.out.println("0 to 999 added");
            
            timeMedium= System.nanoTime();
            for (int i = 0; i < size; i++){
                hashLinkedMedium.put(i*2, ""+i+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");
            
            //System.out.println(hashLinkedMedium);
            
            timeMedium= System.nanoTime();
            System.out.println("0 to 499 removed");
            for (int i = 0; i < size/2; i++){
                hashLinkedMedium.remove(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashLinkedMedium);
            System.out.println("500 to 999 every even number added with different values ");
            timeMedium= System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashLinkedMedium.put(i*2, ""+i*2+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashLinkedMedium);
            System.out.println("501 to 999 every odd number gotten with get() method ");
            timeMedium= System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashLinkedMedium.get(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            System.out.println("-10 to 0 every number gotten with get() method --Non-Exist");
            timeMedium= System.nanoTime();
            for (int i = -10; i < 0; i++){
                hashLinkedMedium.get(i);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
        }
/////
        System.out.println("\n1-c) Large Data Size :10000 ");
        size=10000;
        long timeLarge,timeLarge2;
        try {
            HashTableLinked<Integer,String> hashLinkedLarge= new HashTableLinked<Integer,String>();
            System.out.println("0 to 9999 added");
            
            timeLarge = System.nanoTime();
            for (int i = 0; i < size; i++){
                hashLinkedLarge.put(i*2, ""+i+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
            
            //System.out.println(hashLinkedLarge);
            
            System.out.println("0 to 4999 removed");
            timeLarge = System.nanoTime();
            for (int i = 0; i < size/2; i++){
                hashLinkedLarge.remove(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            //System.out.println(hashLinkedLarge);
            System.out.println("5000 to 9999 every even number added with different values ");
            timeLarge = System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashLinkedLarge.put(i*2, ""+i*2+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            //System.out.println(hashLinkedLarge);
            System.out.println("5001 to 9999 every odd number gotten with get() method ");
            timeLarge = System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashLinkedLarge.get(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            System.out.println("-10 to 0 every number gotten with get() method --Non-Exist");
            timeLarge = System.nanoTime();
            for (int i =-10; i < 0; i++){
                hashLinkedLarge.get(i);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n2) Chaing by Using Tree Set");
        System.out.println("\n2-a) Small Data Size :15 \n");
        size=15;
        try {
            HashTableTree<Integer,String> hashTreeSmall= new HashTableTree<Integer,String>();
            timeSmall=System.nanoTime();
            for (int i = 0; i < size; i++){
                System.out.println(".put("+i*2+",\""+i+"\") "+hashTreeSmall.put(i*2, ""+i+"" ));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashTreeSmall);
            timeSmall=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                System.out.println(".remove("+i*2+") "+hashTreeSmall.remove(i*2));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashTreeSmall);
            timeSmall=System.nanoTime();
            for (int i = size/2; i < size; i+=(size/10)){
                System.out.println(".put("+i*2+",\""+i*(size/10)+"\") "+hashTreeSmall.put(i*2, ""+i*(size/10)+"" ));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashTreeSmall);
            timeSmall=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=(size/10)){
                System.out.println(".get("+i*2+") "+hashTreeSmall.get(i*2));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashTreeSmall);
            timeSmall=System.nanoTime();
            for (int i = -10; i < 0; i++){
                System.out.println(".get("+i+") "+hashTreeSmall.get(i));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
/////////
        System.out.println("\n2-b) Medium Data Size :1000 ");
        size=1000;
        try {
            HashTableTree<Integer,String> hashTreeMedium= new HashTableTree<Integer,String>();
            System.out.println("0 to 999 added");
            timeMedium=System.nanoTime();
            for (int i = 0; i < size; i++){
                hashTreeMedium.put(i*2, ""+i+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashTreeMedium);
            System.out.println("0 to 499 removed");
            timeMedium=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                hashTreeMedium.remove(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashTreeMedium);
            System.out.println("500 to 999 every even number added with different values ");
            timeMedium=System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashTreeMedium.put(i*2, ""+i*2+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashTreeMedium);
            System.out.println("501 to 999 every odd number gotten with get() method ");
            timeMedium=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashTreeMedium.get(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            System.out.println("-10 to 0 every number gotten with get() method ---Non-Exist");
            timeMedium=System.nanoTime();
            for (int i = -10; i < 0; i++){
                hashTreeMedium.get(i);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
/////////
        System.out.println("\n2-c) Large Data Size :10000 ");
        size=10000;
        try {
            HashTableTree<Integer,String> hashTreeLarge= new HashTableTree<Integer,String>();
            System.out.println("0 to 9999 added");
            timeLarge=System.nanoTime();
            for (int i = 0; i < size; i++){
                hashTreeLarge.put(i*2, ""+i+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            //System.out.println(hashTreeLarge);
            System.out.println("0 to 4999 removed");
            timeLarge=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                hashTreeLarge.remove(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            //System.out.println(hashTreeLarge);
            System.out.println("5000 to 9999 every even number added with different values ");
            timeLarge=System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashTreeLarge.put(i*2, ""+i*2+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            //System.out.println(hashTreeLarge);
            System.out.println("5001 to 9999 every odd number gotten with get() method ");
            timeLarge=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashTreeLarge.get(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");

            System.out.println("-10 to 0 every number gotten with get() method ---Non-Exist");
            timeLarge=System.nanoTime();
            for (int i =-10; i < 0; i++){
                hashTreeLarge.get(i);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
/////////////////////////////////////////////
        System.out.println("\n3) Chaing by Using Tree Set");
        System.out.println("\n3-a) Small Data Size :15 \n");
        size=15;
        try {
            HashTableCoalesced<Integer,String> hashCoalescedSmall= new HashTableCoalesced<Integer,String>();
            timeSmall=System.nanoTime();
            for (int i = 0; i < size; i++){
                System.out.println(".put("+i*2+",\""+i+"\") "+hashCoalescedSmall.put(i*2, ""+i+"" ));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashCoalescedSmall);
            timeSmall=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                System.out.println(".remove("+i*2+") "+hashCoalescedSmall.remove(i*2));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashCoalescedSmall);
            timeSmall=System.nanoTime();
            for (int i = size/2; i < size; i+=(size/10)){
                System.out.println(".put("+i*2+",\""+i*(size/5)+"\") "+hashCoalescedSmall.put(i*2, ""+i*(size/5)+"" ));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");

            System.out.println(hashCoalescedSmall);
            timeSmall=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=(size/10)){
                System.out.println(".get("+i*2+") "+hashCoalescedSmall.get(i*2));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");
            
            System.out.println(hashCoalescedSmall);
            timeSmall=System.nanoTime();
            for (int i =-10; i < 0; i++){
                System.out.println(".get("+i+") "+hashCoalescedSmall.get(i));
            }
            timeSmall2=System.nanoTime();
            System.out.println("Time: "+(timeSmall2-timeSmall) / 1000000F+" ms.");
        } catch (Exception e) {
           System.out.println(e.getMessage().toString());
        }
        
////////
        System.out.println("\n3-b) Medium Data Size :1000 ");
        size=1000;
        try {
            HashTableCoalesced<Integer,String> hashCoalescedMedium= new HashTableCoalesced<Integer,String>();
        
            System.out.println("0 to 999 added");
            timeMedium=System.nanoTime();
            for (int i = 0; i < size; i++){
                hashCoalescedMedium.put(i*2, ""+i+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashCoalescedMedium);
            System.out.println("0 to 499 removed");
            timeMedium=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                hashCoalescedMedium.remove(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashCoalescedMedium);
            System.out.println("500 to 999 every even number added with different values ");
            timeMedium=System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashCoalescedMedium.put(i*2, ""+i*2+"" );
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            //System.out.println(hashCoalescedMedium);
            System.out.println("501 to 999 every odd number gotten with get() method ");
            timeMedium=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashCoalescedMedium.get(i*2);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");

            System.out.println("-10 to 0 every number gotten with get() method ---Non-Exist");
            timeMedium=System.nanoTime();
            for (int i = -10; i < 0; i++){
                hashCoalescedMedium.get(i);
            }
            timeMedium2=System.nanoTime();
            System.out.println("Time: "+(timeMedium2-timeMedium) / 1000000F+" ms.");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        
////////
        System.out.println("\n3-c) Large Data Size :10000 ");
        size=10000;
        try {
            HashTableCoalesced<Integer,String> hashCoalescedLarge= new HashTableCoalesced<Integer,String>();
        
            System.out.println("0 to 9999 added");
            timeLarge=System.nanoTime();
            for (int i = 0; i < size; i++){
                hashCoalescedLarge.put(i*2, ""+i+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
    
            //System.out.println(hashCoalescedLarge);
            System.out.println("0 to 4999 removed");
            timeLarge=System.nanoTime();
            for (int i = 0; i < size/2; i++){
                hashCoalescedLarge.remove(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
    
            //System.out.println(hashCoalescedLarge);
            System.out.println("5000 to 9999 every even number added with different values ");
            timeLarge=System.nanoTime();
            for (int i = size/2; i < size; i+=2){
                hashCoalescedLarge.put(i*2, ""+i*2+"" );
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
    
            //System.out.println(hashCoalescedLarge);
            System.out.println("5001 to 9999 every odd number gotten with get() method ");
            timeLarge=System.nanoTime();
            for (int i = (size/2)+1; i < size; i+=2){
                hashCoalescedLarge.get(i*2);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
    
            System.out.println("-10 to 0 every odd number gotten with get() method --Non-Exist");
            timeLarge=System.nanoTime();
            for (int i = -10; i < size; i++){
                hashCoalescedLarge.get(i);
            }
            timeLarge2=System.nanoTime();
            System.out.println("Time: "+(timeLarge2-timeLarge) / 1000000F+" ms.");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
       
    }
}