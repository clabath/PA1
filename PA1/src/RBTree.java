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
		height = findHeight(root) - 1;
		return height;
	}

	// Naive method to find height of the tree based on recursion
	private int findHeight(Node node) {
		if (node == null)
			return 0;
		else {
			int l = findHeight(node.getLeft());
			int r = findHeight(node.getRight());
			// Find which height is greatest and return
			return Math.max(l, r) + 1;
		}
	}

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
		while (x != nil) {
			y = x;
			if (node.getKey() < x.getKey()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
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

	public boolean removeNode(Node node) { // removes node, returns true if successful; //Can probably remove this
		// Todo:
		size--;
		return true;
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
	}

	public void updateVals(Node node) {
		node.getVal();
		node.getMaxVal();
		node.getEmax();
		if (node.getParent() != nil) {
			updateVals(node.getParent());
		}
		return;
	}
	// Add more functions as you see fit.
}
