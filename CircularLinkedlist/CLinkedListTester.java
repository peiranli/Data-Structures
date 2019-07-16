// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

/**
 * Circular Doubly-Linked list tester 
 * @version 1.0
 * @author Peiran Li
 * @since 4-11-2016
 */




package hw2;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;


/**
 *  Title: class CLinkedListTester
 *  Description: JUnit test class for LinkedList class
 *
 */

public class CLinkedListTester
{
	private CLinkedList<Integer> empty ;
	private CLinkedList<Integer> one ;
	private CLinkedList<Integer> several ;
	private CLinkedList<String>  slist ;
	static final int DIM = 5;

	/**
	 * Standard Test Fixture. An empty list, a list with one entry (0) and 
	 * a list with several entries (0,1,2)
	 */ 
	@Before
	public void setUp()
	{
		empty = new CLinkedList<Integer>();

		one = new CLinkedList<Integer>();
		one.add(0,new Integer(0));

		several = new CLinkedList<Integer>() ;
		// List: 1,2,3,...,Dim
		for (int i = DIM; i > 0; i--)
			several.add(0,new Integer(i));

		// List: "First","Last"
		slist = new CLinkedList<String>();
		slist.add(0,"First");
		slist.add(1,"Last");
	}
	/** Test if heads of the lists are correct */
	@Test
	public void testGetHead()
	{
		assertEquals("Check 0",new Integer(0),one.get(0)) ;
		assertEquals("Check 0",new Integer(1),several.get(0)) ;
	}

	/** Test if size of lists are correct */
	@Test
	public void testListSize()
	{
		assertEquals("Check Empty Size",0,empty.size()) ;
		assertEquals("Check One Size",1,one.size()) ;
		assertEquals("Check Several Size",DIM,several.size()) ;
	}

	/** Test setting a specific entry */
	@Test
	public void testSet()
	{
		slist.set(1,"Final");
		assertEquals("Setting specific value", "Final",slist.get(1));
	}

	/** Test isEmpty */
	@Test
	public void testEmpty()
	{
		assertTrue("empty is empty",empty.isEmpty()) ;
		assertTrue("one is not empty",!one.isEmpty()) ;
		assertTrue("several is not empty",!several.isEmpty()) ;
	}

	/** Test out of bounds exception on get */
	@Test
	public void testGetException()
	{
		try 
		{
			empty.get(0);
			// This is how you can test when an exception is supposed 
			// to be thrown
			fail("Should have generated an exception");  
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}
	}

	/** Test if get method is correct*/
	@Test
	public void testGet()
	{
		assertEquals("first",new Integer(1),several.get(0));
		assertEquals("second",new Integer(2),several.get(1));
		assertEquals("third",new Integer(3),several.get(2));
	}


	/** Test iterator on empty list and several list */
	@Test
	public void testIterator()
	{
		int counter = 0 ;
		ListIterator<Integer> iter;
		for (iter = empty.listIterator() ; iter.hasNext(); )
		{
			fail("Iterating empty list and found element") ;
		}
		counter = 0 ;
		for (iter = several.listIterator() ; counter<several.size(); iter.next())
			counter++;
		assertEquals("Iterator several count", counter, DIM);
	}

	/** Test add method on empty*/
	@Test 
	public void testAdd()
	{
		try
		{
			empty.add(1, new Integer(0));
			fail("should throw IndexOutOfBoundsException");
		}catch(IndexOutOfBoundsException ex){
			System.out.println("empty.add(1, new Integer(0) " +
					"throw IndexOutOfBoundsException");
		}

		try
		{
			empty.add(0,new Integer(0));
		}catch(IndexOutOfBoundsException ex){
			System.out.println("empty.add(0,new Integer(0)" +
					"throw IndexOutOfBoundsException");
		}

		try
		{
			empty.add(0,null);
			fail("should throw NullPointerException");
		}catch(NullPointerException ex){
			System.out.println("empty.add(0,null)" +
					"throw NullPointerException");
		}

		try
		{
			empty.add(0,new Integer(1));
			empty.add(1,new Integer(3));
			empty.add(2,new Integer(2));
		}catch(NullPointerException ex){
			System.out.println("exist" +
					"NullPointerException");
		}


		assertEquals("First element",new Integer(1),
				empty.get(0));
		assertEquals("Second element",new Integer(3),
				empty.get(1));
		assertEquals("Third element",new Integer(2),
				empty.get(2));
	}

