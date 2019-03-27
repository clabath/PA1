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
	private Node nil;
	private int size, height; //variables to store the size and the height of the tree
	/**
	 * RB Tree constructor. It initializes nil node as well.
	 */
	public RBTree() {
		
		nil = Node.createNILNode();
		root = nil;
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
	
	public boolean removeNode(Node node){    // removes node, returns true if successful; //Can probably remove this
		
		size--;
		return true;
	}
	
	
	
	//Add more functions as  you see fit.
}
