package hw7;

public class BSTree<T extends Comparable<? super T>>{

	private int nelems;       //Number of nodes in the trees
	private BSTNode root1;    //Root of first tree
	private BSTNode root2;    //Root of second tree

	 protected class BSTNode{
		 
		T elem;
		BSTNode lChild;
		BSTNode rChild;
		BSTNode outer;

		//TODO 
		//Refer to the table in the write-up.
		//Implement ALL methods specified.
		//Make sure the parameters are in the same order as specified in the write-up.
	}

	//BSTree methods

	 public BSTree() {
		 
		 //TODO
		 
	 }
	 
	public BSTNode getRoot(int treeChoice) throws IllegalArgumentException {
		//TODO
	}
	

	public int getSize() {
		//TODO
	}
	
	public void insert(T firstElem, T secondElem) throws NullPointerException{
		//TODO
	}

	
	public boolean findElem(T elem) throws NullPointerException	 {
		//TODO
	}
	
	public T findMoreInfo(T elem) throws NullPointerException,IllegalArgumentException {
		//TODO
	}

	public void update(T oldData, T newData) throws NullPointerException,IllegalArgumentException{
		//TODO

	}
	
	public void printToArray(T[] elemArray, int treeChoice) {
		//TODO
	}
	
	//EXTRA CREDIT
	//You can remove the following if not used.
	
	public int findHeight(int treeChoice) throws IllegalArgumentException {
		//TODO
	}
	
	public int leafCount(int treeChoice )throws IllegalArgumentException {
		//TODO
	}
	
	public int levelCount(int level, int treeChoice) throws IllegalArgumentException {
		//TODO
		
	}
	
	public void loadData(String filePath) {
		//TODO
	}
	
	public void saveData(String filePath) {
		//TODO
	}
}
