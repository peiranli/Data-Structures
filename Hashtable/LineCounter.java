//package hw8;

import java.io.*;
import java.util.*;


public class LineCounter {
	
	public static void printToConsole(String filename, String compareFileName, int percentage) {
		if(!filename.isEmpty())
			System.out.println("\n"+filename+":");
		
		if(!compareFileName.isEmpty())
				System.out.println(percentage+"% of lines are also in "+compareFileName);
	}
	
	public static void main(String[] args) {
		
		if(args.length<1) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		int numArgs = args.length;
		HashTable[] tableList = new HashTable[numArgs];
		
		//Preprocessing: Read every file and create a HashTable
		
		for(int i=0; i<numArgs; i++) {
			tableList[i] = new HashTable(5);
			File file = new File(args[i]);
			try {
				Scanner input = new Scanner(file);
				while(input.hasNextLine()){
					tableList[i].insert(input.nextLine());
					
				}
				System.out.println(tableList[i].getSize());
				input.close();
			} catch (FileNotFoundException e) {
			}
			
		}
		
		//Find similarities across files
		
		for(int i=0; i<numArgs; i++) {
			for(int j = 0; j<numArgs && j != i; j++){
				System.out.println("j:"+j);
				int common = compareHelper(args[i],tableList[j]);
				System.out.println("j:"+j);
				System.out.println(args[i]);
				System.out.println("j:"+j);
				System.out.println(common);
				System.out.println("j:"+j);
				System.out.println(tableList[i].getSize());
				System.out.println("j:"+j);
				int percentage = common*100/countLines(args[i]);
				System.out.println("j:"+j);
				printToConsole(args[i], args[j], percentage);
				
			}
		}
	}
	
	
	private static int compareHelper(String file1,HashTable table2){
		int same = 0;
		File file = new File(file1);
		try{
			Scanner input = new Scanner(file);
			while(input.hasNextLine()){
				if(table2.lookup(input.nextLine())){
					same++;
					System.out.println(same);
				}
			}
			input.close();
		}catch(FileNotFoundException e){
			
		}
		return same;
	}
	
	private static int countLines(String fileName){
		int count = 0;
		File file = new File(fileName);
		try{
			Scanner input = new Scanner(file);
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
