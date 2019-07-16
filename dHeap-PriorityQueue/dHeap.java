// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw6;

import java.util.*;

/**
 * generics d-ary heap class 
 * @author PeiranLi
 * @version 1.0
 * @since 5-5-2016
 * @param <T> generic data type
 */
class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
		

	//instance variables
	private final int DEFAULT_CAPACITY = 5;
	private int nelems;
	private T[] heap;
	private boolean isMaxHeap;
	private int d; 
	
	 /** O-argument constructor. Creates and empty dHeap with 
	 *  initial capacity = 5, and is a 2-min-heap 
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap()
	{
		this.nelems = 0;
		this.heap = (T[])new Comparable[DEFAULT_CAPACITY];
		this.d = 2;
		this.isMaxHeap = false;
	}

	/** 
	 * Constructor to build a min or max dheap
	 * @param isMaxHeap  if true, this is a 2-max-heap, else a 2-min-heap 
	 * with initial size = 'capacity'
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap(int capacity, boolean isMaxHeap)
	{
		this.nelems = 0;
		this.heap = (T[])new Comparable[capacity];
		this.d = 2;
		this.isMaxHeap = isMaxHeap;
	}

	/** 
	 * Constructor to build a with specified initial capacity and
	 * given number of children d. 
	 * @param capacity initial capacity of the heap.	
	 * @param isMaxHeap if true, this is a max-heap, else a min-heap 
	 * @param d number of children, 
	 * @exception if d is less than one, throw IllegalArgumentException();
	 */ 
	@SuppressWarnings("unchecked")
	public dHeap(int capacity, boolean isMaxHeap, int d)
	{
		if(d < 1)
			throw new IllegalArgumentException();
		this.nelems = 0;
		this.isMaxHeap = isMaxHeap;
		this.d = d;
		this.heap = (T[])new Comparable[capacity];
		
	}
			

	/** Returns the number of elements stored in the heap*/
	public int size () {
		return this.nelems;  
	}
	
	public int capacity() {
		return this.heap.length;
	}
	
	/** Adds the specified element to the heap;
	 *  data cannot be null. Resizes the storage if full
	 */	
	public void add (T data) { 
		if(data == null)
			throw new NullPointerException();
		if(this.size() == this.heap.length)
			doubleSize();
		this.heap[this.nelems] = data;
		this.nelems++;
		bubbleUp(this.nelems-1);
		
	}

	/** Removes and returns the element stored on the heap. 
	 * If the heap is empty, then this method throws a NoSuchElementException 
	 */
	public T remove () {
		if(this.nelems == 0)
			throw new NoSuchElementException();
		T data = peek();
		swap(0,this.nelems-1);
		this.heap[this.nelems-1] = null;
		this.nelems--;
		trickleDown(0);
		return data; 
		}

	/**
	 * return the first element of heap without removing it
	 * @return the first element of the heap
	 */
	public T peek() {
		if(this.nelems == 0)
			throw new NoSuchElementException();
		T top = this.heap[0];
		return top;
	}
	
	/**
	 * merge the input heap with calling object
	 * @param hp input heap
	 * @param isMaxHeap if the input heap is max heap or not
	 * @return the heap after merged
	 */
	public dHeap<T> merge(dHeap<T> hp, boolean isMaxHeap){
		int size = hp.size();
		for(int i = 0 ; i < size; i++){
			this.add(hp.remove());
		}
		return this;
	}

	/**
	 * get the nth value with given index
	 * @param index the index of the value to find
	 * @return the value at given index
	 */
	public T getNth(int index){
		return this.heap[index];
	}
	
	/**
	 * help method to enlarge the capacity
	 */
	@SuppressWarnings("unchecked")
	private void doubleSize() {
		T[] newHeap = (T[])new Comparable[this.heap.length*2];
		for(int i = 0; i < this.heap.length; i++)
		{
			newHeap[i] = this.heap[i];
		}
		this.heap = newHeap;
	}
	
	/**
	 * swap two elements at given index
	 * @param i the first index
	 * @param j the second index
	 */
	private void swap(int i, int j){
		T temp = this.heap[i];
		this.heap[i] = this.heap[j];
		this.heap[j] = temp; 
	}
	
	/**
	 * trickle the element at given index down
	 * @param index the index of element
	 */
	private void trickleDown(int index){
		if(isMaxHeap)//if MaxHeap
		{
			//when the first child is not null
			while(firstChildIndex(index)<this.nelems) 
			{
				int large = firstChildIndex(index);
				//find the index of largest child
				for(int i = firstChildIndex(index); i <= lastChildIndex(index); i++)
				{
					if(this.heap[i] == null)
						break;
					if(this.heap[i].compareTo(this.heap[large])>0)
					{
						large = i;
					}
				}
				//compare the largest child with parent
				if(this.heap[index].compareTo(this.heap[large]) < 0)
				{
					swap(index,large);
				}
				else 
					break;
				index = large;
			}
		}
		else{
			//when the first child is not null
			while(firstChildIndex(index)<this.nelems)
			{
				int small = firstChildIndex(index);
				//find the index of smallest child
				for(int i = firstChildIndex(index); i <= lastChildIndex(index); i++)
				{
					if(this.heap[i] == null)
						break;
					if(this.heap[i].compareTo(this.heap[small]) < 0)
					{
						small = i;
					}
				}
				//compare the smallest child with parent
				if(this.heap[index].compareTo(this.heap[small]) > 0)
				{
					swap(index,small);
				}
				else 
					break;
				index = small;
			}
		}
	}
	
	/**
	 * bubble up the element at given index
	 * @param index the index of element
	 */
 	private void bubbleUp(int index) {
 		if(isMaxHeap)//if maxHeap
 		{
 			//when has parent 
 			while(index > 0)
 			{
 				//compare with parent
 				if(this.heap[index].compareTo(this.heap[parentIndex(index)]) > 0)
 					swap(index,parentIndex(index));
 	     			
 				index = parentIndex(index);
 			}
 		}
 		else //minHeap
 		{
 			//when has parent 
 			while(index > 0)
 			{
 				//compare with parent
 				if(this.heap[index].compareTo(this.heap[parentIndex(index)]) < 0)
 					swap(index,parentIndex(index));
 			
 				index = parentIndex(index);
 			}
 		}
 	}
 	
 	/**
 	 * find the parentIndex of given index
 	 * @param index 
 	 * @return the parentIndex 
 	 */
 	private int parentIndex(int index){
 		return (int) (Math.floor((index-1)/d));
 	}
 	
 	/**
 	 * find the firstChildIndex of given index
 	 * @param index
 	 * @return firstChildIndex
 	 */
 	private int firstChildIndex(int index){
 		return this.d*index+1;
 	}
 	
 	/**
 	 * help method to find the index of lastChildIndex
 	 * @param index 
 	 * @return lastChildIndex
 	 */
 	private int lastChildIndex(int index){
 		return this.d*index+d;
 	}
}
