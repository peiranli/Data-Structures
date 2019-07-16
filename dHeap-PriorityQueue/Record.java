// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw6;

/**
 * Record data type that store process, deadline and duration
 * @version 1.0
 * @author PeiranLi
 * @since 5-5-2016
 */
public class Record implements Comparable<Record> {

	//instance variables
	private String process;
	private long deadline; 
	private long duration; 

	// constructor to create a new record
	// given the name of the process,
	// deadline and duration
	Record (String process, long deadline, long duration)
	{
		this.process = process;
		this.deadline = deadline;
		this.duration = duration;
	}

	// constructor to create a new record
	// from the esisting record and new
	// duration
	Record (Record r, long duration)
	{
		this.process=r.process;
		this.deadline=r.deadline;
		this.duration=duration;
	}
	/**
	 * get the duration of process
	 * @return the duration 
	 */
	public long GetDuration()
	{
		return duration;
	}
	
	/**
	 * @return return the string of the information of a process
	 */
	public String toString()
	{
		return process+" with deadline "+deadline+" and duration "+duration;
	}
	/**
	 * return the string of process
	 * @param current_time
	 * @return the process
	 */
	public String toString(long current_time)
	{
		if(current_time > deadline) return process + " (late)";
		return process;
	}
	/**
	 * compare dealine of calling object with input o 
	 */
	@Override
    public int compareTo(Record o) 
	{
		return (int) (this.deadline-o.deadline);
    }
}
