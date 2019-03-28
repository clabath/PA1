import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitTests {
	

		RBTree rbt = new RBTree();
		Node left = new Node(0,1, 1);
		Node right = new Node(3,1, 1);
		Node node = new Node(1,1, 1);
		
	@BeforeEach
	void init() {
		left.setLeft(rbt.getNILNode());
		left.setRight(rbt.getNILNode());
		left.setParent(node);
		right.setLeft(rbt.getNILNode());
		right.setRight(rbt.getNILNode());
		right.setParent(node);
		node.setLeft(left);
		node.setRight(right);
		node.setParent(rbt.getNILNode());
		}
		
	@Test
	void valTest() {
		System.out.println(left.getVal());
		assertEquals(1,left.getVal());
		assertEquals(1,right.getVal());
		assertEquals(3,node.getVal());
	}
	
	@Test
	void vals() {
		Node e1 = new Node(0,1,1);
		Node e2 = new Node(1,1,2);
		Node e3 = new Node(3,1,3);
		Node e4 = new Node(4,-1,1);
		Node e5 = new Node(6,-1,2);
		Node e6 = new Node(7,1,4);
		Node e7 = new Node(9,-1,3);
		Node e8 = new Node(11,-1,4);
		e4.setParent(rbt.getNILNode());
		e4.setLeft(e2);
		e4.setRight(e7);
		e2.setParent(e4);
		e2.setRight(e3);
		e2.setLeft(e1);
		e1.setParent(e2);
		e1.setLeft(rbt.getNILNode());
		e1.setRight(rbt.getNILNode());
		e3.setParent(e2);
		e3.setLeft(rbt.getNILNode());
		e3.setRight(rbt.getNILNode());
		e7.setParent(e4);
		e7.setRight(e8);
		e7.setLeft(e5);
		e5.setParent(e7);
		e5.setLeft(rbt.getNILNode());
		e5.setRight(e6);
		e6.setParent(e5);
		e6.setLeft(rbt.getNILNode());
		e6.setRight(rbt.getNILNode());
		e8.setParent(e7);
		e8.setLeft(rbt.getNILNode());
		e8.setRight(rbt.getNILNode());
		rbt.updateVals(e6);
		rbt.updateVals(e8);
		rbt.updateVals(e5);
		rbt.updateVals(e3);
		rbt.updateVals(e1);
		rbt.updateVals(e7);
		rbt.updateVals(e2);
		rbt.updateVals(e4);
//		System.out.println("vals: " + e1.getVal() + " " + e2.getVal() + " " + e3.getVal() + " " + e4.getVal() + " " + e5.getVal() + " " + e6.getVal() + " " + e7.getVal() + " " + e8.getVal()); 
//		System.out.println("max vals: " + e1.getMaxVal() + " " + e2.getMaxVal() + " " + e3.getMaxVal() + " " + e4.getMaxVal() + " " + e5.getMaxVal() + " " + e6.getMaxVal() + " " + e7.getMaxVal() + " " + e8.getMaxVal()); 
//		System.out.println("emax: " + e1.getEmax().getValue() + " " + e2.getEmax().getValue() + " " + e3.getEmax().getValue() + " " + e4.getEmax().getValue() + " " + e5.getEmax().getValue() + " " + e6.getEmax().getValue() + " " + e7.getEmax().getValue() + " " + e8.getEmax().getValue()); 

	}
	
	@Test
	void test2() {
		int points[][] = {{1, 5}, {4, 13}, {9, 11}, {16, 20}};
		Intervals intv = new Intervals();
		
		for(int i=0; i<points.length; i++) {
			System.out.println("Inserting: "+ Arrays.toString(points[i]));
			intv.intervalInsert(points[i][0], points[i][1]);
		}
		System.out.println("POM is: "+ intv.findPOM()); //Should return 3.
		System.out.println("Height: "  + intv.rbt.getHeight());
		System.out.println("Sorted by Key, inorder traversal");
		intv.rbt.inorderPrint();
	}

}
