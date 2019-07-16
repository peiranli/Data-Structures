package hw7;

import static org.junit.Assert.*; 

import org.junit.Test;
import org.junit.Before;

public class BSTree_ECTester {
	private BSTree_EC<String> myTree = new BSTree_EC<String>();
	private BSTree_EC<String> emptyTree = new BSTree_EC<String>();
	@Before
	public void setUp(){
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
	
	@Test
	public void testFindHeight(){
		assertEquals("height root1",5,myTree.findHeight(1));
		assertEquals("height root1",4,myTree.findHeight(2));
	}
	
	@Test
	public void testLeafCount(){
		assertEquals("leaves",2,myTree.leafCount(1));
		assertEquals("leaves",3,myTree.leafCount(2));
	}
	
	@Test
	public void testLevelCount(){
		assertEquals("level count",2,myTree.levelCount(3,1));
		assertEquals("level count",3,myTree.levelCount(3,2));
	}
	
	@Test
	public void testLoadData(){
		emptyTree.loadData("sample.txt");
		String[] elemArray = new String[emptyTree.getSize()];
		emptyTree.printToArray(elemArray, 1);
		System.out.println();
		String[] elemArray2 = new String[myTree.getSize()];
		myTree.printToArray(elemArray2, 1);
	}
	
	@Test
	public void testSaveData(){
		myTree.saveData("result.txt");
		
	}
}
