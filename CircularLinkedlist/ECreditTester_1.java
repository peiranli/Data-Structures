// *
// NAME: <Peiran Li>

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

/**
 * reverseAndConcat method tester 
 * @version 1.0
 * @author Peiran Li
 * @since 4-11-2016
 */


package hw2;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

public class ECreditTester_1 {
	private CLinkedList<Integer> list;
	private CLinkedList<Integer> list2;
	/** initialize lists*/
	@Before
	public void setUp()
	{
		list = new CLinkedList<Integer>();
		list2 = new CLinkedList<Integer>();
		list.add(0, new Integer(3));
		list.add(1, new Integer(5));
		list.add(2, new Integer(7));
		list2.add(0, new Integer(3));
		list2.add(1, new Integer(5));
		list2.add(2, new Integer(7));
	}

	/** Test the reverse and concatenate method*/
	@Test
	public void test() {
		list.reverseAndConcat(list);
		assertEquals("first", new Integer(3),list.get(0));
		assertEquals("second", new Integer(5),list.get(1));
		assertEquals("third", new Integer(7),list.get(2));
		assertEquals("size", 6,list.size());
		assertEquals("fourth", new Integer(7),list.get(3));
		assertEquals("fifth", new Integer(5),list.get(4));
		assertEquals("sixth", new Integer(3),list.get(5));
		
		list.reverseAndConcat(list2);
		assertEquals("first", new Integer(3),list2.get(0));
		assertEquals("second", new Integer(5),list2.get(1));
		assertEquals("third", new Integer(7),list2.get(2));
		assertEquals("size", 6,list2.size());
		assertEquals("fourth", new Integer(7),list2.get(3));
		assertEquals("fifth", new Integer(5),list2.get(4));
		assertEquals("sixth", new Integer(3),list2.get(5));
		
		
	}

}
