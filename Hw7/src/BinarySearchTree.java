/** A class to represent a binary search tree.
 *  @author Koffman and Wolfgang
 */

public class BinarySearchTree < E extends Comparable < E >>
        extends BinaryTree < E > implements SearchTree < E > {
    /** Return value from the public add method. */
    protected boolean addReturn;

    /** Return value from the public delete method. */
    protected E deleteReturn;

    //Methods
    /** Starter method find.
            @param target The Comparable object being sought
            @return The object, if found, otherwise null
     */
    public E find(E target) {
        return find(root, target);
    }

    /** Recursive find method.
            @param localRoot The local subtree�s root
            @param target The object being sought
            @return The object, if found, otherwise null
     */
    private E find(Node < E > localRoot, E target) {
        if (localRoot == null)
            return null;

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0)
            return localRoot.data;
        else if (compResult < 0)
            return find(localRoot.left, target);
        else
            return find(localRoot.right, target);
    }

    /** Starter method add.
         @param item The object being inserted
         @return true if the object is inserted, false
                         if the object already exists in the tree
     */
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /** Recursive add method.
            @param localRoot The local root of the subtree
            @param item The object to be inserted
            @return The new local root that now contains the
                            inserted item
     */
    private Node < E > add(Node < E > localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            addReturn = true;
            return new Node < E > (item);
        }
        else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        }
        else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /** Starter method delete.
            @param target The object to be deleted
            @return The object deleted from the tree
                            or null if the object was not in the tree
            @throws ClassCastException if target does not implement
                            Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /** Recursive delete method.
            @param localRoot The root of the current subtree
            @param item The item to be deleted
            @return The modified local root that does not contain
                            the item
     */
    private Node < E > delete(Node < E > localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            }
            else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            }
            else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node�s data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }
    /** Removes target from tree.
             @param target Item to be removed
             @return true if the object was in the tree, false otherwise
             @throws ClassCastException if target is not Comparable
     */
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /** Determine if an item is in the tree
            @param target Item being sought in tree
            @return true If the item is in the tree, false otherwise
            @throws ClassCastException if target is not Comparable
     */
    public boolean contains(E target) {
        return find(target) != null;
    }
    /** Find the node that is the inorder predecessor and replace it
            with its left child (if any).
            @param parent The parent of possible inorder predecessor (ip)
            @return The data in the ip
     */
    private E findLargestChild(Node < E > parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else {
            return findLargestChild(parent.right);
        }
    }
    /**
     * Checks The Tree Wheter It Is AVL Or Red-Black Tree
     * @param tree BinarySearchTree<E>
     * @return String Type Of Tree
     */
    public void checkTree(BinarySearchTree<E> tree) {
        
        if (checkAVL(tree.root)) {
            if (tree.isRed()) {
                System.out.println("Both"); 
            }else
                System.out.println("AVL Tree"); 
        }else{
            if (tree.isRed()) {
                System.out.println("Red Black"); 
            }else
                System.out.println("Neither");
        }
    }
    /**
     * Checks The Tree Wheter It Is AVL Or Not
     * @param localRoot Node < E >
     * @return boolean
     */
    private boolean checkAVL(Node < E > localRoot) {
        if (localRoot == null) {
            return true;
        }
        int right= height(localRoot.right);
        int left= height(localRoot.left);
        if (Math.abs(left-right)<2)
            if (checkAVL(localRoot.left) && checkAVL(localRoot.right) )
                return true;

        return false;
    }
    /**
     * Returns Height Of Tree
     * @param node BinaryTree.Node<E>
     * @return int 
     */
    private int height(BinaryTree.Node<E> node){
        if (node == null){
            return 0;
        }else
            return 1+Math.max(height(node.right),height(node.left));
    }
    /**
     * Returns Root Color
     * @return boolean
     */
    public boolean isRed() {
        return !root.isRed;
    }
}
