// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw6;

import static org.junit.Assert.*;  

import java.util.*;

import org.junit.Before;
import org.junit.Test;
/**
 * tester for dHeap
 * @version 1.0
 * @author PeiranLi
 * @since 5-5-2016
 */
public class dHeapTester {
	//heaps
	private dHeap<Integer> twoMinHeap = new dHeap<Integer>();
	private dHeap<Integer> twoMinHeap2 = new dHeap<Integer>();
	private dHeap<Integer> twoMaxHeap = new dHeap<Integer>(5,true);
	private dHeap<Integer> fourMinHeap = new dHeap<Integer>(15,false,4);
	private dHeap<Integer> fiveMaxHeap = new dHeap<Integer>(20,true,5);
	private dHeap<Integer> fiveMaxHeap2 = new dHeap<Integer>(30,true,5);

	/**set up for the test */
	@Before
	public void setUp() {
		//initialize all the heaps
		twoMinHeap.add(5);
		twoMinHeap.add(4);
		twoMinHeap.add(3);
		twoMinHeap.add(1);
		twoMinHeap.add(2);
		for(int i = 6; i < 20; i++)
		{
			twoMinHeap2.add(i);
		}
		for(int i = 0; i < 5; i++){
			twoMaxHeap.add(i);
		}
		for(int i = 0; i < 15; i++){
			fourMinHeap.add(15-i);
		}
		for(int i = 0; i < 20; i++){
			fiveMaxHeap.add(i);
		}
		for(int i = 20; i < 50; i++){
			fiveMaxHeap2.add(i);
		}
	}
	/**test add method */
	@Test
	public void testAdd() {
		//test twoMinHeap
		assertEquals("first element of twoMinHeap",new Integer(1),twoMinHeap.peek());
		assertEquals("first element of twoMinHeap",new Integer(2),twoMinHeap.getNth(1));
		assertEquals("first element of twoMinHeap",new Integer(4),twoMinHeap.getNth(2));
		assertEquals("first element of twoMinHeap",new Integer(5),twoMinHeap.getNth(3));
		assertEquals("first element of twoMinHeap",new Integer(3),twoMinHeap.getNth(4));
		
		//test rest of heaps
		assertEquals("first element of twoMaxHeap",new Integer(4),twoMaxHeap.peek());
		assertEquals("first element of fourMinHeap",new Integer(1),fourMinHeap.peek());
		assertEquals("first element of fiveMaxHeap",new Integer(19),fiveMaxHeap.peek());
		
	}
	/** test remove method*/
	@Test
	public void testRemove(){
		//test the first element after removing 
		twoMinHeap.remove();
		assertEquals("first element of twoMinHeap after remove",new Integer(2),twoMinHeap.peek());
		
		twoMaxHeap.remove();
		assertEquals("first element of twoMaxHeap after remove",new Integer(3),twoMaxHeap.peek());
		
		fourMinHeap.remove();
		assertEquals("first element of fourMinHeap after remove",new Integer(2),fourMinHeap.peek());
		
		fiveMaxHeap.remove();
		assertEquals("first element of fiveMaxHeap after remove",new Integer(18),fiveMaxHeap.peek());
	}
	
	/**test add method when heap is full */
	@Test
	public void testAddWhenFull(){
		int prevCapacity = fourMinHeap.capacity();
		fourMinHeap.add(16);
		assertEquals("capacity after add", prevCapacity*2,fourMinHeap.capacity());
		assertEquals("first element after add", new Integer(1),fourMinHeap.peek());
		
		int prevCapacity2 = fiveMaxHeap.capacity();
		fiveMaxHeap.add(20);
		assertEquals("capacity after add", prevCapacity2*2,fiveMaxHeap.capacity());
		assertEquals("first element after add", new Integer(20),fiveMaxHeap.peek());
	}
	
	/**test the exception of remove */
	@Test 
	public void testRemoveException(){
		//remove all the element
		for(int i = 0; i < 5; i++){
			twoMinHeap.remove();
		}
		//remove when empty
		try{
			twoMinHeap.remove();
			fail("should throw exception");
		}catch(NoSuchElementException ex){
			
		}
		
		for(int i = 0; i < 15; i++)
		{
			fourMinHeap.remove();
		}
		try{
			fourMinHeap.remove();
			fail("should throw exception");
		}catch(NoSuchElementException ex){
			
		}
		
		for(int i = 0; i <20; i++){
			fiveMaxHeap.remove();
		}
		try{
			fiveMaxHeap.remove();
			fail("should throw exception");
		}catch(NoSuchElementException ex){
			
		}
	}
	/** test if the remove can return correct data*/
	@Test
	public void testRemoveReturnValue(){
		for(int i = 0; i < 15; i++){
			assertEquals("return value",new Integer(i+1),fourMinHeap.remove());
		}
		
		for(int i = 0; i < 20;i++){
			assertEquals("return value",new Integer(20-i-1),fiveMaxHeap.remove());
		}
	}
	/** test the merge method*/
	@Test
	public void testMerge(){
		//store previous size
		int size = twoMinHeap.size();
		int size2 = twoMinHeap2.size();
		//merge
		dHeap<Integer> newHeap = twoMinHeap.merge(twoMinHeap2, false);
		//test if size is correct
		assertEquals("size after merge",newHeap.size(),size+size2);
		//test if order is correct
		assertEquals("first element",new Integer(1),newHeap.peek());
		size = fiveMaxHeap.size();
		size2 = fiveMaxHeap2.size();
		dHeap<Integer> newHeap2 = fiveMaxHeap.merge(fiveMaxHeap2, false);
		assertEquals("size after merge",newHeap2.size(),size+size2);
		assertEquals("first element",new Integer(49),newHeap2.peek());
	}

}
