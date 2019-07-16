// *
// NAME: <Peiran Li>   

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw8;

import java.io.*;
import java.util.*;
/**
 * class to find anagrams of a word
 * @author PeiranLi
 * @version 1.0
 * @since 5-22-2016
 */
public class HW8_ExtraCredit {
	/**
	 * inner string pair class
	 * contains original word and the key
	 *
	 */
	protected static class StringPair{
		//instance variables
		private String original; 
		private String key;
		/**
		 * constructor
		 * @param original original word
		 * @param key the key of the original word
		 */
		public StringPair(String original, String key){
			this.original = original;
			this.key = key;
		}
		/**
		 * getter of key
		 * @return the key
		 */
		public String getKey(){
			return this.key;
		}
		/**
		 * getter of original word
		 * @return original word
		 */
		public String getOrigin(){
			return this.original;
		}
	}
	/**
	 * inner hash table class
	 */
	protected static class HashTable  {
		//array of linked list
		private LinkedList<StringPair>[] myList;	
		private int nelems;  //Number of element stored in the hash table
		
		
		/**
		 * Constructor for hash table
		 * @param Initial size of the hash table
		 */
		@SuppressWarnings("unchecked")
		public HashTable(int size) {
			
			//Initialize
			this.nelems = 0;
			myList = new LinkedList[size];
			//initialize array
			for(int i = 0; i< size; i++){
				myList[i] = new LinkedList<StringPair>();
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
		public boolean insert(String original, String key) {
			//if original word is null throw NullPointerException
			if(original == null)
				throw new NullPointerException();
			//if load factor is greater than 2/3, double size and rehash
			if(loadFactor()>(2.0/3)){
				this.rehash();
			}
			//hash value
			int hash = hashValue(key, myList.length);
			//if original already exists, return false
			if(lookup(original,key))
				return false;
			//else add it into hash table
			else{
				myList[hash].add(new StringPair(original,key));
				this.nelems++;
			}
			return true;
			
		}

	
		/**
		 * Use the hash table to determine where i is, 
		 * delete it from the hash table. 
		 *  (an item cannot be deleted if it does not exist in the hash table).
		 *  @param value the element to delete
		 *  @throws Throw a NullPointerException if a null value is passed. 
		 *  @return should return true if the item is deleted, 
		 *  false if it cannot be deleted 
		 */
		public boolean delete(String value,String key) {
			//if the value is null, throw NullPointerException
			if(value == null)
				throw new NullPointerException();
			//get the hash key
			int hash = hashValue(key, myList.length);
			//if value does not exist, return false
			if(!lookup(value,key))
				return false;
			//else find it and delete it
			else{
				for(int i = 0; i < myList[hash].size(); i++){
					if(myList[hash].get(i).getOrigin().equals(value))
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
		public boolean lookup(String value,String key) {
			//if the value is null, throw NullPointerException
			if(value == null)
				throw new NullPointerException();
			//get hash key
			int hash = hashValue(key, myList.length);
			//go through and find it
			for(int i = 0; i < myList[hash].size();i++){
				if(myList[hash].get(i).getOrigin().equals(value))
					return true;
			}
			return false;
			
		}

		/**
		 * getter of linked list at given key
		 * @param key the bucket of hash table
		 * @return the linked list at given key
		 */
		public LinkedList<StringPair> getList(String key){
			int hashvalue = this.hashValue(key, myList.length);
			return myList[hashvalue];
		}
		
		
		/**
		 * @return the number of elements currently stored in the hash table
		 */
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
			//add all the chars up
			for(int i = 0; i < key.length(); i++){
				int letter = key.charAt(i);
				hashValue = (hashValue*27+letter)%tableSize;
			}
			return hashValue;
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
		private LinkedList<StringPair>[] expand(){
			//double the size
			LinkedList<StringPair>[] newList = new LinkedList[myList.length*2];
			for(int i = 0; i < myList.length*2; i++){
				newList[i] = new LinkedList<StringPair>();
			}
			return newList;
		}
		/**
		 * rehash all the elements
		 */
		private void rehash(){
			LinkedList<StringPair>[] newList = expand();
			//rehash all the elements
			for(int i = 0; i < this.myList.length;i++){
				for(int j = 0; j < myList[i].size(); j++){
					//get key
					String key = myList[i].get(j).getKey();
					String origin = myList[i].get(j).getOrigin();//get original word
					int newHash = hashValue(key,newList.length);
					newList[newHash].add(new StringPair(origin,key));
				}
			}
			this.myList = newList;
			

		}
		
		

	}

	
	/**
	 * help method to sort a string
	 * @param s the string to sort
	 * @return
	 */
	private static String sortString(String s){
		char[] array = s.toCharArray();
		int small;
		//selection sort
		for (int i = 0; i < array.length; i++) {
			   small = i;
			   for (int j = i + 1; j < array.length; ++j) {
			      //if index at j is smaller than small
				   //change small to j
			      if (array[j] < array[small]) {
			         small = j;
			      }
			   }
			   //swap small and i
			   char temp = array[i];
			   array[i] = array[small];
			   array[small] = temp;
		}
		String newS = new String(array);
		return newS;
		
	}
	/**
	 * main method
	 * @param args command-line args
	 */
	public static void main(String[] args){
		//if the args length is not two 
		//invalid input
		if(args.length != 2){
			System.out.println("Invalid input args");
			return;
		}
		//create a hash table
		HashTable dic = new HashTable(997);
		File file = new File(args[0]);//create file
		try{
			Scanner input = new Scanner(file);//create input stream
			//store dictionary to hash table
			while(input.hasNext()){
				String original = input.next();
				String key = sortString(original);
				dic.insert(original,key);
			}
			input.close();
		}catch(FileNotFoundException ex){}
		
		String toFind = args[1];//get the word to find
		String key = sortString(toFind);//calculate key
		//get the linked list at given key
		LinkedList<StringPair> anagrams = dic.getList(key);
		boolean found = false;
		//find and print all the anagrams
		for(int i = 0; i < anagrams.size();i++){
			if(key.equals(anagrams.get(i).getKey())){
				found = true;
				System.out.println(anagrams.get(i).getOrigin());
			}
		}
		//if not found
		if(!found)
			System.out.println("No anagram found");
		
	}
	
}
