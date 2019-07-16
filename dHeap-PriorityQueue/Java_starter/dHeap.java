package hw6;

import java.util.*;


class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
		

	 /** O-argument constructor. Creates and empty dHeap with 
	 *  initial capacity = 5, and is a 2-min-heap 
	 */ 
	public dHeap()
	{
	}

	/** 
	 * Constructor to build a min or max dheap
	 * @param isMaxHeap  if true, this is a 2-max-heap, else a 2-min-heap 
	 * with initial size = 'capacity'
	 */ 
	public dHeap(int capacity, boolean isMaxHeap)
	{
	}

	/** 
	 * Constructor to build a with specified initial capacity and
	 * given number of children d. 
	 * @param capacity initial capacity of the heap.	
	 * @param isMaxHeap if true, this is a max-heap, else a min-heap 
	 * @param d number of children, 
	 * @exception if d is less than one, throw IllegalArgumentException();
	 */ 
	public dHeap(int capacity, boolean isMaxHeap, int d)
	{
	}
			


	public int size () {  }
	
	public void add (T data) {  }

		
	public T remove () { }

}
