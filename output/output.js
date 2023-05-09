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

/* End of utility functions and classes */

/* Global variables */
var a;
var b;
var c;
var cosa;
var d;
var e;
var count;
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
				console.log("Hola");
				await sleep(200);
				// For declaration
				for ( let k = 2; k <= 5; k += 3) {
					/* For body */
					console.log("Hola");
					await sleep(200);
				}
				// For declaration
				for ( let b = 2; b <= 10; b++) {
					/* For body */
					Stack.pushStack("myarray", b);
				}
				await Sub["ola"]();
				count = Stack.popStack("myarray");
				console.log(count);
			}
		}
	}
/* End of global functions definitions */

// Main Function
async function MAIN() {
	a = 3;
	b = 4;
	c = 6;
	d = {};
	d["3"] = 2;
	e = 	Array.isArray(d);
	console.log(e);
	console.log(d["3"]);
	e = 	Array.getLength(d);
	console.log(e);
	e = 	Array.getIndexes(d);
	console.log(e);
	e = 	Array.getValue(d, "3");
	console.log(e);
	e = 	Array.getValue(d, "4");
	console.log(e);
	e = 	Array.getValue(d, "5");
	console.log(e);
	Stack.pushStack("myarray", a);
	Stack.pushStack("myarray", b);
	Stack.pushStack("myarray", c);
	count = Stack.popStack("myarray");
	console.log(count);
	console.log("De mar");

}

MAIN();

