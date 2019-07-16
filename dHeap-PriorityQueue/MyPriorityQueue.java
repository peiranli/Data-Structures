// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw6;

/**
 * generic priority queue class
 * @since 5-5-2016
 * @author PeiranLi
 * @version 1.0
 * @param <T> generic data type
 */
public class MyPriorityQueue<T extends Comparable <? super T>> {
	private dHeap<T> priorityQueue;
	
	/**
	 * constructor
	 * @param capacity the capacity of priority queue
	 */
	public MyPriorityQueue(int capacity){
		priorityQueue = new dHeap<T>(capacity,false);
	}
	
	/**
	 * add an element to priorityQueue
	 * @param data the data of the element
	 */
	public void add(T data){
		priorityQueue.add(data);
	}
	
	/**
	 * poll the first element
	 * @return the data of the first element
	 */
	public T poll(){
		return priorityQueue.remove();
	}

}
