package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListTest {

	List<String> list;
	
	@Before
	public void setUp() throws Exception {
		list = new List<String>("1");
	}

	@Test
	public void testConstructor() {
		assertEquals(1, list.size());
		assertEquals(new Node<String>("1"), list.head());
	}
	
	@Test
	public void testPrependWithNode(){
		Node<String> node = new Node<String>("2");
		list.prepend(node);
		
		assertEquals(2, list.size());
		assertEquals(node, list.head());
	}
	
	@Test
	public void testPrependAnotherList(){
		List<String> otherList = new List<String>("2");
		Node<String> element = new Node<String>("3");
		
		otherList.prepend(element);
		list.prepend(otherList);
		
		assertEquals(otherList.head() ,list.head());
		assertEquals(3, list.size());
	}

}
