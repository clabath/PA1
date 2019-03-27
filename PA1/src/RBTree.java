/**
 * Team members:
 * @author Kyle Bowden
 * @author Kyle Trom
 * @author Cassandra Labath
 * 
 * RBTree class, maintains operations on RBTree.
 */
public class RBTree {
	private Node root; 
	private Node nil; //this is the nil node, don't know how to use it yet
	private int size, height; //variables to store the size and the height of the tree
	/**
	 * RB Tree constructor. It initializes nil node as well.
	 */
	public RBTree() {
		root = null;
		size = 0;
							//have to initialize nil node --- return later
	}
	
	/**
	 * Returns the root of teh tree.
	 * @return
	 */
	public Node getRoot() {
		return root;
	}
	
	/**
	 * Returns reference for the nil node, for the rbTree.
	 * @return
	 */
	public Node getNILNode() {
		nil = new Node(-1,-1);
		return nil;
	}
	
	/**
	 * Returns the number of internal nodes in the tree.
	 * @return 
	 */
	public int getSize() {
		return size;
	}
	
	
	/**
	 * Returns the height of the tree.
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean insertNode(Node node) {	//inserts node, returns true if successful
														//we might want this to take a key as argument -- need to consider our goals

		size++;
		return true;
	}
	
	public boolean removeNode(Node node){    // removes node, returns true if successful;
		
		size--;
		return true;
	}
	
	
	
	//Add more functions as  you see fit.
}
