// Translation from Small Basic to Javascript
// 3rd Practice (Semmantic Analyzer)
// Created by:
// Miguel Angel Puentes Cespedes
// Jhonatan Steven Rodriguez IbaÃ±ez
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
var a;
var b;
var olademar;
var c;
var cosa;
var casita2;
var casita3;
var count;
var x;
var False;
var casita;
var algo;
/* End of global variables */

/* Global functions prototypes */
/* This JSON Object is intentionally called 'Sub', a trick which will prevent 
 Small Basic variables to have crossed names with global functions container*/
var Sub = {
	"ola" : async () => {},
};
/* End of global functions prototypes */

/* Global functions definitions */
Sub["ola"] = async () => 	// Function declaration
	{
		/* Function body */
		cosa = 2;
		// If declaration
		if ( olademar = 4 + 2 * 3) {
			/* If body */
			cosa = 3 * 2 <= 6;
			// For declaration
			for ( let i = 2; i <= 4; i += 2) {
				/* For body */
				cosa = 3 * 2 <= 6;
			}
		}
		// While declaration
		while ( algo - 3 * 2 <= 6) {
			/* While body */
			algo = 3 * 2 <= 6;
			// If declaration
			if ( casita && casita2 && casita3 && false) {
				/* If body */
				customPrintLine( "Hola" );
				await sleep( 200);
				// For declaration
				for ( let k = 2; k <= 5; k += 3) {
					/* For body */
					customPrintLine( "Hola" );
					await sleep( 200);
				}
				// For declaration
				for ( let b = 2; b <= 10; b++) {
					/* For body */
					Stack.pushStack( "myarray" , b );
				}
				await Sub["ola"]();
				count = Stack.popStack( "myarray" ) ;
				customPrintLine( count );
			}
		}
	}
/* End of global functions definitions */

// Main Function
async function MAIN() {
	a = prompt() * 1 ;
	b = prompt() * 1 ;
	c = a + b;
	// If declaration
	if ( c > 3) {
		/* If body */
		customPrintLine( "Hola" );
	} else {
		/* Else body */
		customPrintLine( "Adios" );
	}
	a = 2;
	b = 50 * getArrayValue([3, 2, 1, ], x);
	customPrintLine( b );
	a = assignateArray(["3", 2, False, 2 * 3 + 2, ], a, getArrayValue(["3", 2, ], a));

}
// Create main div component for output

MAIN();

