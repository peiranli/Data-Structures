// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *



package hw4;
/**
 * 
 * @version 1.0
 * @since 4-19-2016
 */
public class Location {

	//instance variables
	protected int row;
	protected int column;

	/**
	 * initialize row and column
	 * @param currRow the row
	 * @param currCol the column
	 */
	public Location(int currRow, int currCol) {
		row = currRow;
		column = currCol;
	}

	/**
	 * getter for row
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * getter for column
	 * @return column
	 */
	public int getColumn() {
		return column;
	}


	/* LEFT, UP, RIGHT, DOWN */
	/**
	 * 
	 * @return left location 
	 */
	public Location left() {
		return new Location(row,column-1);
	}
	/**
	 * 
	 * @return up location
	 */
	public Location up() {
		return new Location(row-1,column);
	}
	/**
	 * 
	 * @return right location
	 */
	public Location right() {
		return new Location(row,column+1);

	}
	/**
	 * 
	 * @return down location
	 */
	public Location down() {
		return new Location(row+1,column);
	}



}
