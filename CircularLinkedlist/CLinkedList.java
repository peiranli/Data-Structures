// *
// NAME: <Peiran Li>

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

/**
 * Circular Doubly-Linked list that stores non-null objects of a type.
 * @version 1.0
 * @author Peiran Li
 * @since 4-11-2016
 */

package hw2;

import java.util.*;

/**
 *circular doubly linked list that contains iterator and node class
 * @param <E> generic type
 */
public class CLinkedList<E> extends AbstractList<E> {

	private int nelems;
	private Node head;
	//private Node tail; // if needed
	private static final int ZERO = 0;

	/**
	 * node class that contains data, next, and previous
	 */
	private class Node {
		E data;
		Node next;
		Node prev;

		/** Constructor to create singleton Node */
		public Node(E element)
		{
			this.data = element;
			this.next = null;
			this.prev = null;
		}
		/**
		 * Constructor to create singleton link it between previous and next 
		 *   @param element Element to add, can be null
		 *   @param prevNode predecessor Node, can be null
		 *   @param nextNode successor Node, can be null 
		 */
		public Node(E element, Node prevNode, Node nextNode)
		{
			this.data = element;
			this.next = nextNode;
			this.prev = prevNode;
		}
		/** Remove this node from the list. Update previous and next nodes */
		public void remove()
		{
			this.data = null;
			this.prev.next = this.next;
			this.next.prev = this.prev;
		}
		/** Set the previous node in the list
		 *  @param p new previous node
		 */
		public void setPrev(Node p)
		{
			p.prev = this.prev;
			this.prev.next = p;
			this.prev = p;
			p.next = this;
		}
		/** Set the next node in the list
		 * @param n new next node
		 */
		public void setNext(Node n)
		{
			n.next = this.next;
			this.next.prev = n;
			this.next = n;
			n.prev = this;
		}

		/** Set the element 
		 *  @param e new element 
		 */
		public void setElement(E e)
		{
			this.data = e;
		}

		/** Accessor to get the next Node in the list 
		 * @return next Node
		 */
		public Node getNext()
		{
			return this.next; 
		}
		/** Accessor to get the prev Node in the list 
		 * @return previous node
		 */
		public Node getPrev()
		{
			return this.prev; 
		} 
		/** Accessor to get the Nodes Element 
		 * @return data in the node 
		 */
		public E getElement()
		{
			return this.data; 
		} 
	}

	/** ListIterator for My circular doubly linked list */ 
	protected class MyListIterator implements ListIterator<E> {

		private boolean forward;
		private boolean canRemove;
		private Node left,right; // Cursor sits between these two nodes
		private int idx = 0;     // Current position, what next() would return 
		private int size = 0;

		/**
		 * constructor
		 * initialize instance variables
		 */
		public MyListIterator()
		{
			/* Hint: try to initialize some of the variables above, like
			 * 'left' or 'right'.
			 */
			if(nelems == 0)
			{
				this.left = head;
				this.right = head;
			}
			else if(nelems == 1)
			{
				this.left = head.next;
				this.right = head.next;
			}
			else
			{
				this.left = head.next.prev;
				this.right = head.next;
			}
			
			this.forward = false;
			this.canRemove = false;
			this.size = nelems;
			
			
		}
		/**
		 * Adds an element to the list between 'left' and 'right'.
		 * 
		 * @param e: the data of the node that want to be added
		 * @throws NullPointerException
		 */
		@Override
		public void add(E e) throws  NullPointerException
		{
			if(e == null)
				throw new NullPointerException();
			Node newNode = new Node(e);
			if(isEmpty()) //if list is empty
			{
				addWhenEmpty(newNode);
				this.right = newNode;
			}
			else if(this.size == 1)
			{
				newNode.prev = this.right;
				newNode.next = this.right;
				this.right.next = newNode;
				this.right.prev = newNode;
				this.left = newNode;
			}
			else 
			{
				if(this.right == head.next)
				{
					newNode.prev = this.left;
					newNode.next = this.right;
					newNode.prev.next = newNode;
					newNode.next.prev = newNode;
					this.left = newNode;
				}
				else
				{
					this.left.setNext(newNode);
					this.right.setPrev(newNode);
					this.left = newNode;
				}
				this.idx = (this.idx+1)%this.size;
				
		    }
			this.canRemove = false;
			nelems++;
			this.size++;
		}
		/**
		 * @return if next node has element
		 */
		@Override
		public boolean hasNext()
		{
			if(this.size == 0)
				return false;
			return true;
		}

