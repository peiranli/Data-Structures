// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw6;
/**
 * generics d-ary heap interface
 * @author PeiranLi
 * @version 1.0
 * @since 5-5-2016
 * @param <T> generic data type
 */
public interface dHeapInterface<T extends java.lang.Comparable<? super T>> {
	
	/** Returns the number of elements stored in the heap*/
	public int size();
	
	/** Removes and returns the element stored on the heap. 
	 * If the heap is empty, then this method throws a NoSuchElementException 
	 */
	public T remove();
	
	/** Adds the specified element to the heap;
	 *  o cannot be null. Resizes the storage if full
	 */
	public void add(T o);

}
