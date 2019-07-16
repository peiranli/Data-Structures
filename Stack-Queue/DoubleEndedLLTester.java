// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw4;

import static org.junit.Assert.*; 

import org.junit.Test;
import org.junit.Before;

/**
 * tester for doubleended linkedlist
 * @version 1.0
 * @author PeiranLi
 * @since 4-19-2016
 */
public class DoubleEndedLLTester {
	//three kinds of lists
	private DoubleEndedLL<Integer> empty = new DoubleEndedLL<Integer>();
	private DoubleEndedLL<Integer> one = new DoubleEndedLL<Integer>();
	private DoubleEndedLL<Integer> several = new DoubleEndedLL<Integer>();
	private final int DEFAULT_SIZE = 5;

	/**set up for test */
	@Before
	public void setUp() {
		one.add(0, new Integer(1));
		for(int i = 0; i < DEFAULT_SIZE;i++){
			several.add(i, new Integer(i+1));
		}
	}

	/**Test the add method */
	@Test
	public void testAdd() {
		assertEquals("first element in several",new Integer(1),several.get(0));
		assertEquals("first element in several",new Integer(2),several.get(1));
		assertEquals("first element in several",new Integer(3),several.get(2));
		assertEquals("first element in several",new Integer(4),several.get(3));
		assertEquals("first element in several",new Integer(5),several.get(4));

		empty.add(0, new Integer(1));
		assertEquals("first element in empty",new Integer(1),empty.get(0));
		Integer data = empty.removeFirst();
		empty.addFirst(1);
		assertEquals("first element in empty",new Integer(1),empty.get(0));
		Integer data2 = empty.removeLast();
		assertEquals("compare two data",data,data2);
	}

	/**Test the set method */
	@Test
	public void testSet() {
		try{
			empty.set(0,1);
			fail("should throw exception");
		}catch(IndexOutOfBoundsException ex){

		}

		try{
			one.set(0, null);
			fail("should throw exception");
		}catch(NullPointerException ex){

		}
	}

	/**Test remove method */
	@Test 
	public void testRemove() {
		try{
			empty.remove(0);
			fail("should generate exception");
		}catch(IndexOutOfBoundsException ex){

		}

		try{
			one.remove(1);
			fail("should generate exception");
		}catch(IndexOutOfBoundsException ex){

		}	

		several.remove(3);
		assertEquals("size after remove",4,several.size());

		assertEquals("element after remove",new Integer(1),several.get(0));
		assertEquals("element after remove",new Integer(2),several.get(1));
		assertEquals("element after remove",new Integer(3),several.get(2));
		assertEquals("element after remove",new Integer(5),several.get(3));

		try{
			several.get(4);
			fail("should throw exception");
		}catch(IndexOutOfBoundsException ex){

		}
		several.add(3, new Integer(6));
		assertEquals("element after remove",new Integer(6),several.get(3));
		assertEquals("element after remove",new Integer(5),several.get(4));
		assertEquals("element after remove",new Integer(3),several.get(2));


	}

	/**Test clear method */
	@Test 
	public void testClear() {
		several.clear();
		assertEquals("size after clear",0,several.size());
	}

	/**Test addFirst method */
	@Test 
	public void testAddFirst() {
		empty.addFirst(1);
		assertEquals("first element",new Integer(1),empty.get(0));
		several.addFirst(6);
		assertEquals("first element",new Integer(6),several.get(0));
		assertEquals("second element",new Integer(1),several.get(1));

		one.addFirst(2);
		assertEquals("first element",new Integer(2),one.get(0));
	}

	/**Test addLast method  */
	@Test
	public void testAddLast() {
		empty.addLast(1);
		assertEquals("last element", new Integer(1),empty.get(0));

		one.addLast(2);
		assertEquals("first element", new Integer(1),one.get(0));
		assertEquals("last element", new Integer(2),one.get(1));

		several.addLast(6);
		assertEquals("last element", new Integer(6),several.get(5));
	}

	/**Test removeFirst method */
	@Test 
	public void testRemoveFirst() {
		try{
			empty.removeFirst();
			fail("should throw exception");
		}catch(NullPointerException ex){

		}

		one.removeFirst();
		assertEquals("size when removeFirst",0,one.size());

		several.removeFirst();
		assertEquals("size when removeFirst",4,several.size());
		assertEquals("first element",new Integer(2),several.get(0));
		assertEquals("first element",new Integer(3),several.get(1));
		assertEquals("first element",new Integer(4),several.get(2));
		assertEquals("first element",new Integer(5),several.get(3));

		several.removeFirst();
		assertEquals("first element",new Integer(3),several.get(0));

		several.removeFirst();
		assertEquals("first element",new Integer(4),several.get(0));

		several.removeFirst();
		assertEquals("first element",new Integer(5),several.get(0));

	}

	/**Test removeLast method */
	@Test
	public void testRemoveLast() {
		try{
			empty.removeLast();
			fail("should throw exception");
		}catch(NullPointerException ex){

		}

		one.removeLast();
		assertEquals("size when removeLast",0,one.size());

		several.removeLast();
		assertEquals("size when removeLast",4,several.size());
		assertEquals("last element", new Integer(4),several.get(3));
		several.removeLast();
		assertEquals("size when removeLast",3,several.size());
		assertEquals("last element", new Integer(3),several.get(2));

		several.removeLast();
		assertEquals("size when removeLast",2,several.size());
		assertEquals("last element", new Integer(2),several.get(1));

		several.removeLast();
		assertEquals("size when removeLast",1,several.size());
		assertEquals("last element", new Integer(1),several.get(0));

		several.removeLast();
		assertEquals("size when removeLast",0,several.size());
	}

}
