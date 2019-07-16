// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw4;

import java.util.*;

/**
 * @version 1.0
 * @author PeiranLi
 * @since 4-19-2016
 * @param <T>
 */
public class MyQueue<T> implements Stack_QueueInterface<T> {
	//instance variables
	private T[] queueArray;
	private int front;
	private int rear;
	private int nelems;

	private final int DEFAULT_SIZE = 5;
	/**construct a queue */
	@SuppressWarnings("unchecked")
	public MyQueue()
	{
		queueArray = (T[])new Object[DEFAULT_SIZE];
		front = 0;
		rear = 0;
		nelems = 0;
	}
	/** Checks if the collection is empty
	 * @return returns true if the collection is empty, false otherwise
	 */
	@Override
	public boolean isEmpty(){
		if(this.nelems == 0)
			return true;
		return false;
	}

	/** Checks if the collection is full
	 * @return returns true if the collection is full, false otherwise
	 */
	public boolean isFull(){
		if(this.nelems == queueArray.length)
			return true;
		return false;
	}

	/** Checks the size of the collection
	 * @return returns the number of elements in the collection
	 */
	@Override
	public int size(){
		return this.nelems;
	}

	/**
	 * add the data into collection
	 * @param data
	 */
	@Override
	public void addElement(T data){
		if(this.isFull())
			doubleSize();
		queueArray[rear]=data;
		rear = (rear+1)%queueArray.length;
		this.nelems++;
	}

	/**
	 * remove an element from collection
	 * @return data that pop
	 * @throws NoSuchElementException
	 */
	@Override
	public T removeElement(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		T data = queueArray[front];
		queueArray[front] = null;
		front = (front+1)%queueArray.length;
		this.nelems--;
		return data;
	}

	/**
	 * get the first element
	 * @return the first element of the collection
	 * @throws NoSuchElementException
	 */
	public T peek(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		return queueArray[front];
	}

	@SuppressWarnings("unchecked")
	private void doubleSize(){
		T[] newQueue = (T[])new Object[this.queueArray.length*2];
		for(int i = 0; i < this.nelems; i++)
		{
			newQueue[i] = this.queueArray[front];
			front = (front+1)%this.queueArray.length;
		}
		this.queueArray = newQueue;
		front = 0;
		rear = this.nelems;

	}

	/**
	 * get the capacity of the queue
	 * @return the capacity of the queue
	 */
	public int capacity(){
		return queueArray.length;
	}


}