	/** Test remove method on several */
	@Test
	public void testRemove()
	{
		try
		{
			several.remove(5);
			fail("should throw IndexOutOfBoundsException");
		}catch(IndexOutOfBoundsException ex){
			System.out.println("several.remove(5) IndexOutOfBoundsException");
		}

		try
		{
			several.remove(4);
		}catch(IndexOutOfBoundsException ex){
			System.out.println("several.remove(4) IndexOutOfBoundsException");
		}

		try{
			several.remove(0);
		}catch(IndexOutOfBoundsException ex){
			System.out.println("several.remove(0) IndexOutOfBoundsException");
		}

		assertEquals("LinkedList first element",
				new Integer(3),several.get(1));

		try{
			one.remove(0);
		}catch(IndexOutOfBoundsException ex){
			System.out.println("one.remove(0) IndexOutOfBoundsException");
		}

		assertEquals("if empty",true,one.isEmpty());

		try{
			one.add(0,new Integer(1));

		}catch(IndexOutOfBoundsException ex){
			System.out.println("one.add(0,new Integer(1) IndexOutOfBoundsException");
		}
		assertEquals("first element",new Integer(1),one.get(0));

		try{
			empty.remove(0);
			fail("should throw IndexOutOfBoundsException");
		}catch(IndexOutOfBoundsException ex){

		}
	}

	/** Test clear method on several and empty */
	@Test
	public void testClear()
	{
		try
		{
			several.clear();
		}catch(IndexOutOfBoundsException ex){
			System.out.println("several.clear() IndexOutOfBoundsException");
		}
		assertEquals("size",0,several.size());

		try{
			empty.clear();
		}catch(IndexOutOfBoundsException ex){
			System.out.println("several.clear() IndexOutOfBoundsException");
		}

	}

	/**Test add method in iterator */
	@Test
	public void testIterAdd()
	{
		ListIterator<Integer> iter = empty.listIterator();

		iter.add(new Integer(1));
		iter.add(new Integer(2));
		iter.add(new Integer(3));
		iter.add(new Integer(4));
		iter.add(new Integer(5));
		assertEquals("first element",new Integer(1),empty.get(0));
		assertEquals("second element",new Integer(2),empty.get(1));
		assertEquals("third element",new Integer(3),empty.get(2));
		assertEquals("fourth element",new Integer(4),empty.get(3));
		assertEquals("fifth element",new Integer(5),empty.get(4));
		iter.add(new Integer(6));
		try{
			iter.remove();
			fail("should throw IllegalStateException");
		}catch(IllegalStateException ex){
			
		}
		assertEquals("fifth",new Integer(5),empty.get(4));
		
		ListIterator<Integer> iter2 = several.listIterator();
		iter2.next();
		iter2.next();
		iter2.add(new Integer(7));
		assertEquals("test next return value",new Integer(3),iter2.next());


	}

	/**Test hasNext in iterator */
	@Test
	public void testIterHasNext()
	{
		ListIterator<Integer> iter = empty.listIterator();
		try{
			iter.hasNext();
		}catch(NullPointerException ex){
			System.out.println("iter.hasNext() " +
					"NullPointerException");
		}

	}
	/**Test nextin iterator */
	@Test
	public void testIterNext()
	{
		ListIterator<Integer> iter = empty.listIterator();
		try{
			iter.next();
			fail("should throw NoSuchElementException");
		}catch(NoSuchElementException ex){
			System.out.println("iter.next() " +
					"throw NoSuchElementException");
		}

		ListIterator<Integer> iter2 = several.listIterator();
		Integer data = new Integer(iter2.next());
		try{
			iter2.hasNext();
		}catch(NullPointerException ex){
			System.out.println("iter2.hasNext() " +
					"NullPointerException");
		}
		assertEquals("next",data,several.get(0));

		Integer data2 = iter2.next();
		assertEquals("next",data2,several.get(1));
		Integer data3 = iter2.next();
		assertEquals("next",data3,several.get(2));
		Integer data4 = iter2.next();
		assertEquals("next",data4,several.get(3));	  
		Integer data5 = iter2.next();
		assertEquals("next",data5,several.get(4));
		Integer data6 = iter2.next();
		assertEquals("next",data6,several.get(0));
	}

