// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw4;
interface Stack_QueueInterface<T> {


	/** Tests if the storage is empty.
	 * @return true a storage contains no items; false otherwise.
	 */
	public boolean isEmpty();

	/** Adds an element to a storage
	 * @param newItem - item to be added
	 */
	public void addElement(T newItem);

	/** Removes the object from the storage and returns
	 * that object as the value of this function.
	 * @return value of the removed object.
	 */
	public T removeElement();

	/** Returns the size of the storage
	 * @return the size of the storage
	 */
	public int size();


}
