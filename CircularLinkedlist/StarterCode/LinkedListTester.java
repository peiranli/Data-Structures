package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


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
}
