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
 * Tester for myStack
 * @version 1.0
 * @author PeiranLi
 * @since 4-19-2016
 */
public class MyStackTester {
	private MyStack<Integer> empty = new MyStack<Integer>();
	private MyStack<Integer> one = new MyStack<Integer>();
	private MyStack<Integer> several = new MyStack<Integer>();
	private final int DEFAULT_SIZE = 5;

	/**initialize all the stacks */
	@Before
	public void setUp() {
		one.addElement(1);
		for(int i = 0; i < DEFAULT_SIZE; i++)
		{
			several.addElement(i+1);
		}
	}

	/**Test the push method of stack */
	@Test
	public void testPush() {
		assertEquals("empty size",0,empty.size());
		assertEquals("one's size",1,one.size());
		assertEquals("several's size",DEFAULT_SIZE,several.size());

		assertEquals("top element of several",new Integer(5),several.peek());
		several.addElement(6);
		assertEquals("top element of several",new Integer(6),several.peek());
		several.addElement(7);
		assertEquals("top element of several",new Integer(7),several.peek());

	}

	/**Test pop method of stack */
	@Test
	public void testPop() {
		try{
			empty.removeElement();
			fail("should generate exception");
		}catch(EmptyStackException ex){

		}

		one.removeElement();
		try{
			one.removeElement();
			fail("should generate exception");
		}catch(EmptyStackException ex){

		}

		one.addElement(1);
		assertEquals("top element of several",new Integer(1),one.peek());

		one.addElement(2);
		assertEquals("top element of several",new Integer(2),one.peek());
		
		assertEquals("remove",several.removeElement(),new Integer(5));
		assertEquals("first element",new Integer(4),several.peek());
		
		assertEquals("remove",several.removeElement(),new Integer(4));
		assertEquals("first element",new Integer(3),several.peek());

		assertEquals("remove",several.removeElement(),new Integer(3));
		assertEquals("first element",new Integer(2),several.peek());

		assertEquals("remove",several.removeElement(),new Integer(2));
		assertEquals("first element",new Integer(1),several.peek());
	}

}
