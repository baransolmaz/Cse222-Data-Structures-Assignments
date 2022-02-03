public class BSTHeapTree<E extends Comparable<E>> {
    protected BinarySearchTree<MyHeap<Data<E>> > tree;
    private final int MAX_SIZE=7;
 /////  
    private static class Helper<E extends Comparable<E>> {
        private BinarySearchTree.Node<MyHeap<Data<E>>> node;
        private int count; 
        Helper(BinarySearchTree.Node<MyHeap<Data<E>>> n,int i){
            node=new BinarySearchTree.Node<>(new MyHeap<>());
            node=n;   count=i;
        } 
    } 
/////
    /**Constuctor */
    BSTHeapTree(){
        tree = new BinarySearchTree<MyHeap<Data<E>>>();
        tree.add(new MyHeap<Data<E>>());
    }
    /**
     * Add Method Of BSTHeapTree 
     * @param data E
     */
    public int add(E data) {
        Helper<E> helpMe=new Helper<E>(null,-1);
        helpMe=addToHeap(new Data<E>(data),tree.root);
        tree.root=helpMe.node;
        return helpMe.count;
    }
    /**
     * Helper Method For Add 
     * @param data Data<E>
     * @param root BinarySearchTree.Node<MyHeap<Data<E>>>
     */
    private Helper<E> addToHeap(Data<E> data,
                                BinarySearchTree.Node<MyHeap<Data<E>>> root){
        Helper<E> helpMe=new Helper<E>(null,-1);
        int count=1;
        if (root==null){
            root=new BinarySearchTree.Node<>(new MyHeap<>(data));
            helpMe=new Helper<E>(root,count);
            return helpMe;
        }
        if (root.data.size() <MAX_SIZE ) {
            if(root.data.contains(data)){
                count=root.data.findElement(data).increaseCount();
            }else
                root.data.add(data);
            helpMe=new Helper<E>(root,count);
        }else if (root.data.contains(data)){
            count=root.data.findElement(data).increaseCount();
            helpMe=new Helper<E>(root,count);
        }else if (data.compareTo(root.data.peek())<0) {
            helpMe=addToHeap(data, root.left);
            root.left=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }else {
            helpMe=addToHeap(data, root.right);
            root.right=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }
        return helpMe;
    }
    /**
     * Remove Method Of BSTHeapTree
     * @param data E
     */
    public int remove(E data) {
        Helper<E> helpMe=new Helper<E>(null,-1);
        helpMe=removeFromHeap(new Data<E>(data),tree.root);
        tree.root=helpMe.node;
        return helpMe.count;
    }
    /**
    * Helper Method For Remove 
    * @param data Data<E>
    * @param root BinarySearchTree.Node<MyHeap<Data<E>>>
    */
    private Helper<E> removeFromHeap(Data<E> data,
                            BinarySearchTree.Node<MyHeap<Data<E>>> root){
        Helper<E> helpMe=new Helper<E>(null,-1);
        int count=-1;
        if (root==null){
            helpMe=new Helper<E>(root,count);
            return helpMe;
        }
        if (root.data.contains(data)){
            Data<E> temp=root.data.findElement(data);
            count=temp.decreaseCount();
            if (count==0) {
                if (root.data.size()==1) {
                    tree.remove(root.data);
                    return new Helper<E>(null,count);
                }else
                    temp.changeData(getLastPoll(root));
                root.data.sortHeap();
            }
            helpMe=new Helper<E>(root,count);
        }else if (data.compareTo(root.data.peek())<0) {
            helpMe=removeFromHeap(data, root.left);
            root.left=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }else {
            helpMe=removeFromHeap(data, root.right);
            root.right=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }
        root.data.sortHeap();
        return helpMe;
    }
    /**
     * Helper Method For Remove 
     * Returns Child Data 
     * @param root BinarySearchTree.Node<MyHeap<Data<E>>>
     */
    private Data<E> getLastPoll(BinarySearchTree.Node<MyHeap<Data<E>>> root) {
        if (root.left != null) 
            return getLastPoll(root.left);

        if (root.right != null)
            return getLastPoll(root.right);

        Data<E> temp;
        if (root.data.size()==1) {
            temp =root.data.peek();
            tree.remove(root.data);
            return temp;
        }
        temp =root.data.poll();
        return temp;
    }
    /**
     * Finds The Target Data
     * @param data E
     */
    public int find(E target) {
        Helper<E> helpMe=new Helper<E>(null,-1);
        helpMe=findData(new Data<E>(target),tree.root);
        tree.root=helpMe.node;
        return helpMe.count;
    }
    /**
     * Helper Method For Find 
     * @param data Data<E>
     * @param root BinarySearchTree.Node<MyHeap<Data<E>>>
     */
    private Helper<E> findData(Data<E> data,
                            BinarySearchTree.Node<MyHeap<Data<E>>> root){
        Helper<E> helpMe=new Helper<E>(null,-1);
        int count=-1;
        if (root==null){
            helpMe=new Helper<E>(root,count);
            return helpMe;
        }
        if (root.data.contains(data)){       
            helpMe=new Helper<E>(root, root.data.findElement(data).getCount());
        }else if (data.compareTo(root.data.peek())<0) {
            helpMe=findData(data, root.left);
            root.left=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }else {
            helpMe=findData(data, root.right);
            root.right=helpMe.node;
            helpMe=new Helper<E>(root,helpMe.count);
        }
        return helpMe;
    }
    /**
     * Returns Mode Of BSTHeapTree
     */
    public E find_mode() {
        Data<E> temp = findMode(tree.root); 
        return temp.getData();
    }
    /**
     * Helper Method For Find_Mode
     * @param root BinarySearchTree.Node<MyHeap<Data<E>>>
     */
    private Data<E> findMode(BinarySearchTree.Node<MyHeap<Data<E>>> root){
        Data<E> modeLeft=new Data<E>(null);
        modeLeft.setCount(0);
        Data<E> modeRight=new Data<E>(null);
        modeRight.setCount(0);
        if (root==null)
            return null;
        if (root.left!=null)      
            modeLeft=findMode(root.left);
        if (root.right!=null)
            modeRight=findMode(root.right);
 
        MyHeap<Data<E>>.HeapIter itr=root.data.heapIterator();
        while (itr.hasNext()) {
            Data<E> temp= itr.next();
            if (modeLeft.getCount()<=temp.getCount())
                modeLeft=temp;
        }
        return modeLeft.getCount()>=modeRight.getCount() ? modeLeft : modeRight;
    }
    /**To String */
    public String toString(){
        return tree.toString();
    }
}