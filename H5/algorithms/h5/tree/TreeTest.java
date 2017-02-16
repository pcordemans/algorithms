package algorithms.h5.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TreeTest {

	@Test
	public void testEmptyTreeConstruction() {
		ITree<String> t = new Tree<String>();
		assertEquals(0, t.size());
		assertEquals(true, t.isEmpty());
	}

	@Test
	public void testTreeConstruction() {
		ITree<String> t = new Tree<String>("root");
		assertEquals(1, t.size());
		assertEquals(false, t.isEmpty());
	}

	@Test
	public void testReturnPositions() {
		ITree<String> t = new Tree<String>("root");
		for (TreePosition<String> pos : t.positions()) {
			assertEquals("root", pos.getElement());
			assertEquals(true, t.isRoot(pos));
			assertEquals(false, t.isInternal(pos));
			assertEquals(true, t.isExternal(pos));
		}
		assertEquals(1, t.positions().size());
	}

	@Test
	public void testReplaceElement() {
		ITree<String> t = new Tree<String>("root");
		TreePosition<String> root = t.root();
		assertEquals("root", root.getElement());
		assertEquals("root", t.replace(root, "newRoot"));
		assertEquals("newRoot", root.getElement());

	}

	@Test
	public void testMergingTrees() {
		ITree<String> s1 = new Tree<String>("sibling1");
		ITree<String> s2 = new Tree<String>("sibling2");
		ITree<String> r = new Tree<String>("root");

		TreePosition<String> rootpos = r.root();

		r.attach(rootpos, s1);

		assertEquals(2, r.size());
		assertEquals(true, r.isInternal(rootpos));
		assertEquals(1, rootpos.numberOfChildren());

		for (TreePosition<String> pos : rootpos.getChildren()) {
			assertEquals("sibling1", pos.getElement());
		}

		r.attach(rootpos, s2);
		assertEquals(3, r.size());
		assertEquals(2, rootpos.numberOfChildren());

		ArrayList<TreePosition<String>> positionList = r.positions();
		assertEquals(3, positionList.size());
		assertEquals("root", positionList.get(0).getElement());
		assertEquals("sibling1", positionList.get(1).getElement());
		assertEquals("sibling2", positionList.get(2).getElement());

	}
}
