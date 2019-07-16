// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw7;

import static org.junit.Assert.*;  

import org.junit.Test;
import org.junit.Before;
/**
 * tester of BSTree
 * @author PeiranLi
 * @version 1.0
 * @since 5-12-2016
 */
public class BSTreeTester {
	
	private BSTree<String> myTree = new BSTree<String>();
	
	/**initialize the test */
	@Before
	public void setUp(){
		//initialize the tree
		myTree.insert("Richard","Tran");
		myTree.insert("Pooja", "Bhat");
		myTree.insert("Zach", "Meyer");
		myTree.insert("Marina", "Langlois");
		myTree.insert("Thiago", "Marback");
		myTree.insert("Siwei", "Xu");
		myTree.insert("Annie", "Xiao");
		myTree.insert("Don", "Vo");
		myTree.insert("Haoran", "Sun");

		
	}

	/**test insert method */
	@Test
	public void testInsert(){
		System.out.println("testInsert\n");
		myTree.insert("Ziang", "Jing");
		String[] array = new String[myTree.getSize()];
		myTree.printToArray(array, 2);
		System.out.println();
		
	}
	/** test find element method*/
	@Test
	public void testFindElem() {
		assertTrue(myTree.findElem("Xu"));
		assertTrue(myTree.findElem("Pooja"));
		assertFalse(myTree.findElem("Peiran"));
		assertTrue(myTree.findElem("Marina"));
		assertTrue(myTree.findElem("Haoran"));
	}
	/** test more info method*/
	@Test
	public void testFindMoreInfo() {
		String more = myTree.findMoreInfo("Marina");
		assertEquals("more info","Langlois",more);
		
	}
	/**test print to array method */
	@Test
	public void testPrintToArray() {
		System.out.println("testPrintToArray\n");
		String[] array = new String[myTree.getSize()];
		myTree.printToArray(array, 1);
		assertEquals("first element","Annie",array[0]);
		assertEquals("second element","Xiao",array[1]);
		assertEquals("third element","Don",array[2]);
		assertEquals("fourth element","Vo",array[3]);
		assertEquals("fifth element","Haoran",array[4]);
		assertEquals("sixth element","Sun",array[5]);
		assertEquals("seventh element","Marina",array[6]);
		assertEquals("eighth element","Langlois",array[7]);
		assertEquals("ninth element","Pooja",array[8]);
		assertEquals("tenth element","Bhat",array[9]);
		assertEquals("eleventh element","Richard",array[10]);
		assertEquals("twelfth element","Tran",array[11]);
		assertEquals("thirteenth element","Siwei",array[12]);
		assertEquals("fourteenth element","Xu",array[13]);
		assertEquals("fifteenth element","Thiago",array[14]);	
		assertEquals("sixteenth element","Marback",array[15]);
		assertEquals("seventeenth element","Zach",array[16]);
		assertEquals("eighteenth element","Meyer",array[17]);
		System.out.println("\ntestPrintToArray\n");
		myTree.printToArray(array, 2);
	}

	/**test update */
	@Test
	public void testUpdate(){
		assertTrue(myTree.findElem("Marina"));
		myTree.update("Marina","Malina");
		assertTrue(myTree.findElem("Malina"));
		assertFalse(myTree.findElem("Marina"));
		String more = myTree.findMoreInfo("Langlois");
		assertEquals("first name after update","Malina",more);
		
		assertTrue(myTree.findElem("Vo"));
		myTree.update("Vo","Nguyen");
		assertTrue(myTree.findElem("Nguyen"));
		assertFalse(myTree.findElem("Vo"));
		String more2 = myTree.findMoreInfo("Don");
		assertEquals("last name after update","Nguyen",more2);
		
	}
}
