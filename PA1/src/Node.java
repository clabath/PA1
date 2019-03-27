/**
 * Team members:
 * @author Kyle Bowden
 * @author Kyle Trom
 * @author Cassandra Labath
 * 
 * Node class for RBTree.
 */
public class Node {            //the node represents an endpoint
	Endpoint key;                   //the endpoint(assumed positive integers unless nil node)
	int p;                     //TODO p is a boolean of either +1 or -1 (may need to be changed talk to me)
	Node parent, left, right;  //variables for the node's location in tree
	enum Color {RED, BLACK};   //color of the nodes
	Color color; 
	int leftmost, rightmost;   //indicies of leftmost and rightmost endpoints in subtree rooted at v
	int val;                   //sum of p-values of nodes in the subtree rooted at v (including v)
	int maxval;                //TODO maximum value obtained by s(leftmost, i) for leftmost <= i <= rightmost TALK TO ME ABOUT THIS I HAVE CONCERNS
	Endpoint emax;                  //the key (endpoint) that maximizes s(leftmost, i) for leftmost <= i <= rightmost 

	
	public Node(int key, int p) //TODO this is probably not right, just setting something up right now
	{
		this.key = new Endpoint(key);
		this.p = p;
	}
	/**
	 * Returns the parent of this node.
	 * @return 
	 */
	public Node getParent() { 
		return parent; 				//returns the parent. if root, returns null
	}
	
	/**
	 * Returns the left child.
	 * @return
	 */
	public Node getLeft() {
		return left;
	}
	
	/**
	 * Returns the right child.
	 * @return
	 */
	public Node getRight() {
		return right;
	}
	
	/**
	 * Returns the endpoint value, which is an integer.
	 * @return
	 */
	public int getKey() {
		return key.getValue();
	}
	
	/**
	 * Returns the value of the functionpbased on this endpoint.
	 * @return
	 */
	public int getP() {
		return p;
	}
	
	/**
	 * Returns the val of the node as described in this assignment.
	 * @return
	 */
	public int getVal() {
		if(getKey() < 0) {
			val = 0;
		} else {
			val = left.val + p + right.val;
		}
		return val;
	}
	
	/**
	 * Returns the maxval of the node as described in this assignment.
	 * @return
	 */
	public int getMaxVal() {
		if (getKey() < 0) {
			maxval = 0;
		} else {
			maxval = Math.max(left.maxval, Math.max(left.val + p, left.val + p + right.maxval));
		}
		return maxval;
	}
	
	/**
	 * Returns theEndpointobject that this node represents.
	 * @return
	 */
	public Endpoint getEndpoint() {
		return key;
	}
	
	/**
	 * Returns anEndpointobject that represents emax. 
	 * Calling this method on the root node will give the End point object whose getValue() 
	 * provides a point of maximum overlap.
	 * @return
	 */
	public Endpoint getEmax() {
		if (getKey() < 0) {
			emax = key;
		} else if(Math.max(left.maxval, Math.max(left.maxval + p, left.maxval + p + right.maxval)) == left.maxval) {
			emax = left.key;
		} else if (Math.max(left.maxval, Math.max(left.maxval + p, left.maxval + p + right.maxval)) == (left.maxval + p)){
			emax = key;
		} else {
			emax = right.key;
		}
		return emax;
	}
	
	/**
	 * Returns 0 if red. Returns 1 if black.
	 * @return
	 */
	public int getColor() {
		return color.ordinal();
	}
	
	//Add more functions as  you see fit. 
	public void setParent(Node parent) // sets the parent of this node to a given node
	{
		this.parent = parent;

	}
	public void setRight(Node right) // sets the right child of this node to a given node
	{
		this.right = right;
	}
	
	public void setLeft(Node left) // sets the left child of this node to a given node
	{
		this.left = left;
	}
	
	public void setColor(Color color) //setting color with enum
	{
		this.color = color;
	}
	
	public void setColor(int color) // setting color with 0/1 as arguments
	{
		if (color == 0)
			this.color = Color.RED;
		else if (color == 1)
			this.color = Color.BLACK;
		else 
			throw new IllegalArgumentException("setColor ONLY ACCEPTS 0 AND 1 AS ARGUMENTS");
	}
	
	/**
	 * Creates a new nil node with p = -1, key = -1, val = 0, maxval = 0 and emax = key of -1
	 * @return
	 */
	public static Node createNILNode() {
		Node nil = new Node(-1,-1);
		nil.setColor(1);
		nil.getVal();
		nil.getMaxVal();
		nil.getEmax();
		return nil;
	}
	
	
	
	
	
	//TODO I believe more functions should be evaluating the 
	//	   the values of the functions (p, emax) but we may just 
	//	   calculate them in the get methods
	
	
}
