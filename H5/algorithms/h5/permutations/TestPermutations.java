package algorithms.h5.permutations;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPermutations {

	private static ArrayList<String> strings; 	
	private static Tree<String> tree;
	private static ArrayList<String> p1, p2, p3, p4, p5, p6;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		strings = new ArrayList<String>();
		strings.add("A");
		strings.add("B");
		strings.add("C");
		
		tree = new Tree<String>(strings);
		
		p1 = new ArrayList<String>();
		p1.add("A");
		p1.add("B");
		p1.add("C");
		
		p2= new ArrayList<String>();
		p2.add("A");
		p2.add("C");
		p2.add("B");
		
		p3= new ArrayList<String>();
		p3.add("B");
		p3.add("A");
		p3.add("C");
		
		p4= new ArrayList<String>();
		p4.add("B");
		p4.add("C");
		p4.add("A");
		
		p5= new ArrayList<String>();
		p5.add("C");
		p5.add("B");
		p5.add("A");
		
		p6= new ArrayList<String>();
		p6.add("C");
		p6.add("A");
		p6.add("B");
	}
		
	@Before
	public void setUp() {
		
	}

	@Test
	public void testGeneratingTree() {
		assertEquals(15, tree.size());
	}
	
	@Test
	public void testPermutations(){
		ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();
		permutations.add(p5);
		permutations.add(p4);
		permutations.add(p6);
		permutations.add(p2);
		permutations.add(p3);
		permutations.add(p1);
		
		assertEquals(permutations, tree.permutations());
	}
	
	@Test
	public void testVariations(){
		ArrayList<String> c1= new ArrayList<String>();
		c1.add("A");
		c1.add("B");
				
		ArrayList<String> c2= new ArrayList<String>();
		c2.add("A");
		c2.add("C");
				
		ArrayList<String> c3= new ArrayList<String>();
		c3.add("B");
		c3.add("A");
				
		ArrayList<String> c4= new ArrayList<String>();
		c4.add("B");
		c4.add("C");
				
		ArrayList<String> c5= new ArrayList<String>();
		c5.add("C");
		c5.add("B");
				
		ArrayList<String> c6= new ArrayList<String>();
		c6.add("C");
		c6.add("A");
				
		ArrayList<ArrayList<String>> variations = new ArrayList<ArrayList<String>>();
		variations.add(c3);
		variations.add(c6);
		variations.add(c1);
		variations.add(c5);
		variations.add(c2);
		variations.add(c4);
		
		assertEquals(variations, tree.variations(2));
	}	
}
