// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *


package hw7;


import java.util.ArrayList;

/**
 * generic binary search tree class
 * @version 1.0
 * @author Peiran Li
 * @since 5-12-2016
 * @param <T>
 */
public class BSTree<T extends Comparable<? super T>>{

	//instance variables
	private int nelems;       //Number of nodes in the trees
	private BSTNode root1;    //Root of first tree
	private BSTNode root2;    //Root of second tree

	/**
	 * inner class BSTNode
	 */
	protected class BSTNode{
		//instance variables of BSTNode
		T elem;
		BSTNode lChild;
		BSTNode rChild;
		BSTNode outer;

		/**
		 * BSTNode constructor
		 * @param lChild left child
		 * @param rChild right child
		 * @param outer outer child
		 * @param elem the data
		 */
		public BSTNode(BSTNode lChild, BSTNode rChild, BSTNode outer, T elem){
			this.elem = elem;
			this.lChild = lChild;
			this.rChild = rChild;
			this.outer = outer;
		}
		/**
		 * Returns a value stored in a given node
		 * @return value stored in a given node
		 */
		public T getElem(){
			return this.elem;
		}	

		/**
		 * getter of left child
		 * @return the left child of a given node
		 */
		public BSTNode getLChild(){
			return this.lChild;
		}
		/**
		 * getter of right child
		 * @return the right child of a given node
		 */
		public BSTNode getRChild(){
			return this.rChild;
		}

		/**
		 * getter of outerNode
		 * @return  the outer node (the corresponding node in the other BST) of a given node
		 */
		public BSTNode getOuterNode(){
			return this.outer;
		}

		/**
		 * Sets the elem of the node to newElem.
		 * @param newElem the new data
		 */
		public void setElem(T newElem){
			this.elem = newElem;
		}

		/**
		 * Sets newLNode as the left child of a node
		 * @param newLChild set new left child
		 */
		public void setLChild(BSTNode newLChild){
			this.lChild = newLChild;
		}

		/**
		 * Sets newLNode as the right child of a node
		 * @param newRChild set new right child
		 */
		public void setRChild(BSTNode newRChild){
			this.rChild = newRChild;
		}

		/**
		 * Sets newOuterNode as the outer link  of a node
		 * @param newOuterNode set new outer child
		 */
		public void setOuterNode(BSTNode newOuterNode){
			this.outer = newOuterNode;
		}	
	}

	//BSTree methods

	/**
	 * non-arguments constructor
	 * Initializes roots to null and nelems to 0.
	 */
	public BSTree() {
		//initialize
		this.nelems = 0;
		this.root1 = null;
		this.root2 = null;		

	}

	/**
	 * Public getter for the root.
	 *	treeChoice =1 Return root of first tree
	 * treeChoice =2  Return root of second tree
	 *	Throws IllegalArgumentException for an invalid treeChoice
	 * @param treeChoice determine root1 or root2
	 * @return
	 * @throws IllegalArgumentException
	 */
	public BSTNode getRoot(int treeChoice) throws IllegalArgumentException {
		if(treeChoice == 1)
			return this.root1;
		else if(treeChoice == 2)
			return this.root2;
		else
			throw new IllegalArgumentException();
	}


	/**
	 * the count of nodes in the trees
	 * @return the number of nodes in the trees.
	 */
	public int getSize() {
		return this.nelems;
	}

	/**
	 * This method will take the two arguments 
	 * and adds them to the corresponding trees 
	 * (first argument for the first tree, second is for the second tree). 
	 * If either element is null, the method will throw a NullPointerException. 
	 * @param firstElem the elem to insert in first tree
	 * @param secondElem the elem to insert in second tree
	 * @throws NullPointerException
	 */
	public void insert(T firstElem, T secondElem) throws NullPointerException{
		if(firstElem == null || secondElem == null)
			throw new NullPointerException();
		//create two nodes with given elements
		BSTNode first = new BSTNode(null, null,null,firstElem);
		BSTNode second = new BSTNode(null,null,null,secondElem);
		//set outer nodes
		first.setOuterNode(second);
		second.setOuterNode(first);
		if(root1 == null)
			root1 = first;
		else
			add(root1,first);

		if(root2 == null)
			root2 = second;
		else
			add(root2,second);

		this.nelems+=2;
	}


