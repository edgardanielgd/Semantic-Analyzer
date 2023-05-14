// Translation from Small Basic to Javascript
// 3rd Practice (Semmantic Analyzer)
// Created by:
// Miguel Angel Puentes Cespedes
// Jhonatan Steven Rodriguez Ibañez
// Edgar Daniel Gonzalez Diaz

/* Utility Functions and Classes */
function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

// Useful class for simulating Small Basic's Stacks
class Stack {
	// There will be some stack objects which must be saved
	// and registered on execution time
	// Each one will also be identified by its related name
	static stacks = {};

	// Function for pushing data into a Stack
	static pushStack(stackName, element) {
		if( this.stacks[stackName] == undefined ) {
			this.stacks[stackName] = new Stack();
		}
		this.stacks[stackName].push(element);
	}

	// Function for removing data from Stack
	static popStack(stackName) {
		if( this.stacks[stackName] == undefined ) {
			return undefined;
		}
		return this.stacks[stackName].pop();
	}

	// Function for getting stack length
	static getStackSize(stackName) {
		if( this.stacks[stackName] == undefined ) {
			return undefined;
	}
		return this.stacks[stackName].getSize();
	}

	constructor() {
		this.items = [];
		this.size = 0;
	}

	push(element) {
		this.items.push(element);
		this.size ++;
	}

	pop() {
		if (this.items.length == 0)
			return undefined;
		this.size --;
		return this.items.pop();
	}

	peek() {
		return this.items[this.items.length - 1];
	}

	getSize() {
		return this.size;
	}
}

// Useful class for simulating Small Basic's Array methods
// In our context arrays are actually objects
class Array {
	// Check if an object is an array (Object actually)
	static isArray(obj) {
		return typeof obj === "object";
	}

	// Get the length of an array
	static getLength(obj) {
		if( !Array.isArray(obj) )
			return undefined;
		return Object.keys(obj).length;
	}

	// Get the value of an array at a given index
	static getValue(obj, index) {
		if( !Array.isArray(obj) )
			return undefined;
		return obj[index];
	}

	// Set the value of an array at a given index
	static setValue(obj, index, value) {
		if( !Array.isArray(obj) )
			return undefined;
		obj[index] = value;
	}

	// Remove a value from the end of an array
	static removeValue(obj) {
		if( !Array.isArray(obj) )
			return undefined;
		obj.pop();
	}

	// Check if array contains an element
	static contains(obj, element) {
		if( !Array.isArray(obj) )
			return undefined;
		return obj.includes(element);
	}

	// Check if array contains a index
	static containsIndex(obj, index) {
		if( !Array.isArray(obj) )
			return undefined;
		return obj[index] != undefined;
	}

	// Get all array's indexes
	static getIndexes(obj) {
		if( !Array.isArray(obj) )
			return undefined;
		return Object.keys(obj);
	}
}
// Declaring TextWindow object, here we will display all of our outputs
TextWindow = document.createElement("div");

document.body.appendChild(TextWindow);

// Useful elements for printing data into DOM Object
function customPrint( text ) { TextWindow.innerHTML += text; }
function customPrintLine( text ) { TextWindow.innerHTML += text + "<br>"; }
function customClear( ) { TextWindow.innerHTML = ""; }

// Utils for checking array elements existence
function getArrayValue( indexes_sequence, variable,  ) {
	let iter_variable = variable;
	for( let index of indexes_sequence ) { 
		if( !Array.containsIndex( variable, index ) ) {
			return undefined;
		}
		iter_variable = iter_variable[index];
	}
	return iter_variable;
}

function assignateArray( indexes_sequence, origin, value ){
	// indexes, array of indexes to assign
	// origin, target variable actualy, we will check its state
	// value, value to be assigned to origin[index1]...[indexn]

	let o = {};
	// Make a deep copy of a
	if( typeof a === "object" ) o = JSON.parse( JSON.stringify( a ) );

	let iterator = o;
	let lastIndex = indexes_sequence[ indexes_sequence.length - 1 ];
	for( index of indexes_sequence ){
		if( index == lastIndex ) {
			iterator[ index ] = value;
		} else if ( ! ( index in iterator ) ) {
			iterator[ index ] = {};
		}
		iterator = iterator[ index ];
	}
	return o;
}


/* End of utility functions and classes */

/* Global variables */
var test5;
var test3;
var i;
/* End of global variables */

/* Global functions prototypes */
/* This JSON Object is intentionally called 'Sub', a trick which will prevent 
 Small Basic variables to have crossed names with global functions container*/
var Sub = {
};
/* End of global functions prototypes */

/* Global functions definitions */
/* End of global functions definitions */

// Main Function
async function MAIN() {
	//Goto salto: goto calls are not supported in Javascript
	customPrintLine( "Esto no debe imprimirse" );
	//salto: labels are not supported in Javascript
	i = 20;
	// While declaration
	while ( i > 0) {
		/* While body */
		test3 = i - 3 * (i / 3);
		test5 = i - 5 * (i / 5);
		// If declaration
		if ( test3 == 0 && test5 == 0) {
			/* If body */
			customPrintLine( i + " Fizzbuzz" );
		} else if ( test3 == 0) {
			/* Else if body */
			customPrintLine( i + " Fizz" );
		} else if ( test5 == 0) {
			/* Else if body */
			customPrintLine( i + " Buzz" );
		}		i = i - 1;
	}
	customPrintLine( "" );
	customPrintLine( "- Con etiquetas -" );
	customPrintLine( "" );
	i = 20;
	//loop: labels are not supported in Javascript
	test3 = i - 3 * (i / 3);
	test5 = i - 5 * (i / 5);
	// If declaration
	if ( test3 == 0 && test5 == 0) {
		/* If body */
		customPrintLine( i + " Fizzbuzz" );
	} else if ( test3 == 0) {
		/* Else if body */
		customPrintLine( i + " Fizz" );
	} else if ( test5 == 0) {
		/* Else if body */
		customPrintLine( i + " Buzz" );
	}	i = i - 1;
	// If declaration
	if ( i > 0) {
		/* If body */
		//Goto loop: goto calls are not supported in Javascript
	}

}
// Create main div component for output

MAIN();

