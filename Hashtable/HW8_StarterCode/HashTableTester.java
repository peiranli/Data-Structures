// *
// NAME: <Peiran Li>   

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw8;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;
/**
 * tester for my hash table
 * @version 1.0
 * @author PeiranLi
 * @since 5-19-2016
 */
public class HashTableTester {
	private HashTable myTable = new HashTable(5,"records.txt");
	/**
	 * set up for every test
	 * initialize hash table
	 */
	@Before
	public void setUp(){
		myTable.insert("brian");
		myTable.insert("andy");
		myTable.insert("boxi");
		myTable.insert("torreto");
		myTable.insert("rogers");
		myTable.insert("bruce");
		myTable.insert("ricardo");
		myTable.insert("banes");
		myTable.insert("bear");
		myTable.insert("wolf");
		myTable.insert("chicken");
		myTable.insert("dog");
		myTable.insert("tiger");
		myTable.insert("kangaroo");
		myTable.insert("panda");
	}

	/**test insert method */
	@Test
	public void testInsert() {
		assertEquals(myTable.getSize(),15);
		myTable.insert("ricardo");
		assertEquals(myTable.getSize(),15);
		myTable.insert("luke");
		assertEquals(myTable.getSize(),16);
		assertFalse(myTable.insert("luke"));
		
	}
	/**test delete method */
	@Test
	public void testDelete(){
		myTable.delete("andy");
		assertFalse(myTable.lookup("andy"));
		myTable.delete("brian");
		assertFalse(myTable.lookup("brian"));
		assertFalse(myTable.delete("chick"));
	}
	
	/**test look up method */
	@Test
	public void testLookUp(){
		assertTrue(myTable.lookup("brian"));
		assertFalse(myTable.lookup("luke"));
		myTable.insert("luke");
		assertTrue(myTable.lookup("luke"));
	}
	/**test get size method */
	@Test
	public void testGetSize(){
		assertEquals(myTable.getSize(),15);
		myTable.delete("chicken");
		assertEquals(myTable.getSize(),14);
		
	}
	

}