	/**
	 * This method looks for a value given by the parameter elem.   
	 * If the parameter is null the method will throw a NullPointerException
	 * @param elem data to find
	 * @return If the value is found in either tree, the method will return "true". 
	 * If the value is not found in both trees, it will return false.
	 * @throws NullPointerException
	 */
	public boolean findElem(T elem) throws NullPointerException{
		if(!canFind(root1,elem) && !canFind(root2,elem))
			return false;
		return true;
	}
	/**
	 * Given an attribute ‘elem’ as a parameter, 
	 * find and return the corresponding attribute from another tree.
	 * Throws NullPointerException if attribute is null.
	 * Throws IllegalArgumentException if attribute is not found in any of the trees.
	 * @param elem given elem 
	 * @return data in another tree of given elem
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public T findMoreInfo(T elem) throws NullPointerException,IllegalArgumentException {
		if(elem == null)
			throw new NullPointerException();
		//find in two trees
		BSTNode temp1 = find(root1,elem);
		BSTNode temp2 = find(root2,elem);
		if(temp1 == null && temp2 == null)
			throw new IllegalArgumentException();
		else{
			if(temp1 != null)
				return temp1.getOuterNode().getElem();
			else
				return temp2.getOuterNode().getElem();
		}


	}

	/**
	 * Given an attribute ‘oldData’, find and replace it with ‘newData’
	 * Throws NullPointerException if any parameter is null.
	 * Throws IllegalArgumentException if oldData is not found in any of the trees.
	 * @param oldData data to find and replace
	 * @param newData data to replace the oldData
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public void update(T oldData, T newData) 
			throws NullPointerException,IllegalArgumentException{
		if(oldData == null || newData == null)
			throw new NullPointerException();
		if(!findElem(oldData))
			throw new IllegalArgumentException();
		if(canFind(root1,oldData)){
			//store data
			T data = findMoreInfo(oldData);
			//find outer node
			BSTNode another = find(root2,data);
			//find the old node
			BSTNode toDelete = find(root1,oldData);
			delete(root1,toDelete);
			BSTNode toAdd = new BSTNode(null,null,another,newData);
			add(root1,toAdd);
			another.setOuterNode(toAdd);
		}
		else if(canFind(root2,oldData)){
			//store data
			T data = findMoreInfo(oldData);
			//find outer node
			BSTNode another = find(root1,data);
			//find the old node
			BSTNode toDelete = find(root2,oldData);
			delete(root2,toDelete);
			BSTNode toAdd = new BSTNode(null,null,another,newData);
			add(root2,toAdd);
			another.setOuterNode(toAdd);
		}

	}
	/**
	 * Prints the tree, using INORDER traversal, one entry per line, \
	 * and also populate an array of values in INORDER. 
	 * If treeChoice is set to 1, use the first tree for traversal and for every value,
	 *  add the value from the second tree after it.
	 * If treeChoice is set to 2, use the second tree for traversal and and for every value, 
	 * append the value from the first tree after it.
	 * If the tree is empty, throws a NullPointerException
	 * Throws IllegalArgumentException for an invalid treeChoice

	 * @param elemArray
	 * @param treeChoice
	 */
	public void printToArray(T[] elemArray, int treeChoice) {
		ArrayList<T> elemList = new ArrayList<T>();
		if(treeChoice == 1)
			inOrderTraverse(elemList,root1);
		if(treeChoice == 2)
			inOrderTraverse(elemList,root2);
		//copy from arraylist to array
		for(int i = 0; i < elemList.size(); i++){
			elemArray[i] = elemList.get(i);
		}
	}



	/**
	 * help method to add a node to a given root
	 * @param n the root to add
	 * @param toAdd the node to add
	 */
	private void add(BSTNode n,BSTNode toAdd){
		//compare first
		int compare = toAdd.getElem().compareTo(n.getElem());
		BSTNode cur = n;
		if(compare == 0)
			return;
		else{
			if(compare < 0){
				//if left child is null, set to it
				if(cur.getLChild() == null)
					cur.setLChild(toAdd);
				else
					add(cur.getLChild(),toAdd);
			}
			else if(compare > 0){
				//if right child is null, set to it
				if(cur.getRChild() == null)
					cur.setRChild(toAdd);
				else
					add(cur.getRChild(),toAdd);
			}
		}	

	}

