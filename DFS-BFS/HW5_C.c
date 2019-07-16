// *
// NAME: <Peiran Li> 

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

// function declaration
// Returns the index of max element
int max_elem_location(int[], int); 

int main() {
	int location; //you  will need more variables
	int size;
	int* array;
	printf("Input number of elements in array\n");
	//use scanf to get an input (size of the array) from a user
	scanf("%d",&size);
	array = (int*)malloc(sizeof(int)*size);
	printf("Enter %d integers\n", size);

	//Now prompt for the numbers and add them to an array
	int i;
	int temp;
	for(i = 0; i < size; i++)
	{
		scanf("%d",&temp);
		array[i] = temp;
	}

	location = max_elem_location(array, size);
	

	printf("Max element location = %d and value = %d.\n", location+1, array[location]);
	return 0;
}

// body of the function
// Note it returns the location of the max element 
int max_elem_location(int a[], int n) {
	int max = a[0];
	int i;
	int index = 0;
	for(i = 1; i < n; i++)
	{
		if(a[i] > a[index])
		{
			max = a[i];
			index = i;
		}
	}
	return index;
}
