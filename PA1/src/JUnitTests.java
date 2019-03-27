import static org.junit.Assert.assertEquals;

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

}