		/**
		 * @return if previous node has element
		 */
		@Override
		public boolean hasPrevious()
		{
			if(this.size == 0)
				return false;
			return true;
		}
		
		/**
		 * @throws NoSuchElementException
		 * @return next element in the list
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			if(isEmpty())
			{
				throw new NoSuchElementException();
			}
			if(!hasNext())
				throw new NoSuchElementException();
			E data = this.left.next.getElement();
			if(this.size == 1 && this.left == head)
			{
				this.left = head.next;
				this.right = head.next;
			}
			else if(this.size ==1 && this.left == head.next)
			{
				this.right = this.right.next;
				this.left = this.left.next;
				this.idx = (this.idx+1)%this.size;
			}
			else
			{
				this.left = this.left.next;
				this.right = this.right.next;
				this.idx = (this.idx+1)%this.size;
				
			}
			
			this.canRemove = true;
			this.forward = true;
			return data;  
		}
		
		/**
		 * @return next index
		 */
		@Override
		public int nextIndex()
		{
			if(isEmpty())
				return -1;
			else
				return this.idx; 
		}
		
		/**
		 * 
		 * @throws NoSuchElementException
		 * @return previous element in the list
		 */
		@Override
		public E previous() throws NoSuchElementException
		{
			if(isEmpty())
			{
				throw new NoSuchElementException();
			}
			if(!hasPrevious())
				throw new NoSuchElementException();
			E data = this.right.prev.getElement();
			if(this.left == head && this.right == head.next)
			{
				this.left = head.next.prev;
			}
			if(this.left == head.next 
					&& this.size > 1)
			{
				this.left = head.next.prev;
				this.right = this.right.prev;
				this.idx = this.previousIndex();
			}
			else if(this.size == 1)
			{
				this.left = head.next;
				this.right = head.next;
			}
			else
			{
				this.right = this.left;
				this.left = this.left.prev;
				this.idx = this.previousIndex();
			}
			
			this.canRemove = true;
			this.forward = false;
			return data; 
		}

		/**
		 * @return previous index
		 */
		@Override
		public int previousIndex()
		{
			if(isEmpty())
				return -1;  
			else
				return (this.idx+this.size-1)%(this.size);
		}
		/**
		 * remove the node that last returned
		 * @throws IllegalStateException
		 * 
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if(this.canRemove == false)
				throw new IllegalStateException();
			if(forward) //if the iterator goes forward
			{
				Node cur = this.left;
				this.left = this.left.prev;
				if(cur == head.next && !isEmpty()) //if the cursor points to first node
				{
					cur.next.prev = cur.prev;
					cur.prev.next = cur.next;
					head.next = cur.next;
				}
				else if(this.size == 1)
				{
					head = null;
				}
				else
				{
					cur.remove();
				}
				
			}
			else
			{
				Node cur = this.right;
				this.right = this.right.next;
				cur.remove();
			}
			this.canRemove = false;
			nelems--;
			this.size--;
		}
		/**
		 * Replaces last element returned by next() or previous() with a
		 * given element.
		 * @param the element to be set
		 */
		@Override
		public void set(E e) 
				throws NullPointerException,IllegalStateException
		{
			if(e == null)
				throw new NullPointerException();
			if(this.canRemove == false)
				throw new IllegalStateException();
			if(forward)
				this.left.setElement(e);
			else
				this.right.setElement(e);
			
				
			
		}

	}


	//  Implementation of the CLinkedList Class


	/** Only 0-argument constructor is defined */
	public CLinkedList()
	{
		/* Hint: You may wish to keep track of a head, tail (up to you), and
		 *       the number of elements. */
		this.head = new Node(null);
		this.head.next = null;
		this.nelems = ZERO;

	}
	/**
	 * 
	 * @return the size of list
	 */
	@Override
	public int size()
	{
		// need to implement the size method
		return this.nelems; 
	}

