import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class Tree<E extends Comparable<E>>{
    protected Node < E > root;
	/**Inner Static Class */
    protected static class Node < E >implements Serializable {
        protected E data;
        protected ArrayList<Node < E >> nodes;
        /** Construct a node with given data and no children.
            @param data The data to store in this node
         */
        public Node(E data) {
          this.data = data;
          nodes=new ArrayList<Node < E >>();
        }
        /** Return a string representation of the node.
            @return A string representation of the data fields
         */
        public String toString() {
        	StringBuilder str=new StringBuilder();
			for (int i=0;i<nodes.size();i++ ) {
				str.append(i+1+") "+nodes.get(i).data+"\n");
			}
			return str.toString();
        }
		/**
		 * Existing Check
		 * @param item E
		 * @return Boolen
		 */
		public boolean contains(E item){
			for (int i=0;i<nodes.size();i++ ) 			
				if (nodes.get(i).data.equals(item))
					return true;
			return false;
		}
		/**
		 * Returns Index Of Item
		 * @param item E
		 * @return int
		 */
		public int find(E item){
			for (int i=0;i<nodes.size();i++ ) 			
				if (nodes.get(i).data.equals(item))
					return i;
			return -1;
		}
    }
	/**
	 * Constructor
	 * @param item E
	 */
	public Tree(E item){
		root=new Node<E>(item);
	}
	/**
	 * Constructor
	 * @param root Node<E>
	 */
	protected Tree(Node < E > root) {
		this.root = root;
	}
	/**
	 * Adds in Tree
	 * @param item E
	 * @return Boolean
	 */
	public boolean add(E item){
		root = add(root,item,0);
		return true;
	}
	/**
	 * Helper Method For Add
	 * @param localRoot Node<E>
	 * @param item E
	 * @param index int
	 * @return Node<E>
	 */
	private Node < E > add(Node < E > localRoot, E item,int index) {
		String[] datas=item.toString().split(" >> ");
		if (index==datas.length) {
			return localRoot;
		}
		Node<E> temp= new Node < E > ((E)datas[index]);
		if (localRoot.nodes.isEmpty()){
			localRoot.nodes.add(temp);
			localRoot.nodes.set(0,add(localRoot.nodes.get(0),item,index+1));
			return localRoot;
		}
		if (localRoot.contains((E)datas[index])) {
			int nodeIndex=localRoot.find((E)datas[index]);
			temp=add(localRoot.nodes.get(nodeIndex),item,index+1);
			localRoot.nodes.set(nodeIndex,temp);
			return localRoot;
		}else{
			localRoot.nodes.add(temp);
			temp=add(localRoot.nodes.get(localRoot.nodes.size()-1),item,index+1);
			localRoot.nodes.set(localRoot.nodes.size()-1,temp);
			return localRoot;
		}
	}
	/**toString */
	public String toString(){
		return root.toString();
	}
}
