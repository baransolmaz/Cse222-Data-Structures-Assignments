import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("\n###### PART 1 #####\n");
        try {
            part1();
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        
        System.out.println("\n###### PART 2 #####\n");
        try {
            part2();
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        System.out.println("\n###### PART 3 #####\n");
        try {
            part3();
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
    }
    private static void part1() {
        System.out.println("----- Generating Unique Random Numbers -----");
        ArrayList<Integer> randomNumbers= new ArrayList<Integer>();
        generateRandoms(randomNumbers,20);
        System.out.println("\n----- NavigableSet Using Skip-List -----");
        NavigableSkip<Integer> skip=new NavigableSkip<Integer>();
        System.out.println("\n-- Insertion --");
        for (int i = 0; i < 10; i++) {
            System.out.print(".insert("+randomNumbers.get(i)+")\t"); skip.insert(randomNumbers.get(i));
            System.out.println(".insert("+randomNumbers.get(10+i)+")"); skip.insert(randomNumbers.get(i+10));
        }
        System.out.println(skip);

        System.out.println("\n-- Deletion --");
        for (int i = 0; i < 4; i++) {
            System.out.print(".delete("+randomNumbers.get(i)+")\t"); skip.delete(randomNumbers.get(i));
            System.out.println(".delete("+randomNumbers.get(10+i)+")"); skip.delete(randomNumbers.get(i+10));
        }
        System.out.println(skip);

        System.out.println("\n-- Descending Iterator --");
        Iterator<Integer> iter=skip.descendingIterator();
        while (iter.hasNext()) {
            System.out.print(iter.next()+" ");
        }
        System.out.println();
        System.out.println("\n----- NavigableSet Using AVL Tree -----");
        NavigableAVL<Integer> avl=new NavigableAVL<Integer>();
        for (int i = 0; i < 10; i++) {
            System.out.print(".insert("+randomNumbers.get(i)+")\t"); avl.insert(randomNumbers.get(i));
            System.out.println(".insert("+randomNumbers.get(10+i)+")"); avl.insert(randomNumbers.get(i+10));
        }
        //System.out.println(avl);
        System.out.println("\n-- Iterator --");
        Iterator<Integer> iter2=avl.iterator();
        while (iter2.hasNext()) {
            System.out.print(iter2.next()+" ");
        }

        System.out.println("\n-- Head Set - Tail Set --");
        System.out.println(".headSet("+randomNumbers.get(5)+")-> "+avl.headSet(randomNumbers.get(5)));
        System.out.println(".tailSet("+randomNumbers.get(5)+")-> "+avl.tailSet(randomNumbers.get(5))+"\n");
        
        System.out.println(".headSet("+randomNumbers.get(15)+")-> "+avl.headSet(randomNumbers.get(15)));
        System.out.println(".tailSet("+randomNumbers.get(15)+")-> "+avl.tailSet(randomNumbers.get(15)));

    }
    private static void part2() {
        RedBlackTree<Integer> redBlack= new RedBlackTree<Integer>();
        AVLTree<Integer> avl= new AVLTree<Integer>();
        RedBlackTree<Integer> both= new RedBlackTree<Integer>();
        BinarySearchTree<Integer> bst= new BinarySearchTree<Integer>();
        System.out.println("Only Red-Black Tree With {3,2,15,8,4,9}");
        redBlack.add(3);
        redBlack.add(2);
        redBlack.add(15);
        redBlack.add(8);
        redBlack.add(4);
        redBlack.add(9);
        System.out.println(redBlack);
        bst.checkTree(redBlack);
        System.out.println("\nOnly AVL Tree With {12,8,18,5,11,17,4}");
        avl.add(12);
        avl.add(8);
        avl.add(18);
        avl.add(5);
        avl.add(11);
        avl.add(17);
        avl.add(4);
        System.out.println(avl);
        bst.checkTree(avl);
        System.out.println("\nNeither Tree With Degenerate Tree {8,9,7,6,5,4,3}");
        bst.add(8);
        bst.add(9);
        bst.add(7);
        bst.add(6);
        bst.add(5);
        bst.add(4);
        bst.add(3);
        System.out.println(bst);
        bst.checkTree(bst);
        System.out.println("\nBoth Tree With Perfect Tree {10,5,15,3,6,12,17}");
        both.add(10);
        both.add(5);
        both.add(15);
        both.add(3);
        both.add(6);
        both.add(12);
        both.add(17);
        System.out.println(both);
        bst.checkTree(both);

    }
    private static void part3(){
        ArrayList<Integer> randomNumbers= new ArrayList<Integer>();
        generateRandoms(randomNumbers,80100);
        long timeSmall,timeSmall2;
        float average=0;
///////////////////////////////////////////////////////////////////////////////
        System.out.println("\nAdding 100 Elements On The 10,000 Elements \n");
        for (int j = 0; j < 10; j++) {
            BinarySearchTree<Integer> bst1 =new BinarySearchTree<Integer>();
            
            for (int i = 0; i < 10000; i++)
                bst1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                bst1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();

            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Binary Search Tree 10K+ Time : "+average / 10+" ms.");
        average=0;

        for (int j = 0; j < 10; j++) {
            RedBlackTree<Integer> redBlack1 =new RedBlackTree<Integer>();
            
            for (int i = 0; i < 10000; i++)
                redBlack1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                redBlack1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Red Black Tree 10K+ \tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            TwoThreeTree<Integer> twoThree1 =new TwoThreeTree<Integer>();
            
            for (int i = 0; i < 10000; i++)
                twoThree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                twoThree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
        
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("2-3 Tree 10K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            BTree<Integer> bTree1 =new BTree<Integer>(6);
            
            for (int i = 0; i < 10000; i++)
                bTree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                bTree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("B Tree 10K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            SkipList<Integer> skip1 =new SkipList<Integer>();
            
            for (int i = 0; i < 10000; i++)
                skip1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                skip1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("SkipList 10K+ \t\tTime : "+average / 10+" ms.");
        average=0;
/////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nAdding 100 Elements On The 20,000 Elements \n");
        for (int j = 0; j < 10; j++) {
            BinarySearchTree<Integer> bst1 =new BinarySearchTree<Integer>();
            
            for (int i = 0; i < 20000; i++)
                bst1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                bst1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();

            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Binary Search Tree 20K+ Time : "+average / 10+" ms.");
        average=0;

        for (int j = 0; j < 10; j++) {
            RedBlackTree<Integer> redBlack1 =new RedBlackTree<Integer>();
            
            for (int i = 0; i < 20000; i++)
                redBlack1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                redBlack1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Red Black Tree 20K+ \tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            TwoThreeTree<Integer> twoThree1 =new TwoThreeTree<Integer>();
            
            for (int i = 0; i < 20000; i++)
                twoThree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                twoThree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
        
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("2-3 Tree 20K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            BTree<Integer> bTree1 =new BTree<Integer>(6);
            
            for (int i = 0; i < 20000; i++)
                bTree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                bTree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("B Tree 20K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            SkipList<Integer> skip1 =new SkipList<Integer>();
            
            for (int i = 0; i < 20000; i++)
                skip1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                skip1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("SkipList 20K+ \t\tTime : "+average / 10+" ms.");
        average=0;
////////////////////////////////////////////////////////////////////////////
        System.out.println("\nAdding 100 Elements On The 40,000 Elements \n");
        for (int j = 0; j < 10; j++) {
            BinarySearchTree<Integer> bst1 =new BinarySearchTree<Integer>();
            
            for (int i = 0; i < 40000; i++)
                bst1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                bst1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();

            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Binary Search Tree 40K+ Time : "+average / 10+" ms.");
        average=0;

        for (int j = 0; j < 10; j++) {
            RedBlackTree<Integer> redBlack1 =new RedBlackTree<Integer>();
            
            for (int i = 0; i < 40000; i++)
                redBlack1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                redBlack1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Red Black Tree 40K+ \tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            TwoThreeTree<Integer> twoThree1 =new TwoThreeTree<Integer>();
            
            for (int i = 0; i < 40000; i++)
                twoThree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                twoThree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
        
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("2-3 Tree 40K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            BTree<Integer> bTree1 =new BTree<Integer>(6);
            
            for (int i = 0; i < 40000; i++)
                bTree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                bTree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("B Tree 40K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            SkipList<Integer> skip1 =new SkipList<Integer>();
            
            for (int i = 0; i < 40000; i++)
                skip1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                skip1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("SkipList 40K+ \t\tTime : "+average / 10+" ms.");
        average=0;
//////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nAdding 100 Elements On The 80,000 Elements \n");
        for (int j = 0; j < 10; j++) {
            BinarySearchTree<Integer> bst1 =new BinarySearchTree<Integer>();
            
            for (int i = 0; i < 80000; i++)
                bst1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                bst1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();

            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Binary Search Tree 80K+ Time : "+average / 10+" ms.");
        average=0;

        for (int j = 0; j < 10; j++) {
            RedBlackTree<Integer> redBlack1 =new RedBlackTree<Integer>();
            
            for (int i = 0; i < 80000; i++)
                redBlack1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                redBlack1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("Red Black Tree 80K+ \tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            TwoThreeTree<Integer> twoThree1 =new TwoThreeTree<Integer>();
            
            for (int i = 0; i < 80000; i++)
                twoThree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                twoThree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
        
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("2-3 Tree 80K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            BTree<Integer> bTree1 =new BTree<Integer>(6);
            
            for (int i = 0; i < 80000; i++)
                bTree1.insert(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++)
                bTree1.insert(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("B Tree 80K+ \t\tTime : "+average / 10+" ms.");
        average=0;
        
        for (int j = 0; j < 10; j++) {
            SkipList<Integer> skip1 =new SkipList<Integer>();
            
            for (int i = 0; i < 80000; i++)
                skip1.add(randomNumbers.get(i));
            
            timeSmall= System.nanoTime();
            for (int i = 80000; i < 80100; i++) 
                skip1.add(randomNumbers.get(i));
            timeSmall2= System.nanoTime();
            
            average+=(timeSmall2-timeSmall) / 1000000F;
        }
        System.out.println("SkipList 80K+ \t\tTime : "+average / 10+" ms.");
        average=0;
    }
    private static void generateRandoms(ArrayList<Integer> randomNumbers,int value) {
        Random rand=new Random(); 
        while(randomNumbers.size()<value){
            int temp=rand.nextInt(1000000);
            if (!randomNumbers.contains(temp))
                randomNumbers.add(temp);
        }
    }
}