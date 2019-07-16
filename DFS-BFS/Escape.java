// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw4;

/**
 * main method for escape dark room
 * @since 4-19-2016
 * @author PeiranLi
 * @version 1.0
 */
public class Escape {
	public static void main(String[] args)
	{
		DarkRoom d = new DarkRoom();//create a darkRoom
		d.readFromFile(args[0]); //read from file
		d.escapeDarkRoom("Stack"); //escape using stack
		d.clear(); //clear room
		d.escapeDarkRoom("Queue"); //escape using queue
	}
}