	/**
	 * help method to delete a node
	 * @param root the root that the node to be deleted located
	 * @param toDelete the node to delete
	 */
	private void delete(BSTNode root,BSTNode toDelete){
		//leaf node
		if(!hasChild(toDelete)){
			//if toDelete is not root
			if(toDelete != root){
				//find parent
				BSTNode parent = findParent(root,toDelete);
				//assign which points to delete to null
				if(parent.getRChild() == toDelete)
					parent.setRChild(null);
				else if(parent.getLChild() == toDelete)
					parent.setLChild(null);
				toDelete = null;
			}
			else
				root = null;
		}
		//two children
		else if(toDelete.getLChild() != null && toDelete.getRChild()!=null){
			//find successor
			BSTNode successor = findSuccessor(toDelete);
			//if right child is null, set successor's data to delete
			//set successor to null
			if(successor.getRChild() == null){
				toDelete.setElem(successor.getElem());
				successor = null;
			}
			//if right child is not null, let successor's parent point to child
			else{
				BSTNode parent = findParent(root,successor);
				parent.setLChild(successor.getRChild());
				toDelete.setElem(successor.getElem());
				successor = null;
			}
		}
		//single child
		else{
			//if toDelete is root
			if(toDelete == root){
				//assign root to whichever child
				if(toDelete.getLChild() == null){
					root = toDelete.getRChild();
					toDelete = null;
				}
				else if(toDelete.getRChild() == null){
					root = toDelete.getLChild();
					toDelete = null;
				}
			}
			//if not root
			else{
				//if left child is null assign parent to right child
				if(toDelete.getLChild() == null){
					BSTNode parent = findParent(root,toDelete);
					if(parent.getLChild() == toDelete){
						parent.setLChild(toDelete.getRChild());
						toDelete = null;
					}
					else{
						parent.setRChild(toDelete.getRChild());
						toDelete = null;
					}
				}
				//if right child is null assign parent to left child
				else if(toDelete.getRChild() == null){
					BSTNode parent = findParent(root,toDelete);
					if(parent.getLChild() == toDelete){
						parent.setLChild(toDelete.getLChild());
						toDelete = null;
					}
					else{
						parent.setRChild(toDelete.getLChild());
						toDelete = null;
					}
				}
			}
		}	


	}


	/**
	 * help method to determine whether a data can be find
	 * @param n root of the tree where the elem can be find
	 * @param elem data to find
	 * @return true if can find the elem and false if the elem cannot be found
	 */
	private boolean canFind(BSTNode n, T elem){
		//base case
		if(n == null)
			return false;
		if(n.getElem().compareTo(elem) == 0)
			return true;
		else{
			//recursion
			if(elem.compareTo(n.getElem()) < 0)
				return canFind(n.getLChild(),elem);
			else
				return canFind(n.getRChild(),elem);
		}

	}

	/**
	 * help method to find a node with given elem
	 * @param n root of the tree
	 * @param elem to data to find
	 * @return the node that contains the given elem
	 */
	private BSTNode find(BSTNode n, T elem){
		while(n != null){
			int value = elem.compareTo(n.getElem());
			if(value == 0)
				return n;
			else 
				if(value < 0)
					n = n.getLChild();
				else
					n = n.getRChild();

		}
		return null;
	}

	/**
	 * find the successor of given node
	 * @param n given node
	 * @return the successor
	 */
	private BSTNode findSuccessor(BSTNode n){
		//base case
		if(!hasChild(n))
			return n;
		else{
			if(n.getLChild() != null)
				return findSuccessor(n.getLChild());
			else
				return n;
		}
	}
	/**
	 * inorder traverse the tree
	 * @param array store elems in the array
	 * @param n node to traverse
	 */
	private void inOrderTraverse(ArrayList<T> array,BSTNode n){
		inOrder(array,n);
	}

	/**
	 * inorder traverse helper
	 * @param array store elems in the array
	 * @param n node to traverse
	 */
	private void inOrder(ArrayList<T> array,BSTNode n){
		if(n != null){
			inOrder(array,n.getLChild());
			System.out.println(n.getElem()+" "+n.getOuterNode().getElem());
			array.add(n.getElem());
			array.add(n.getOuterNode().getElem());
			inOrder(array,n.getRChild());
		}
	}

	/**
	 * determine whether given node has child
	 * @param n given node 
	 * @return true if has child false if not
	 */
	private boolean hasChild(BSTNode n){
		if(n.getLChild() == null && n.getRChild() == null)
			return false;
		return true;
	}
	/**
	 * the parent node of given node
	 * @param n root to find the node
	 * @param toFind the node to find
	 * @return parent node of given node
	 */
	private BSTNode findParent(BSTNode n,BSTNode toFind){
		//compare
		int compare = toFind.getElem().compareTo(n.getElem());
		//base case
		if(compare == 0)
			return null;
		//recursion
		else{
			//if less than zero find left
			if(compare < 0){
				if(n.getLChild() == toFind)
					return n;
				else
					return findParent(n.getLChild(),toFind);
			}
			//else find right
			else{
				if(n.getRChild() == toFind)
					return n;
				else
					return findParent(n.getRChild(),toFind);
			}
		}
	}
}
