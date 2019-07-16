package hw2;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 *  Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 * */

/*
 * You should modify the information above to add your name 
 * 
 * Finally, when your tester is complete, you will rename it CLinkedListTester.java 
 * (renaming LinkedList to CLinkedList everywhere in the file) so that you can
 * use it to test your CLinkedList and MyListIterator classes.
 */
public class LinkedListTester
{
  private LinkedList<Integer> empty ;
  private LinkedList<Integer> one ;
  private LinkedList<Integer> several ;
  private LinkedList<String>  slist ;
  static final int DIM = 5;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new LinkedList<Integer>();

    one = new LinkedList<Integer>();
    one.add(0,new Integer(0));
    
    several = new LinkedList<Integer>() ;
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new LinkedList<String>();
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
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
  
  @Test 
  public void testAdd()
  {
	  try
	  {
		  empty.add(1, new Integer(0));
		  fail("should throw IndexOutOfBoundsException");
	  }catch(IndexOutOfBoundsException ex){
		 System.out.println("empty.add(1, new Integer(0) " +
		 		"IndexOutOfBoundsException");
	  }
	  
	  try
	  {
		  empty.add(0,new Integer(0));
	  }catch(IndexOutOfBoundsException ex){
			 System.out.println("empty.add(0,new Integer(0)" +
			 		"IndexOutOfBoundsException");
	  }
	  
	  try
	  {
		  empty.add(0,null);
	  }catch(NullPointerException ex){
			 System.out.println("empty.add(0,null)" +
				 		"NullPointerException");
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
			  new Integer(2),several.get(0));
  }
  
  @Test
  public void testClear()
  {
	   try
	   {
		   several.clear();
	   }catch(IndexOutOfBoundsException ex){
		   System.out.println("several.clear() IndexOutOfBoundsException");
	   }
	  
  }
  @Test
  public void testIterAdd()
  {
	  ListIterator<Integer> iter = empty.listIterator();
	  try{
		  iter.add(new Integer(1));
	  }catch(NullPointerException ex){
		  System.out.println("iter.add() NullPointerException");
	  }
	  iter.add(new Integer(2));
	  assertEquals("first element",new Integer(1),empty.get(0));
	  assertEquals("first element",new Integer(2),empty.get(1));
	  
	  ListIterator<Integer> iter2 = several.listIterator();
		iter2.next();
		iter2.next();
		iter2.add(new Integer(7));
		assertEquals("test next return value",new Integer(3),iter2.next());
  }
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
	  
	  
  }
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
	  Integer data = new Integer(iter2.next());
	  try{
		  iter2.hasPrevious();
	  }catch(NullPointerException ex){
		  System.out.println("iter2.hasPrevious() " +
		  		"NullPointerException");
	  }
	  assertEquals("previous",data,several.get(0));
	  Integer data2 = iter2.previous();
	  assertEquals("previous",data2,several.get(0));
	  /*Integer data3 = iter2.previous();
	  assertEquals("previous",data3,several.get(4));
	  Integer data4 = iter2.previous();
	  assertEquals("previous",data4,several.get(3));*/
  }
  
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
	  assertEquals("index1",0,index1);
	  assertEquals("index2",1,index2);
	  assertEquals("index3",2,index3);
	  assertEquals("index4",3,index4);
	  assertEquals("index5",4,index5);
	  
	  
  }
  
  @Test
  public void testPrevIndex()
  {
	  ListIterator<Integer> iter = several.listIterator();
	  int index1 = iter.previousIndex();
	  iter.next();
	  iter.next();
	  iter.next();
	  int index2 = iter.previousIndex();
	  assertEquals("index1",-1,index1);
	  assertEquals("index1",2,index2);
	  
	  
  }
  
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
  
 
  
  
  
  
}

