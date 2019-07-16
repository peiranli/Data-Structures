// *
// NAME: <Peiran Li>

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

/**
 * swapNodes tester 
 * @version 1.0
 * @author Peiran Li
 * @since 4-11-2016
 */


package hw2;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

public class ECreditTester_2 {
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
		list2.add(0, new Integer(1));
		list2.add(1, new Integer(6));
		list2.add(2, new Integer(8));
	}
	/** test the exception and swapNodes on list and list2*/
	@Test
	public void test() {
		try{
			list.swapNodes(list2, 0, 4);
			fail("should throw IndexOutOfBoundsException");
		}catch(IndexOutOfBoundsException ex){
			
		}
		
		list.swapNodes(list2,1,2);
		assertEquals("second in list",new Integer(6),list.get(1));
		assertEquals("third in list",new Integer(8),list.get(2));
	}

}
