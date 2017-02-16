package algorithms.h1.linkedlist;

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
		assertEquals("1", list.head());
	}
	
	@Test
	public void testPrependElement(){
		String element = "2";
		list.prepend(element);
		
		assertEquals(2, list.size());
		assertEquals(element, list.head());
	}
	
	@Test
	public void testPrependAnotherList(){
		List<String> otherList = new List<String>("2");
		String element = "3";
		
		otherList.prepend(element);
		list.prepend(otherList);
		
		assertEquals(otherList.head() ,list.head());
		assertEquals(3, list.size());
	}

}
