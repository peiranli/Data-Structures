// *
// NAME: <Peiran Li>   

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw8;

import java.io.*;
import java.util.*;

/**
 * counter the common lines among files
 * @version 1.0
 * @author PeiranLi
 * @since 5-19-2016
 */
public class LineCounter {
	/**
	 * print the results to console
	 * @param filename the file to be compared
	 * @param compareFileName the file to compare
	 * @param percentage the percentage of filename in compareFileName
	 */
	public static void printToConsole(String filename, String compareFileName, int percentage) {
		if(!filename.isEmpty())
			System.out.println("\n"+filename+":");
		
		if(!compareFileName.isEmpty())
				System.out.println(percentage+"% of lines are also in "+compareFileName);
	}
	/**
	 * main method
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		//if count of arguments is less than one
		if(args.length<1) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		int numArgs = args.length;//get the number of args
		HashTable[] tableList = new HashTable[numArgs];
		
		//Preprocessing: Read every file and create a HashTable
		
		for(int i=0; i<numArgs; i++) {
			tableList[i] = new HashTable(5);
			File file = new File(args[i]);//create file for ith filename
			try {
				Scanner input = new Scanner(file);
				//insert all the lines in to hash table
				while(input.hasNextLine()){
					tableList[i].insert(input.nextLine());
				}
				input.close();
			} catch (FileNotFoundException e) {
			}
			
		}
		
		//Find similarities across files
		int time = 0;
		for(int i=0; i<numArgs; i++) {
			time = 0;
			//compare one file
			for(int j = 0; j<numArgs; j++){
				if(j!=i){
					int common = compareHelper(args[i],tableList[j]);
					//calculate percentage
					int percentage = common*100/countLines(args[i]);
					if(time == 0)
						printToConsole(args[i], args[j], percentage);
					else
						printToConsole("", args[j], percentage);
				time++;
				}
			}
		}
	}
	
	/**
	 * help method to compare two files
	 * @param filename the file to be compared 
	 * @param table the hash table of the file to compare
	 * @return
	 */
	private static int compareHelper(String filename,HashTable table){
		int same = 0;
		File file = new File(filename);//create file
		try{
			Scanner input = new Scanner(file);//input stream
			while(input.hasNextLine()){
				//look up every line in hash table
				if(table.lookup(input.nextLine())){
					same++;
				}
			}
			input.close();
		}catch(FileNotFoundException e){
			
		}
		return same;
	}
	/**
	 * help method to count lines in a file
	 * @param fileName the file to count
	 * @return the number of lines in this file
	 */
	private static int countLines(String fileName){
		int count = 0;
		File file = new File(fileName);//create a file
		try{
			Scanner input = new Scanner(file);//create input stream
			//count all the lines
			while(input.hasNextLine()){
				input.nextLine();
				count++;
			}
			input.close();
		}catch(FileNotFoundException e){
			
		}
		return count;
	}

}
