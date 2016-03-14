package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	DynamicArray<String> list; 
	
	@Before
	public void setUp() throws Exception {
		list = new DynamicArray<String>();
	}

	@Test
	public void testSize() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	@Test
	public void testSet() {
		list.set(0, "1");
		assertEquals("1", list.get(0));
		assertEquals("1", list.set(0, "2"));
		assertEquals("2", list.get(0));
	}

	
	@Test
	public void testRemove() {
		list.set(0, "1");
		assertEquals("1", list.remove(0));
		assertEquals(null, list.get(0));
	}
	
	@Test
	public void testAddMoreElementsThanCapacity() {
		list.set(2, "1");
		assertEquals("1", list.get(2));
	}

}
