// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * escape from darkRoom class 
 * @since 4-19-2016
 * @author PeiranLi
 * @version 1.0
 */
public class DarkRoom implements DarkRoomInterface {

	//instance variables
	protected char [][] darkRoom;
	protected int numRows=0;
	protected int numCols;

	/**read the darkRoom from room file */
	public void readFromFile(String fname) {

		String line;  //temp string to store input
		BufferedReader inputStrem; //input stream
		StringTokenizer st; //


		try {
			int currentRow = 0;  //initialize currentRow

			//create input stream for the file
			inputStrem = new BufferedReader(new FileReader(fname)); 
			//while has next line
			while ((line = inputStrem.readLine()) != null) {
				//if it is the first row
				if (numRows == 0) {
					st = new StringTokenizer(line); //read the line
					numRows = Integer.parseInt(st.nextToken());//read the numRows
					numCols = Integer.parseInt(st.nextToken()); //read the numCols
					darkRoom = new char[numRows][numCols]; //initialize darkRoom with size
				} else if (line.length() == 1)// if the length of line is 1 quit the while loop
					break;
				else {
					//initialize the darkRoom 
					for (int c = 0; c < numCols; c++) {
						darkRoom[currentRow][c] = line.charAt(c);
					}
					currentRow ++;
				}
			}
		}
		catch (IOException e) {
			System.out.println (e.toString()); //print exception
			System.out.println("Could not find file " + fname); //print error message
		}

	}


	//Helper methods:

	/** Method that returns the Location of "start"*/
	public Location findStart()
	{
		int startRow = 0;
		int startCol = 0;
		for(int i = 0; i < darkRoom.length; i++)
		{
			for(int j = 0; j < darkRoom[i].length; j++)
			{
				if(darkRoom[i][j] == 'S')
				{
					startRow = i;
					startCol = j;
					break;
				}
			}
		}
		return new Location(startRow,startCol);  
	}

	/**Method that checks if the goal was found*/
	public boolean isDoor (Location loc)
	{
		if(darkRoom[loc.getRow()][loc.getColumn()] == 'D')
			return true;
		return false; 
	}


	/** Method that checks if you can move */
	public boolean canMove(Location loc)
	{
		if(darkRoom[loc.getRow()][loc.getColumn()] == '*' 
				|| darkRoom[loc.getRow()][loc.getColumn()] == '@' )
			return false;  
		return true;
	}

	/** Marks explored (visited) positions*/
	public void markVisited (Location loc)
	{
		darkRoom[loc.getRow()][loc.getColumn()] = '.';

	}

	/**counts the number of visited positions */
	public int countVisited()
	{
		int count = 0;
		for(int i = 0 ; i < darkRoom.length; i++)
		{
			for(int j = 0; j < darkRoom[i].length; j++)
			{
				if(darkRoom[i][j] == '.')
					count++;
			}
		}
		return count; 
	}


	/** removes marks from visiting (removes '.')*/
	public void clear()
	{
		for(int i = 0; i<darkRoom.length; i++)
		{
			for(int j = 0; j < darkRoom[i].length; j++)
			{
				if(darkRoom[i][j] == '.')
					darkRoom[i][j] = ' ';
			}
		}
	}

	/**prints your array that represents a room */
	public void printRoom()
	{
		for(int i = 0; i<darkRoom.length; i++)
		{
			for(int j = 0; j < darkRoom[i].length; j++)
			{
				System.out.print(darkRoom[i][j]+" ");
			}
			System.out.println();
		}

	}

	/** 
	 * escape the room using stack and queue
	 * @param choice the way to escape
	 */

	public void escapeDarkRoom(String choice){
		//create a storage
		Stack_QueueInterface<Location> storage;
		ArrayList<Location> check;
		int stepsTaken = 0;
		int positionsLeft = 0;
		boolean found = false;
		//if using stack
		if(choice.equals("Stack"))
			//create stack
			storage = new MyStack<Location> ();
		else
			//create queue
			storage = new MyQueue<Location> ();

		check = new ArrayList<Location>();
		Location start = findStart(); //find start loc
		storage.addElement(start); //push
		check.add(start);
		//while stack is not empty
		while(!storage.isEmpty())
		{
			//push 
			Location curr = storage.removeElement();

			//update 
			positionsLeft = storage.size();
			//if free mark
			if(isFree(curr))
			{
				markVisited(curr);
			}

			//update visited spots
			stepsTaken = countVisited();
			//for each adjacent push if it is free and not the door
			if(isDoor(curr.left()))
			{
				found = true;
				break;
			}
			else if(canMove(curr.left())
					&& isFree(curr.left())
					&& !isInStorage(check,curr.left()))
			{
				storage.addElement(curr.left());
				check.add(curr.left());
				positionsLeft = storage.size();
			}

			if(isDoor(curr.up()))
			{
				found = true;
				break;
			}
			else if(canMove(curr.up())
					&& isFree(curr.up())
					&& !isInStorage(check,curr.up()))
			{
				storage.addElement(curr.up());
				check.add(curr.up());
				positionsLeft = storage.size();
			}

			if(isDoor(curr.right()))
			{
				found = true;
				break;
			}
			else if(canMove(curr.right())
					&& isFree(curr.right())
					&& !isInStorage(check,curr.right()))
			{
				storage.addElement(curr.right());
				check.add(curr.right());
				positionsLeft = storage.size();
			}

			if(isDoor(curr.down()))
			{
				found = true;
				break;
			}
			else if(canMove(curr.down())
					&& isFree(curr.down())
					&& !isInStorage(check,curr.down()))
			{
				storage.addElement(curr.down());
				check.add(curr.down());
				positionsLeft = storage.size();
			}


		}


		if(found)
		{
			//print the statistics
			printGoal(choice,stepsTaken,positionsLeft);
			//print the final room
			printRoom();
		}
		else{
			System.out.println("door not found");
		}

	}

	/**print the statistics */
	public void printGoal(String choice, int stepsTaken, int positionsLeft)
	{
		System.out.println("Goal found (with " + choice + "): It took "
				+ stepsTaken + " explored positions");
		System.out.println("There is (are) " + positionsLeft
				+ " position(s) left to explore in " + choice);

	}
	/**help method to see if location is free */
	private boolean isFree(Location loc)
	{
		if(darkRoom[loc.getRow()][loc.getColumn()] == ' '
				&& darkRoom[loc.getRow()][loc.getColumn()] != 'S')
			return true;
		return false;
	}

	private boolean isInStorage(ArrayList<Location>check,Location loc)
	{
		for(int i  = 0; i < check.size(); i++)
		{
			if(check.get(i).getRow() == loc.getRow() && check.get(i).getColumn() == loc.getColumn())
				return true;
		}
		return false;
	}

}


