// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw4;

import static org.junit.Assert.*; 

import java.util.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Tester for myQueue class
 * @since 4-19-2016
 * @author PeiranLi
 * @version 1.0
 */
public class MyQueueTester {
	private MyQueue<Integer> empty = new MyQueue<Integer>();
	private MyQueue<Integer> one = new MyQueue<Integer>();
	private MyQueue<Integer> full = new MyQueue<Integer>();
	private final int DEFAULT_SIZE = 5;

	/** initialize all the queues*/
	@Before
	public void setUp() {
		one.addElement(1);
		for(int i = 0; i < DEFAULT_SIZE; i++)
		{
			full.addElement(i+1);
		}
	}

	/**Test the enqueue method */
	@Test
	public void testEnqueue() {
		assertEquals("empty size",0,empty.size());
		assertEquals("one size",1,one.size());
		assertEquals("full size",DEFAULT_SIZE,full.size());

		assertEquals("first element",new Integer(1),one.peek());
		assertEquals("first element",new Integer(1),full.peek());

		full.addElement(6);
		assertEquals("first element",new Integer(1),full.peek());
		full.addElement(7);
		assertEquals("first element",new Integer(1),full.peek());
		full.addElement(8);
		assertEquals("first element",new Integer(1),full.peek());

		assertEquals("remove",full.removeElement(),new Integer(1));
		assertEquals("first element",new Integer(2),full.peek());
		
		assertEquals("remove",full.removeElement(),new Integer(2));
		assertEquals("first element",new Integer(3),full.peek());

		assertEquals("remove",full.removeElement(),new Integer(3));
		assertEquals("first element",new Integer(4),full.peek());

		assertEquals("remove",full.removeElement(),new Integer(4));
		assertEquals("first element",new Integer(5),full.peek());


	}

	/**Test dequeue method */
	@Test
	public void testDequeue() {
		try{
			empty.removeElement();
			fail("should throw exception");
		}catch(NoSuchElementException ex){

		}

		assertEquals("one element",new Integer(1),one.removeElement());
		try{
			one.removeElement();
			fail("should throw exception");
		}catch(NoSuchElementException ex){

		}

		for(int i = 0; i < DEFAULT_SIZE; i++)
		{
			full.removeElement();
		}

		assertEquals("size after pop",0,full.size());
		assertEquals("capacity after pop all elements",DEFAULT_SIZE,full.capacity());
		
		
	}


}
