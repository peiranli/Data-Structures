// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

package hw6;

/**
 * sorting algorithms
 * @since 5-5-2016
 * @author PeiranLi
 * @version 1.0
 */
public class SortComplexity {
	
	/**
	 * heap sort
	 * @param list input list
	 */
	public static void heapSort(int[] list)
	{
		dHeap<Integer> heapList = new dHeap<Integer>(list.length,true);
		for(int i = 0 ; i < list.length; i++){
			heapList.add(list[i]);
		}
		int[] newList = new int[list.length];
		for(int i = 0 ; i < list.length; i++){
			newList[list.length-1-i] = heapList.remove();
		}
	}

	/**
	 * selection sort
	 * @param list input list
	 */
	public static void selectionSort(int list[])
	{
		for (int j=list.length-1; j>0; j--)
		{
			int maxpos = 0;
			for (int k=1; k<=j; k++)
			{
				if (list[k]>list[maxpos])
				{
					maxpos = k;
				}
			}
			if (j != maxpos)    // Only move if we must
			{
				int temp = list[j];
				list[j] = list[maxpos];
				list[maxpos] = temp;
			}
		}
	}

	/**
	 * insertion sort
	 * @param list input list
	 */
	public static void insertionSort(int list[])
	{
		for (int j=1; j<list.length; j++)
		{
			int temp = list[j];
			int k = j;
			while( k > 0 && list[k-1]>temp )
			{
				list[k] = list[k-1];
				k--;
			}
			list[k] = temp; 
		}
	}

}
