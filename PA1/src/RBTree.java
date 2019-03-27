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
		height = 0;
	}
	
	/**
	 * Returns the root of the tree.
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
		height = findHeight(root);
		return height;
	}
	
	//Naive method to find height of the tree based on recursion
	private int findHeight(Node node) {
		if (node == null) 
            return 0; 
        else 
        { 
            int l = findHeight(node.getLeft()); 
            int r = findHeight(node.getRight()); 
            //Find which height is greatest and return
            return Math.max(l, r) + 1;
        } 
    }
	
	public boolean isEmpty() {
		return root == nil;
	}
	
	/**
	 * inserts the new Node given in parameter to the RBTree then calls insertFixup to correct tree
	 * @param node
	 */
	public void insertNode(Node node) {	
		Node x = root;
		Node y = nil;
		while(x != nil) {
			y = x;
			if(node.getKey() < x.getKey()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		node.setParent(y);
		if(y==nil) {
			root = node;
		} else if (node.getKey() < y.getKey()) {
			y.setLeft(node);
		} else {
			y.setRight(node);
		}
		node.setLeft(nil);
		node.setRight(nil);
		node.setColor(0);
		size++;
		insertFixup(node);
		if(root.getRight() == null)
		{
			root.setRight(nil);
		} else if(root.getLeft() == null) {
			root.setLeft(nil);
		}
		return;
	}
	
	/**
	 * corrects RBTree after insert by rotating and changing colors
	 * @param node
	 */
	public void insertFixup(Node node) {
		while (node.getParent().getColor() == 0) {
			if(node.getParent() == node.getParent().getParent().getLeft()) {
				Node y = node.getParent().getParent().getRight();
				if(y.getColor() == 0) {
					node.getParent().setColor(1);
					y.setColor(1);
					node.getParent().getParent().setColor(0);
					node = node.getParent().getParent();
				} else {
					if(node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(node);
					}
					node.getParent().setColor(1);
					node.getParent().getParent().setColor(0);
					rightRotate(node.getParent().getParent());
				}
			} else {
				if(node.getParent() == node.getParent().getParent().getRight()) {
					Node y = node.getParent().getParent().getLeft();
					if(y.getColor() == 0) {
						node.getParent().setColor(1);
						y.setColor(1);
						node.getParent().getParent().setColor(0);
						node = node.getParent().getParent();
					} else {
						if(node == node.getParent().getLeft()) {
							node = node.getParent();
							leftRotate(node);
						}
						node.getParent().setColor(1);
						node.getParent().getParent().setColor(0);
						rightRotate(node.getParent().getParent());
					}
				}
			}
		}
		root.setColor(1);
		return;
	}
	
	public boolean removeNode(Node node){    // removes node, returns true if successful; //Can probably remove this
		//Todo: 
		size--;
		return true;
	}
	
	
	private void leftRotate(Node x)
	{
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if(y.getLeft() != null)
			y.getLeft().setParent(x);
		y.setParent(x.getParent());
		if(x.getParent() == null)
			root = y; 
		else if (x == x.getParent().getLeft())
			x.getParent().setLeft(y);
		else 
			x.getParent().setRight(y);
		y.setLeft(x);
		x.setParent(y);
	}
	private void rightRotate(Node x)
	{
		Node y = x.getLeft();
		x.setLeft(y.getRight());
		if(y.getRight() != null)
			y.getRight().setParent(x);
		y.setParent(x.getParent());
		if(x.getParent() == null)
			root = y; 
		else if (x == x.getParent().getRight())
			x.getParent().setRight(y);
		else 
			x.getParent().setLeft(y);
		y.setRight(x);
		x.setParent(y);
	}
	//Add more functions as  you see fit.
}
