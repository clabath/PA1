import java.util.Arrays;

/**
 * Team members:
 * @author Kyle Bowden
 * @author Kyle Trom
 * @author Cassandra Labath
 * 
 * A wrapper class for RBTree
 */
public class Intervals {
	//private int ID = 0; If deletion is done, this could be used to keep track of edpoints
	//                    for the same interval.
	int nextID = 1;
	/**
	 * Constructor with no parameters.
	 */
	public Intervals() {
		//TODO
	}
	
	/**
	 * 
	 * Adds the interval with left endpoint a and right endpoint b 
	 * to the collection of intervals. Each newly inserted interval 
	 * must be assigned an ID. The IDs should be consecutive; that is, 
	 * the ID of the interval inserted on the ith call of this method should be i.
	 * For example if intervalInsert is called successively to insert intervals 
	 * [5,7],[4,9],[1,8], then the IDs of these intervals should be 1,2,3, respectively.These IDs are permanent
	 *  for the respective intervals. Keep track of the IDs, as multiple intervals that have the same endpoints
	 *   on both sides can be added. intervalInsertshould run in O(logn)time
	 * @param a
	 * @param b
	 */
	//as of right now the ID is equal to the index + 1
	void intervalInsert(int a, int b) {
		getRBTree().insertNode(new Node(a, 1, nextID));
		getRBTree().insertNode(new Node(b, -1, nextID));
		nextID++;
	}
	
	/**
	 * To delete an interval from delete.
	 * 
	 * 
	 * Deletes the interval whose ID (gener-ated byintervalInsert) isintervalID. Returnstrueif 
	 * deletion was successful. Thismethod should run inO(logn)time.Note.TheintervalDeletemethod 
	 * isoptional; that is, you are not requiredto implement it. However, your codemustprovide 
	 * anintervalDeletemethodeven if you choose not to implement interval deletion. If you do not
	 *  implementdeletion, theintervalDeletemethod should consist of just one line that returnsfalse.
	 * @param intervalID
	 * @return
	 */
	boolean intervalDelete(int intervalID) {
		return false;
	}
	
	/**
	 * Finds the endpoint that has maximum overlap and returns its value. Thismethod should run in constant time.
	 * @return
	 */
	int findPOM() {
		//TODO: Modify it accordingly.
		return 0;
	}
	
	/**
	 * Returns the red-black tree used, which is an object of typeRBTree.
	 * @return
	 */
	RBTree getRBTree() {
		//TODO: Modify it accordingly.
		return null;
	}
	
	
	//Add more functions as  you see fit.
	
	
	/**
	 * This is a suggested way on how to add intervals and call POM()
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
//		int points[][] = {{0, 4}, {1, 6}, {3, 9}, {7, 11}};
//		Intervals intv = new Intervals();
//		
//		for(int i=0; i<points.length; i++) {
//			//System.out.println("Inserting: "+ Arrays.toString(points[i]));
//			intv.intervalInsert(points[i][0], points[i][1]);
//		}
//		System.out.println("POM is: "+ intv.findPOM()); //Should return 3.
		RBTree rbt = new RBTree();
		Node left = new Node(0,1, 1);
		Node right = new Node(3,1, 1);
		Node node = new Node(1,1, 1);
		left.setLeft(rbt.getNILNode());
		left.setRight(rbt.getNILNode());
		left.setParent(node);
		right.setLeft(rbt.getNILNode());
		right.setRight(rbt.getNILNode());
		right.setParent(node);
		node.setLeft(left);
		node.setRight(right);
		node.setParent(rbt.getNILNode());
		left.getVal();
		left.getMaxVal();
		left.getEmax();
		right.getVal();
		right.getMaxVal();
		right.getEmax();left.getVal();
		node.getVal();
		node.getMaxVal();
		node.getEmax();
		System.out.println("left val is:" + left.val + "   left maxval is:" + left.maxval + "   left emax is at key:" + left.emax.getValue());
		System.out.println("right val is:" + right.val + "   right maxval is:" + right.maxval + "   right emax is at key:" + right.emax.getValue());
		System.out.println("node val is:" + node.val + "   node maxval is:" + node.maxval + "   node emax is at key:" + node.emax.getValue());
	}
}