	/**
	 * @return the element in the list of given index
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node n = this.head.next;
		int i = 0;
		while(i!=index)
		{
			n = n.next;
			i++;
		}
		return n.getElement();
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
		if(index > this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		if(data == null)
			throw new NullPointerException();
		Node newNode = new Node(data);
		if(index == this.nelems)	
		{
			if(this.nelems == 0) //if the list is empty
			{
				addWhenEmpty(newNode);
				this.nelems++;
			}
			else
			{
				addLast(newNode);
				this.nelems++;
			}
		}
		else if(index == 0)
		{
			if(this.nelems == 0) //if the list is empty
			{
				addWhenEmpty(newNode);
				this.nelems++;
			}
			else
			{
				addFirst(newNode);
				this.nelems++;
			}
		}
		else
		{
			Node n = getNth(index);
			n.setPrev(newNode);
			this.nelems++;
		}
		
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
		if(data == null)
			throw new NullPointerException();
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node n = this.getNth(index);
		E prevData = n.getElement();
		n.setElement(data);
		return prevData;
	}

	/** Remove the element at an index in the list 
	 * @param index  where in the list to add
	 * @return element the data found
	 * @throws IndexOutOfBoundsException
	 */ 
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node n = this.getNth(index);
		E data = n.getElement();
		if(n == this.head.next)
		{
			this.head.next.next.prev = this.head.next.prev;
			this.head.next.prev.next = this.head.next.next;
			this.head.next = n.next;
			
		}
		else
		{
			n.remove();
		}
		
		this.nelems--;
		return data; 
	}

	/** Clear the linked list */
	public void clear()
	{
		if(this.isEmpty())
			return;
		Node n = this.head.next;
		Node cur;
		while(!this.isEmpty())
		{
			cur = n;
			n = n.next;
			cur.remove();
			this.nelems--;
		}
	}

	/** Determine if the list empty 
	 *  @return true if empty, false otherwise */
	public boolean isEmpty()
	{
		if(this.nelems == 0)
			return true; 
		return false;
	}

	public Iterator<E> QQQiterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> QQQlistIterator()
	{
		return new MyListIterator();
	}

	/**Helper method to get the Node at the Nth index 
	 * @param index the nth node to get
	 * @return the node of given index
	 */
	private Node getNth(int index) 
	{
		Node n = this.head.next;
		if(index >= 0 && index < this.nelems)
		{
			for(int i = 0; i < this.nelems; i++)
			{
				if(i == index)
				{
					break;
				}
				n = n.next;
			}
			return n;
		}
		else
			throw new IndexOutOfBoundsException();
		
	}
	
	/**
	 * help method to add node when the list is empty
	 * @param newNode
	 */
	private void addWhenEmpty(Node newNode)
	{
		this.head.next = newNode;
		newNode.prev = newNode;
		newNode.next = newNode;
	}
	
	/**
	 * help method to add node to last place
	 * @param newNode
	 */
	private void addLast(Node newNode)
	{
		newNode.prev = this.head.next.prev;
		newNode.next = this.head.next;
		this.head.next.prev.next = newNode;
		this.head.next.prev = newNode;
	}

	/**
	 * help method to add node to first place
	 * @param newNode
	 */
	private void addFirst(Node newNode)
	{
		newNode.prev = this.head.next.prev;
		this.head.next.prev = newNode;
		newNode.next = this.head.next;
		this.head.next = newNode;
		newNode.prev.next = newNode;
	}



	/**
	 * @return the listIterator of linked list
	 */
   public Iterator<E> iterator()
   {
    return new MyListIterator();
   }
   
   /**
	 * @return the listIterator of linked list
	 */
   public ListIterator<E> listIterator()
   {
    return new MyListIterator();
   }
   
   /**
    * reverse the list and then concatenate
    * @param list
    */
   public void reverseAndConcat(CLinkedList<E> list)
   {
	   CLinkedList<E> reverse = new CLinkedList<E>();
	   
	   for(int i = 0; i < list.size();i++)
	   {
		   reverse.add(i,list.get(list.size()-1-i));
	   }
	   
	   for(int i = list.size(),j = 0; j < reverse.size(); i++,j++)
	   {
		   list.add(i,reverse.get(j));
	   }
   }
   
   /**
    * swap nodes from index ind1 to ind2 both inclusive
    * @param list
    * @param ind1
    * @param ind2
    * @throws IndexOutOfBoundsException
    */
   public void swapNodes(CLinkedList <E> list, int ind1, int ind2)
		   throws IndexOutOfBoundsException
   {
	   if(ind1 < 0 || ind1 > this.size() || ind1 > list.size()
			   || ind2 < 0 || ind1 > this.size() || ind2 > list.size())
		   throw new IndexOutOfBoundsException();
	  
	   for(int i = ind1; i <= ind2; i++)
	   { 
		   Node n1 = list.getNth(i);
		   Node n2 = this.getNth(i);
		   E temp = n1.getElement();
		   n1.setElement(n2.getElement());
		   n2.setElement(temp);
	   }
	   
   }
}
// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

