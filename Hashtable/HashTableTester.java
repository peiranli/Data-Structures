package hw8;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

public class HashTableTester {
	private HashTable myTable = new HashTable(5,"records.txt");
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
	}

	@Test
	public void testInsert() {
		System.out.println(myTable.getSize());
		System.out.println(myTable.getCapacity());
		myTable.printTable();
		myTable.insert("ricardo");
		myTable.printTable();
		myTable.insert("luke");
		myTable.printTable();
		assertFalse(myTable.insert("luke"));
		
	}
	
	@Test
	public void testDelete(){
		myTable.printTable();
		myTable.delete("andy");
		myTable.printTable();
		myTable.delete("brian");
		myTable.printTable();
	}
	
	@Test
	public void testLookUp(){
		assertTrue(myTable.lookup("brian"));
		assertFalse(myTable.lookup("luke"));
	}
	
	
	

}
