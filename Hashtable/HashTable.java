
//package hw8;

import java.io.*;
import java.util.*;
public class HashTable implements IHashTable {
	private LinkedList<String>[] myList;	
	private File file;
	private PrintWriter output;
	//You will need a HashTable of LinkedLists. 
	//You can either implement your own Node and LinkedList classes as protected classes in this file, 
	//or use Java's LinkedList.
	


	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	
	//You are allowed to add more :)
	
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

	@Override
	public boolean insert(String value) {
		if(value == null)
			throw new NullPointerException();
		if(loadFactor()>(2.0/3)){
			this.rehash();
		}
		int hash = hashValue(value, myList.length);
		if(lookup(value))
			return false;
		else{
			if(myList[hash].size() > 0)
				this.collision++;
			myList[hash].add(value);
			this.nelems++;
		}
		return true;
		
	}

	@Override
	public boolean delete(String value) {
		if(value == null)
			throw new NullPointerException();
		int hash = hashValue(value, myList.length);
		if(!lookup(value))
			return false;
		else{
			for(int i = 0; i < myList[hash].size(); i++){
				if(myList[hash].get(i).equals(value))
					myList[hash].remove(i);
			}
			this.nelems--;
		}
		return true;
	}

	@Override
	public boolean lookup(String value) {
		if(value == null)
			throw new NullPointerException();
		int hash = hashValue(value, myList.length);
		for(int i = 0; i < myList[hash].size();i++){
			if(myList[hash].get(i).equals(value))
				return true;
		}
		return false;
		
	}

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
	
	@Override
	public int getSize() {
		return this.nelems;
	}

	public int getCapacity(){
		return this.myList.length;
	}
	
	private int hashValue(String key, int tableSize){
		int hashValue = 0;
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
	private int longestChain(){
		int longest = 0;
		for(int i = 0; i < myList.length;i++){
			if(longest < myList[i].size() )
				longest = myList[i].size();
		}
		return longest;
	}
	private double loadFactor(){
		double sum = 0;
		for(int i = 0; i < myList.length;i++){
			sum += myList[i].size();
		}
		return sum/myList.length;
	}
	@SuppressWarnings("unchecked")
	private LinkedList<String>[] expand(){
		LinkedList<String>[] newList = new LinkedList[myList.length*2];
		for(int i = 0; i < myList.length*2; i++){
			newList[i] = new LinkedList<String>();
		}
		this.expand+=1;
		return newList;
	}
	private void rehash(){
		LinkedList<String>[] newList = expand();
		printStatistics();
		this.collision = 0;
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
