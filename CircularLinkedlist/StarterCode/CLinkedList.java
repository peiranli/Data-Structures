// *
// NAME: <your name>
// ID: <your student ID>
// LOGIN: <your class login>
// *

/**
 * Circular Doubly-Linked list that stores non-null objects of a type.
 * @version
 * @author
 * @since
 */

package hw2;

import java.util.*;

public class CLinkedList<E> extends AbstractList<E> {
  
  private int nelems;
  private Node head;
  private Node tail; // if needed
  private static final int ZERO = 0;
  
  protected class Node {
    E data;
    Node next;
    Node prev;
    
    /** Constructor to create singleton Node */
    public Node(E element)
    {
    }
    /**
     * Constructor to create singleton link it between previous and next 
     *   @param element Element to add, can be null
     *   @param prevNode predecessor Node, can be null
     *   @param nextNode successor Node, can be null 
     */
    public Node(E element, Node prevNode, Node nextNode)
    {

    }
    /** Remove this node from the list. Update previous and next nodes */
    public void remove()
    {

    }
    /** Set the previous node in the list
     *  @param p new previous node
     */
    public void setPrev(Node p)
    {

    }
    /** Set the next node in the list
     * @param n new next node
     */
    public void setNext(Node n)
    {

    }
    
    /** Set the element 
    *  @param e new element 
    */
    public void setElement(E e)
    {
      
    }

    /** Accessor to get the next Node in the list */
    public Node getNext()
    {
      return (Node) null; // XXX-CHANGE-XXX
    }
    /** Accessor to get the prev Node in the list */
    public Node getPrev()
    {
      return (Node) null; // XXX-CHANGE-XXX
    } 
    /** Accessor to get the Nodes Element */
    public E getElement()
    {
      return (E) null; // XXX-CHANGE-XXX
    } 
  }
  
  /** ListIterator implementation */ 
  protected class MyListIterator implements ListIterator<E> {
    
    private boolean forward;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int idx = 0;     // Current position, what next() would return 
    private int size = 0;

    public MyListIterator()
    {
      /* Hint: try to initialize some of the variables above, like
       * 'left' or 'right'.
       */
    }
    /**
    * Adds an element to the list between 'left' and 'right'.
    * 
    * @param TODO: finish me!
    * @throws TODO: finish me!
    */
    @Override
    public void add(E e) throws  NullPointerException
    {
    }
    /**
    * Checks if there is another element to be retrieved by calling 
    * next().
    * 
    * Hint: how would this work for a circular list?
    */
    @Override
    public boolean hasNext()
    {
      return false; // XXX-CHANGE-XXX
    }
    
    @Override
    public boolean hasPrevious()
    {
      return false; // XXX-CHANGE-XXX
    }
    @Override
    public E next() throws NoSuchElementException
    {
      return (E) null;  // XXX-CHANGE-XXX
    }
    @Override
    public int nextIndex()
    {
      return 0; // XXX-CHANGE-XXX
    }
    @Override
    public E previous() throws NoSuchElementException
    {
      return (E) null; // XXX-CHANGE-XXX
    }
    
    @Override
    public int previousIndex()
    {
      return 0;  // XXX-CHANGE-XXX
    }
    @Override
    public void remove() throws IllegalStateException
    {
    }
    /**
     * Replaces last element returned by next() or previous() with a
     * given element.
     */
    @Override
    public void set(E e) 
      throws NullPointerException,IllegalStateException
    {
    }
    
  }
  
  
  //  Implementation of the CLinkedList Class
  
  
  /** Only 0-argument constructor is defined */
  public CLinkedList()
  {
    /* Hint: You may wish to keep track of a head, tail (up to you), and
     *       the number of elements. */
  }
  @Override
  public int size()
  {
    // need to implement the size method
    return 0; // XXX-CHANGE-XXX 
  }
  
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
    return (E) null;  // XXX-CHANGE-XXX
  }
  
  @Override
  /** Add an element to the list 
    * @param index  where in the list to add
    * @param data data to add
    * @throws IndexOutOfBoundsException
    * @throws NullPointerException
    */ 
    public void add(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
  {
  }
  
  
  /** Set the element at an index in the list 
    * @param index  where in the list to add
    * @param data data to add
    * @return element that was previously at this index.
    * @throws IndexOutOfBoundsException
    * @throws NullPointerException
    */ 
  public E set(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
  {
    return (E) null; // XXX-CHANGE-XXX
  }
  
  /** Remove the element at an index in the list 
    * @param index  where in the list to add
    * @return element the data found
    * @throws IndexOutOfBoundsException
    */ 
  public E remove(int index) throws IndexOutOfBoundsException
  {
    return (E) null; // XXX-CHANGE-XXX
  }
  
  /** Clear the linked list */
  public void clear()
  {
  }
  
  /** Determine if the list empty 
    *  @return true if empty, false otherwise */
  public boolean isEmpty()
  {
    return true;  // XXX-CHANGE-XXX
  }
  
  public Iterator<E> QQQiterator()
  {
    return new MyListIterator();
  }
  public ListIterator<E> QQQlistIterator()
  {
    return new MyListIterator();
  }
  
  // Helper method to get the Node at the Nth index
  private Node getNth(int index) 
  {
    return (Node) null;  // XXX-CHANGE-XXX
  }
  
  
  
  
  /*  UNCOMMENT the following when you believe your MyListIterator class is
   functioning correctly
   public Iterator<E> iterator()
   {
    return new MyListIterator();
   }
   public ListIterator<E> listIterator()
   {
    return new MyListIterator();
   }
   */
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

