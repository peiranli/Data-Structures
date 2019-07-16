// *
// NAME: <Peiran Li>  

// ID: <A92036065>
// LOGIN: <cs12smz>
// *

/**
 * Addind C Standard library
 */
#include <stdio.h>
#include <stdlib.h>

/**
 * Adding local header files
 */
#include "Person.h"

/**
 * Main method to make a Person struct, populate it and
 * call helper methods to print Person information.
 * 
 * Arguments:
 *     argc -- Holds the number of command line
 *             arguments passed.
 *     argv -- Holds each command line argument as
 *             char string.
 *
 * Error:
 * If we don't pass in 3 arguments, write an error to stderr
 * and terminate the program
 *
 * Return:
 * EXIT_FAILURE upon hitting an error. EXIT_SUCCESS on successful program run.
 */
int main( int argc, char* argv[] ) {

	//Checks if we pass in 3 arguments
	if(argc != 4 ) {
		fprintf( stderr, "Program only accepts 3 arguments.\n" );
		return EXIT_FAILURE;
	}

	//Make and populate Person struct from command line arguments
	Person myPerson = {argv[1],argv[2],argv[3]};

	//Print Person's initials followed by Person's information
	//initials first
	getInitials(myPerson);
	printf( "Information:\n");
	
		
	//then print Person informaiton
	print(myPerson);
	//Print first and last name followed by whether the Person is an adult or not
	
	if(isAdult(myPerson))
		printf("%s %s is an Adult!",myPerson.firstName,myPerson.lastName);
	else
		printf("%s %s is not an Adult!",myPerson.firstName,myPerson.lastName);
	
	return EXIT_SUCCESS;
}
