// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw6;
import java.io.*;
import java.util.Scanner;

/**
 * use priority queue to deal with process
 * @version 1.0
 * @author PeiranLi
 * @since 5-5-2016
 */
public class EDF {
	
	/**
	 * print the records
	 * @param choice print mode
	 * @param current_time current time 
	 * @param r process to be printed
	 */
	public static void print(int choice,long current_time, Record r)
	{
		if(choice==1)
			System.out.println( current_time + ": adding " +
	                r.toString() );
		else if(choice==2)
			 System.out.println( current_time + ": busy with " +
	                 r.toString() );
		else
			System.out.println( current_time +  ": done with " +
	                r.toString( current_time ) );
	}

	//main method
	public static void main(String[] args) {
		//if the input arguments is not one
		//print error and exit
		if(args.length != 1)
		{
			 System.err.println("Incorrect number of arguments: "+args.length);
			 System.err.println("java hw6.EDF <input-file>");
			 System.exit(1); 
		}
		//create a file
		File file = new File(args[0]);
		//create a priority queue
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time=0;
		try{
			//create input stream of the file
			Scanner input = new Scanner(file);
			while(input.hasNext())
			{
				String s = input.next();
				if(s.equals("schedule")) //when the process is schedule
				{
					//create a record
					Record newMission = new Record(input.next()
							,input.nextLong(),input.nextLong());
					queue.add(newMission); //add to queue
					print(1,current_time,newMission); //print
				}
				else if(s.equals("run")) //when the process is run
				{
					//get the run time
					Long time = input.nextLong();
					//when have time
					while(current_time < time)
					{
						//poll a mission
						Record mission = queue.poll();
						print(2,current_time,mission);
						//if have enough time after mission
						if(current_time + mission.GetDuration() < time)
						{
							current_time += mission.GetDuration();
							print(0,current_time,mission);
						}
						else
						{
							//create a remaining mission
							Record remain = new Record(mission,
									mission.GetDuration()-(time-current_time));
							current_time = time;
							//add the queue
							queue.add(remain);
							print(1,current_time,remain);
						}
					}
				}
			}
			input.close(); //close input stream
		}
		catch(FileNotFoundException e) //catch the exception
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);
		
	}

}
