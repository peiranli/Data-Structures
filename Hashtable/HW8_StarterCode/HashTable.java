// *
// NAME: <Peiran Li>   

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

package hw8;

import java.io.*; 
import java.util.*;
/**
 * my hash table class
 * @version 1.0
 * @author PeiranLi
 * @since 5-19-2016
 */
public class HashTable implements IHashTable {
	private LinkedList<String>[] myList;//array of linked list
	private File file; //file to store statistics
	private PrintWriter output; //output stream


	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	
		
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		
		//Initialize
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		myList = new LinkedList[size];
		//initialize array
		for(int i = 0; i< size; i++){
			myList[i] = new LinkedList<String>();
		}
		
	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName){
		
		//Set printStats to true
		this(size);
		this.printStats = true;
		this.statsFileName = fileName;
		file = new File(this.statsFileName);
		try {
			this.output = new PrintWriter(file);
		} catch (FileNotFoundException e) {
		}
	}

	/**
	 * Inserts element in the hash table. 
	 * should return true or false, 
	 * depending on whether it was inserted or not. 
	 * Throw a NullPointerException if a null value is passed. 
	 * @param value the element to insert
	 * @return Return true if item is inserted, false if it already exists.
	 * @throws NullPointerException
	 */
	@Override
	public boolean insert(String value) {
		//if the value is null, throw NullPointerException
		if(value == null)
			throw new NullPointerException();
		//if load factor is greater than 2/3 double and rehash
		if(loadFactor()>(2.0/3)){
			this.rehash();
		}
		//get the hash key
		int hash = hashValue(value, myList.length);
		//if the value is already existed, return false
		if(lookup(value))
			return false;
		//else add it 
		else{
			if(myList[hash].size() > 0)
				this.collision++;
			myList[hash].add(value);
			this.nelems++;
		}
		return true;
		
	}

	@Override
	/**
	 * Use the hash table to determine where i is, 
	 * delete it from the hash table. 
	 *  (an item cannot be deleted if it does not exist in the hash table).
	 *  @param value the element to delete
	 *  @throws Throw a NullPointerException if a null value is passed. 
	 *  @return should return true if the item is deleted, 
	 *  false if it cannot be deleted 
	 */
	public boolean delete(String value) {
		//if value is null, throw NullPointerException
		if(value == null)
			throw new NullPointerException();
		//get the hash key
		int hash = hashValue(value, myList.length);
		//if the value does not exist, return false
		if(!lookup(value))
			return false;
		//else find the hash and delete it
		else{
			for(int i = 0; i < myList[hash].size(); i++){
				if(myList[hash].get(i).equals(value))
					myList[hash].remove(i);
			}
			this.nelems--;
		}
		return true;
	}

	/**
	 * Uses the hash table to determine if i is in the data structure. 
	 * @return should return true or false, depending on whether the item exists. 
	 * @throws Throw a NullPointerException if a null value is passed. 
     * @param value the element to find
	 */
	@Override
	public boolean lookup(String value) {
		//if the value is null throw NullPointerException
		if(value == null)
			throw new NullPointerException();
		//get the hash key
		int hash = hashValue(value, myList.length);
		//go through entire list and find value
		for(int i = 0; i < myList[hash].size();i++){
			if(myList[hash].get(i).equals(value))
				return true;
		}
		return false;
		
	}

	/**
	 * Print out the hash table
	 */
	@Override
	public void printTable() {
		for(int i = 0 ; i < myList.length; i++){
			System.out.print(i + ": ");
			for(int j = 0; j < myList[i].size(); j++){
				System.out.print(myList[i].get(j)+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @return the number of elements currently stored in the hashtable
	 */
	@Override
	public int getSize() {
		return this.nelems;
	}

	/**
	 * help method to get hash value
	 * @param key the element key
	 * @param tableSize the size of hash table
	 * @return hash value of the key
	 */
	private int hashValue(String key, int tableSize){
		int hashValue = 0;
		//add all the char's value up to get hash value
		for(int i = 0; i < key.length(); i++){
			int letter = key.charAt(i);
			hashValue = (hashValue*27+letter)%tableSize;
		}
		return hashValue;
	}
	
	
	//We expect AT LEAST the following helper methods in your code
	//expand() to enlarge the HashTable when load factor goes over the threshold
	//rehash() to rehash the items into the table after expansion
	//printStatistics() to print the statistics after each expansion. This method will be called only if printStats=true
	/**
	 * get the count of longest chain
	 * @return the longest chain
	 */
	private int longestChain(){
		int longest = 0;
		for(int i = 0; i < myList.length;i++){
			if(longest < myList[i].size() )
				longest = myList[i].size();
		}
		return longest;
	}
	
	/**
	 * get the load factor
	 * @return load factor
	 */
	private double loadFactor(){
		double sum = 0;
		for(int i = 0; i < myList.length;i++){
			sum += myList[i].size();
		}
		return sum/(double)myList.length;
	}
	/**
	 * 
	 * @return a new expanded linked list
	 */
	@SuppressWarnings("unchecked")
	private LinkedList<String>[] expand(){
		//double the size
		LinkedList<String>[] newList = new LinkedList[myList.length*2];
		for(int i = 0; i < myList.length*2; i++){
			newList[i] = new LinkedList<String>();
		}
		this.expand+=1;
		return newList;
	}
	/**
	 * rehash all the elements
	 */
	private void rehash(){
		LinkedList<String>[] newList = expand();
		//keep records
		printStatistics();
		this.collision = 0;
		//rehash all the elements
		for(int i = 0; i < this.myList.length;i++){
			for(int j = 0; j < myList[i].size(); j++){
				String value = myList[i].get(j);
				int newHash = hashValue(value,newList.length);
				if(newList[newHash].size() > 0)
						this.collision++;
				newList[newHash].add(value);
			}
		}
		this.myList = newList;
		

	}
	
	/**
	 * print statistics to file
	 */
	private void printStatistics(){
		if(this.printStats){
			this.output.println(this.expand+" resizes, " + "load factor "+ loadFactor()+
					", "+this.collision+" collisions, "+longestChain() +" longest chain");
			this.output.flush();
		}
		else
			return;
	}

}
