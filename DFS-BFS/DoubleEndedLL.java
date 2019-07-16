// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw4;

/**
 * double ended linkedlist with head and tail
 * @author PeiranLi
 * @version 1.0
 * @since 4-19-2016
 * @param <T>
 */

public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T>{
	//instance variables
	private int nelems;
	private Node head;
	private Node tail; 
	private static final int ZERO = 0;

	/**
	 * inner class Node
	 */
	private class Node {
		T data;
		Node next;

		/** Constructor to create singleton Node */
		public Node(T element)
		{
			this.data = element;
			this.next = null;
		}

		/** Remove this node from the list. Update next nodes */
		public void remove()
		{
			this.data = null;
			this.next = null;
		}


		/** Set the element 
		 *  @param e new element 
		 */
		public void setElement(T data)
		{
			this.data = data;
		}

		/** Accessor to get the next Node in the list 
		 * @return next Node
		 */
		public Node getNext()
		{
			return this.next; 
		}

		/** Accessor to get the Nodes Element 
		 * @return data in the node 
		 */
		public T getElement()
		{
			return this.data; 
		} 
	}
	/**
	 * constructor
	 */
	public DoubleEndedLL(){
		this.head = null;
		this.tail = null;
	}

	/** Checks if the list is empty
	 * @return returns true if the list is empty, false otherwise
	 */
	public boolean isEmpty(){
		if(this.nelems == ZERO)
			return true; 
		return false;
	}
	/** Checks the size of the list
	 * @return returns the number of elements in the list
	 */
	public int size(){
		return this.nelems;

	}

	/**
	 * @return the element in the list of given index
	 * @throws IndexOutOfBoundsException
	 */
	public T get(int index) throws IndexOutOfBoundsException
	{
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node n = this.getNth(index);
		return n.getElement();
	}

	/** Add an element to the list 
	 * @param index  where in the list to add
	 * @param data data to add
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */ 
	public void add(int index,T data) throws NullPointerException,IndexOutOfBoundsException
	{
		if(data == null)
			throw new NullPointerException();
		if(index > this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node newNode = new Node(data);
		if(isEmpty())
		{
			addWhenEmpty(newNode);
			this.nelems++;
		}
		else if(index == 0)
		{
			addFirst(data);
		}
		else if(index == this.nelems)
		{
			addLast(data);
		}
		else
		{
			Node before = getNth(index-1);
			newNode.next = before.next;
			before.next = newNode;
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
	public T set(int index, T data) 
			throws IndexOutOfBoundsException,NullPointerException
			{
		if(data == null)
			throw new NullPointerException();
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node cur = this.getNth(index);
		T prevData = cur.getElement();
		cur.setElement(data);
		return prevData;
			}

	/** Adds a new node to the front of the list
	 * @param newItem - an element to add
	 */
	@Override
	public void addFirst(T newItem){
		Node newNode = new Node(newItem);
		if(this.isEmpty())
		{
			addWhenEmpty(newNode);
		}
		else{
			newNode.next = this.head;
			this.head = newNode;
		}
		this.nelems++;
	}

	/** Adds a new node to the end of the list
	 * @param newItem - an element to add
	 */
	@Override
	public void addLast(T newItem){
		Node newNode = new Node(newItem);
		if(this.isEmpty())
		{
			addWhenEmpty(newNode);
		}
		else
		{
			this.tail.next = newNode;
			this.tail = newNode;
		}
		this.nelems++;
	}


	/** Remove the element at an index in the list 
	 * @param index  where in the list to add
	 * @return element the data found
	 * @throws IndexOutOfBoundsException
	 */ 
	public T remove(int index) throws IndexOutOfBoundsException {
		if(index >= this.nelems || index < 0)
			throw new IndexOutOfBoundsException();
		Node cur = this.getNth(index);
		T data = cur.getElement();
		if(this.nelems == 1){
			removeWhenHasOne();
			this.nelems--;
		}
		else if(index == 0)
		{
			removeFirst();
		}
		else if(index == this.nelems-1)
		{
			removeLast();
		}
		else
		{
			Node prev = this.getNth(index-1);
			prev.next = cur.next;
			this.nelems--;
		}

		return data;
	}
	/** Removes a node from the beginning of the list
	 * @return element the data found
	 * @throws NullPointerException
	 */
	@Override
	public T removeFirst(){
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		T data = this.head.getElement();
		if(this.nelems == 1)
		{
			this.head = null;
			this.tail = null;
		}
		else
		{
			this.head = this.head.next;
		}
		this.nelems--;
		return data;
	}


	/** Removes a node from the end of the list
	 * @return element the data found
	 * @throws NullPointerException
	 */
	@Override
	public T removeLast(){
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		T data = this.tail.getElement();
		if(this.nelems == 1)
		{
			this.head = null;
			this.tail = null;
		}
		else
		{
			Node lastSecond = findLastSecond();
			this.tail = lastSecond;
			lastSecond.next = null;
		}
		this.nelems--;
		return data;

	}

	/** Clear the linked list */
	public void clear()
	{
		if(this.isEmpty())
			return;
		Node cur = this.head;
		while(cur != null)
		{
			this.head = cur.next;
			cur.remove();
			cur = this.head;
			this.nelems--;
		}
	}

	/** 
	 * Helper method to get the last second node
	 */
	private Node findLastSecond()
	{
		Node cur = this.head;
		while(cur.getNext() != null)
		{
			if(cur.getNext() == this.tail)
			{
				break;
			}
			cur = cur.next;
		}
		return cur;
	}

	/**Helper method to get the Node at the Nth index 
	 * @param index the nth node to get
	 * @return the node of given index
	 */
	private Node getNth(int index) 
	{
		if(index < 0 && index >= this.nelems)
			throw new IndexOutOfBoundsException();
		Node cur = this.head;
		int i = 0;
		while(i!=index){
			cur = cur.next;
			i++;
		}
		return cur;


	}

	/**
	 * help method to add then list is empty
	 * @param newNode the node to add
	 */
	private void addWhenEmpty(Node newNode){
		this.head = newNode;
		this.tail = newNode;
	}

	/**
	 * help method to remove when list has only one element
	 */
	private void removeWhenHasOne() {
		this.head = null;
		this.tail = null;
	}
}