	/**Test hasPrevious in iterator */
	@Test
	public void testIterHasPrev()
	{
		ListIterator<Integer> iter = empty.listIterator();
		try{
			iter.hasPrevious();
		}catch(NullPointerException ex){
			System.out.println("iter.hasPrevious() " +
					"NullPointerException");
		}


	}
	/**Test previous in iterator */
	@Test
	public void testIterPrev()
	{
		ListIterator<Integer> iter = empty.listIterator();
		try{
			iter.previous();
			fail("should throw NoSuchElementException");
		}catch(NoSuchElementException ex){
			System.out.println("iter.previous() " +
					"throw NoSuchElementException");
		}
		ListIterator<Integer> iter2 = several.listIterator();
		Integer data = iter2.next();
		try{
			iter2.hasPrevious();
		}catch(NullPointerException ex){
			System.out.println("iter2.hasPrevious() " +
					"NullPointerException");
		}
		assertEquals("previous",data,several.get(0));
		Integer data2 = iter2.previous();
		assertEquals("previous",data2,several.get(0));
		Integer data3 = iter2.previous();
		assertEquals("previous",data3,several.get(4));
		Integer data4 = iter2.previous();
		assertEquals("previous",data4,several.get(3));
		Integer data5 = iter2.previous();
		assertEquals("previous",data5,several.get(2));
		Integer data6 = iter2.previous();
		assertEquals("previous",data6,several.get(1));
		Integer data7 = iter2.previous();
		assertEquals("previous",data7,several.get(0));
		Integer data8 = iter2.previous();
		assertEquals("previous",data8,several.get(4));


	}

	/** Test remove method in iterator*/
	@Test
	public void testIterRemove()
	{
		ListIterator<Integer> iter = several.listIterator();
		try{
			iter.remove();
			fail("should throw IllegalStateException");
		}catch(IllegalStateException ex){
			System.out.println("iter.remove() " +
					"IllegalStateException");
		}
		Integer data = new Integer(iter.next());
		Integer store = new Integer(several.get(0));
		try{
			iter.remove();
		}catch(IllegalStateException ex){
			System.out.println("iter.remove() " +
					"IllegalStateException");
		}
		assertEquals("data",data,store);
		iter.next();
		Integer data2 = new Integer(iter.previous());
		store = new Integer(several.get(0));
		try{
			iter.remove();
		}catch(IllegalStateException ex){
			System.out.println("iter.remove() " +
					"IllegalStateException");
		}

		assertEquals("data2",data2,store);
	}

	/** Test nextIndex method in iterator*/
	@Test
	public void testNextIndex()
	{
		ListIterator<Integer> iter = several.listIterator();
		int index1 = iter.nextIndex();
		iter.next();
		int index2 = iter.nextIndex();
		iter.next();
		int index3 = iter.nextIndex();
		iter.next();
		int index4 = iter.nextIndex();
		iter.next();
		int index5 = iter.nextIndex();
		iter.next();
		int index6 = iter.nextIndex();
		assertEquals("index1",0,index1);
		assertEquals("index2",1,index2);
		assertEquals("index3",2,index3);
		assertEquals("index4",3,index4);
		assertEquals("index5",4,index5);
		assertEquals("index6",0,index6);

		ListIterator<Integer> iter2 = empty.listIterator();
		assertEquals("index",-1,iter2.nextIndex());

		ListIterator<Integer> iter3 = one.listIterator();
		assertEquals("index",0,iter3.nextIndex());

	}
	/** Test prevIndex method in iterator*/
	@Test
	public void testPrevIndex()
	{
		ListIterator<Integer> iter = several.listIterator();
		int index1 = iter.previousIndex();
		iter.next();
		iter.next();
		iter.next();
		int index2 = iter.previousIndex();
		assertEquals("index1",4,index1);
		assertEquals("index1",2,index2);


	}

	/** Test set method in iterator*/
	@Test
	public void testIterSet()
	{
		ListIterator<String> iter = slist.listIterator();
		try{
			iter.set("Third");
			fail("should throw IllegalStateException");
		}catch(IllegalStateException ex){
			System.out.println("iter.set() IllegalStateException");
		}

	}
	/** Test the previous index when empty*/ 
	@Test
	public void testIterPreviousIndexWhenEmpty()
	{
		ListIterator<Integer> iter = empty.listIterator();
		assertEquals("previous index",-1,iter.previousIndex());
	}

	/** Test the next index when empty*/
	@Test
	public void testIterNextIndexWhenEmpty()
	{
		ListIterator<Integer> iter = empty.listIterator();
		assertEquals("previous index",-1,iter.nextIndex());
	}

	/** Test the edge case*/
	@Test
	public void testEdge()
	{
		ListIterator<Integer> iter = empty.listIterator();
		iter.add(1);
		iter.add(2);
		assertEquals(new Integer(1), empty.get(0));
	    assertEquals(new Integer(2), empty.get(1));
	    assertEquals(new Integer(1), empty.get(0));
	    assertEquals(new Integer(2), empty.get(1));
	}

}




