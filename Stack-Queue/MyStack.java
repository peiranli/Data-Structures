// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw4;

import java.util.*;

/**
 * @since 4-19-2016
 * @author PeiranLi
 * @version 1.0
 * @param <T>
 */
public class MyStack<T> implements Stack_QueueInterface<T>{

	//instance
	private DoubleEndedLL<T> stackList;

	/**construct a stack */
	public MyStack()
	{
		stackList = new DoubleEndedLL<T>();
	}
	/** Checks if the collection is empty
	 * @return returns true if the collection is empty, false otherwise
	 */
	@Override
	public boolean isEmpty(){
		return stackList.isEmpty();
	}


	/** Checks the size of the collection
	 * @return returns the number of elements in the collection
	 */
	@Override
	public int size(){
		return stackList.size();
	}

	/**
	 * add the data into collection
	 * @param data
	 */
	@Override
	public void addElement(T data){
		stackList.addFirst(data);
	}

	/**
	 * remove an element from collection
	 * @return data that pop
	 * @throws EmptyStackException
	 */
	@Override
	public T removeElement(){
		if(this.isEmpty())
			throw new EmptyStackException();
		return stackList.removeFirst();
	}

	/**
	 * get the first element
	 * @return the first element of the collection
	 */
	public T peek(){
		return stackList.get(0);
	}
}
