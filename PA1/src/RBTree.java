/**
 * Team members:
 * 
 * @author Kyle Bowden
 * @author Kyle Trom
 * @author Cassandra Labath
 * 
 *         RBTree class, maintains operations on RBTree.
 */
public class RBTree {
	private Node root;
	private Node nil;
	private int size, height; // variables to store the size and the height of the tree

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
	 * 
	 * @return
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Returns reference for the nil node, for the rbTree.
	 * 
	 * @return
	 */
	public Node getNILNode() {
		return nil;
	}

	/**
	 * Returns the number of internal nodes in the tree.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the height of the tree.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	// Naive method to find height of the tree based on recursion

	public void inorderPrint() {
		inorder(root);
	}

	public void preorderPrint() {
		preorder(root);
	}

	public void postorderPrint() {
		postorder(root);
	}

	private void inorder(Node node) {
		if (node == null)
			return;
		else {
			inorder(node.getLeft());
			if (node.getKey() > -1)
				System.out.println(
						"Key: " + node.getKey() + " ,Val:" + node.getVal() + " Emax: " + node.getEmax().getValue());
			inorder(node.getRight());
		}
	}

	private void preorder(Node node) {
		if (node == null)
			return;
		else {
			if (node.getKey() > -1) {
				System.out.print("Key: " + node.getKey());
				if (node.getColor() == 0) {
					System.out.println(" Color: Red");
				} else {
					System.out.println(" Color: Black");
				}
			}

			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}

	private void postorder(Node node) {
		if (node == null)
			return;
		else {

			postorder(node.getLeft());
			postorder(node.getRight());

			if (node.getKey() > -1) {
				System.out.print("Key: " + node.getKey());
				if (node.getColor() == 0) {
					System.out.println(" Color: Red");
				} else {
					System.out.println(" Color: Black");
				}
			}
		}
	}

	public boolean isEmpty() {
		return root == nil;
	}
	
	public boolean checkValidColors() {
		return checkColors(root);
	}
	private boolean checkColors(Node node) {
		if (node.isNil()) {
			return true;
		} else {
			boolean l = checkColors(node.getLeft());
			if(node.getColor() == 0 && ( node.getLeft().getColor() == 0 || node.getRight().getColor() == 0)){
				return false;
			}
			boolean r = checkColors(node.getRight());
			return l && r;
		}
	}

	/**
	 * inserts the new Node given in parameter to the RBTree then calls insertFixup
	 * to correct tree
	 * 
	 * @param node
	 */
	public void insertNode(Node node) {
		Node x = root;
		Node y = nil;
		int h = 0;
		while (x != nil) {
			y = x;
			if (node.getKey() < x.getKey()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
			h++;
		}
		node.setParent(y);
		if (y == nil) {
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
		height = Math.max(h, height);
		insertFixup(node);
		updateVals(node);
	}

	/**
	 * corrects RBTree after insert by rotating and changing colors
	 * 
	 * @param node
	 */
	public void insertFixup(Node node) {
		while (node.getParent().getColor() == 0) {
			if (node.getParent() == node.getParent().getParent().getLeft()) {
				Node y = node.getParent().getParent().getRight();
				if (y.getColor() == 0) {
					node.getParent().setColor(1);
					y.setColor(1);
					node.getParent().getParent().setColor(0);
					node = node.getParent().getParent();
				} else {
					if (node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(node);
					}
					node.getParent().setColor(1);
					node.getParent().getParent().setColor(0);
					rightRotate(node.getParent().getParent());
				}
			} else {
				Node y = node.getParent().getParent().getLeft();
				if (y.getColor() == 0) {
					node.getParent().setColor(1);
					y.setColor(1);
					node.getParent().getParent().setColor(0);
					node = node.getParent().getParent();
				} else {
					if (node == node.getParent().getLeft()) {
						node = node.getParent();
						rightRotate(node);
					}
					node.getParent().setColor(1);
					node.getParent().getParent().setColor(0);
					leftRotate(node.getParent().getParent());
				}
			}
		}
		root.setColor(1);
		return;
	}

	private void RBTransplant(Node u, Node v)
	{
		if(u.getParent() == nil)
			root = v;
		else if(u == u.getParent().getLeft())
		{
			u.getParent().setLeft(v);
		}
		else if(u == u.getParent().getRight())
		{
			u.getParent().setRight(v);
		}
		v.setParent(u.getParent());
	}
	public void removeNode(Node node) { // removes node, returns true if successful; //Can probably remove this
		Node y = node;
		Node x;
		int yOrigColor = y.getColor();
		if(node.getLeft() == nil)
		{
			x = node.getRight();
			RBTransplant(node, node.getRight());
		}
		else if (node.getRight() == nil)
		{
			x = node.getLeft();
			RBTransplant(node, node.getLeft());
		}
		else
		{
			y = node.getRight();
			while (y.getLeft() != nil)
				y = y.getLeft();
			yOrigColor = y.getColor();
			x = y.getRight();
			if(y.getParent() == node)
			{
				x.setParent(y);
			}
			else 
			{
				 RBTransplant(y, y.getRight());
				 y.setRight(node.getRight());
				 y.getRight().setParent(y);
			}
			
			RBTransplant(node, y);
			y.setLeft(node.getLeft());
			y.getLeft().setParent(y);
			y.setColor(node.getColor());
		}
		if(yOrigColor == 1)
		{
			RBDeleteFixup(x);
		}
	}

	private void RBDeleteFixup(Node x)
	{
		Node w;
		while(x != root && x.getColor()== 1)
		{
			if(x == x.getParent().getLeft())
			{
				w = x.getParent().getRight();
				if(w.getColor() == 0)
				{
					w.setColor(1);
					x.getParent().setColor(0);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().getColor() == 1 && w.getRight().getColor() == 1)
				{
					w.setColor(0);
					x = x.getParent();
				}
				else
				{
					if(w.getRight().getColor() == 1)
					{
						w.getLeft().setColor(1);
						w.setColor(0);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					w.getRight().setColor(1);
					leftRotate(x.getParent());
					x = root;
				}
			}
			else
			{
				w = x.getParent().getLeft();
				if(w.getColor() == 0)
				{
					w.setColor(1);
					x.getParent().setColor(0);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().getColor() == 1 && w.getLeft().getColor() == 1)
				{
					w.setColor(0);
					x = x.getParent();
				}
				else
				{
					if(w.getLeft().getColor() == 1)
					{
						w.getRight().setColor(1);
						w.setColor(0);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					w.getLeft().setColor(1);
					rightRotate(x.getParent());
					x = root;
				}

			}
				
			
		}
	}
	private void leftRotate(Node x) {
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if (y.getLeft() != nil)
			y.getLeft().setParent(x);

		y.setParent(x.getParent());

		if (x.getParent() == nil)
			root = y;
		else if (x == x.getParent().getLeft())
			x.getParent().setLeft(y);
		else
			x.getParent().setRight(y);

		y.setLeft(x);
		x.setParent(y);
		
		updateVals(x);
	}

	private void rightRotate(Node x) {
		Node y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != nil)
			y.getRight().setParent(x);
		y.setParent(x.getParent());
		if (x.getParent() == nil)
			root = y;
		else if (x == x.getParent().getRight())
			x.getParent().setRight(y);
		else
			x.getParent().setLeft(y);
		y.setRight(x);
		x.setParent(y);
		
		updateVals(x);
	}

	public void updateVals(Node node) {
		updateSingle(node);
		if (node.getParent() != nil) {
			updateVals(node.getParent());
		}
		return;
	}
	
	public void updateSingle(Node node) {
		node.getVal();
		node.getMaxVal();
		node.getEmax();
	}
	// Add more functions as you see fit.
}
