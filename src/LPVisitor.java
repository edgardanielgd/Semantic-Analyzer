package src;

import java.util.*;

import gen.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import src.CommonTypes;

public class LPVisitor<T> extends LPGrammarBaseVisitor<T> {
    // Here we will override each of already defined visitors
    //
    // Our visitor will generate an string containing the final translated code
    // Fortunately javascript doesn't neccesarily need to be indented or even
    // have newlines, so we will just append to the output string
    //
    // We will also write final code with ; delimitors, however new lines
    // are a good practice we will seek to include as posiblee

    // Show indentation properly (this is also kinda prettier to read :) )
    int indentationLevel = 0;

    // Also we must save already defined variables
    // Note the case of Small Basic does consider all variables as global
    // So one definition anywhere should be enough (we'll do at code's start)
    //
    // Variables will be defined at start as
    // var <variable_name>;
    //
    // Boolean related value indicates whether the variable is an array or not
    HashSet<String> variables = new HashSet<>();

    // Functions can even have the same name than some variables (in Small Basic)
    // so we must save them in another place D:, also they must be defined at code start
    // since small basic doesn't actually care about functions position
    //
    // Functions will be defined at start as
    // Sub[<function_name>] = function() { ... };
    HashMap<String, String> functions = new HashMap<>();

    // Arrays would need special traits since Small Basic allow some weird stuff
    // like a[2]["3"][5555], so we must use separate arrays and use them separately
    // only when a variable is being indexed
    //
    // Arrays (Objects), or well, arrays, will be also defined at start as
    // Array[<array_name>] = {};
    HashSet<String> arrays = new HashSet<>();

    // Finally labels can even have the same than variables and functions
    // so again we must difference them in any way
    //
    // Labels will be defined at start as
    // since javascript doesn't have any goto statement, we will simulate it
    // by means of a while and
    // Goto[<label_name>] = true;
    HashSet<String> labels = new HashSet<>();

    // Checking if current rule is supossed to be within a sentence and not a statement
    // by itself (useful for special calls, which can be assigned or just called)
    Boolean inAssignation = false;

    // Utilities for "console" element inside custom HTML div
    // Console logs and errors will be printed there

    @Override
    public T visitS(LPGrammarParser.SContext ctx) {
        // We will visit each of the children of the root node
        // and append the result to the output string
        //
        // Print a cool code header


        // Helps with declaring statements in a custom order
        String currentOutput = "";
        String mainOutput = "";

        this.indentationLevel++;
        if( ctx.mainstatement() != null ){
            mainOutput += (String)visit(ctx.mainstatement());
        }
        this.indentationLevel--;

        // We will need some extra util functions at start

        currentOutput += getIndentedLine(
            "/* Utility Functions and Classes */",
            true
        );

        // First we will need a function to sleep (Program.Delay)
        // we will use a promise to do so
        String utilities = getIndentedLine(
            "function sleep(ms) {",
            true
        );
        this.indentationLevel++;
        utilities += getIndentedLine(
            "return new Promise(resolve => setTimeout(resolve, ms));",
            true
        );
        this.indentationLevel--;
        utilities += getIndentedLine(
            "}\n",
            true
        );

        // Define Stack class which will be helpful on Stack operations
        utilities += getIndentedLine(

        "// Useful class for simulating Small Basic's Stacks\n" +
            "class Stack {\n" +
                "\t// There will be some stack objects which must be saved\n" +
                "\t// and registered on execution time\n" +
                "\t// Each one will also be identified by its related name\n" +
                "\tstatic stacks = {};\n" +
                "\n" +
                "\t// Function for pushing data into a Stack\n" +
                "\tstatic pushStack(stackName, element) {\n" +
                    "\t\tif( this.stacks[stackName] == undefined ) {\n" +
                    "\t\t\tthis.stacks[stackName] = new Stack();\n" +
                    "\t\t}\n" +
                    "\t\tthis.stacks[stackName].push(element);\n" +
                "\t}\n" +
                "\n" +
                "\t// Function for removing data from Stack\n" +
                "\tstatic popStack(stackName) {\n" +
                    "\t\tif( this.stacks[stackName] == undefined ) {\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\t}\n" +
                    "\t\treturn this.stacks[stackName].pop();\n" +
                "\t}\n" +
                "\n" +
                "\t// Function for getting stack length\n" +
                "\tstatic getStackSize(stackName) {\n" +
                    "\t\tif( this.stacks[stackName] == undefined ) {\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t}\n" +
                    "\t\treturn this.stacks[stackName].getSize();\n" +
                "\t}\n" +
                "\n" +
                "\tconstructor() {\n" +
                    "\t\tthis.items = [];\n" +
                    "\t\tthis.size = 0;\n" +
                "\t}\n" +
                "\n" +
                "\tpush(element) {\n" +
                    "\t\tthis.items.push(element);\n" +
                    "\t\tthis.size ++;\n" +
                "\t}\n" +
                "\n" +
                "\tpop() {\n" +
                    "\t\tif (this.items.length == 0)\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\tthis.size --;\n" +
                    "\t\treturn this.items.pop();\n" +
                "\t}\n" +
                "\n" +
                "\tpeek() {\n" +
                    "\t\treturn this.items[this.items.length - 1];\n" +
                "\t}\n" +
                "\n" +
                "\tgetSize() {\n" +
                    "\t\treturn this.size;\n" +
                "\t}\n" +
            "}\n\n" +
            "// Useful class for simulating Small Basic's Array methods\n" +
            "// In our context arrays are actually objects\n" +
            "class Array {\n" +
                "\t// Check if an object is an array (Object actually)\n" +
                "\tstatic isArray(obj) {\n" +
                    "\t\treturn typeof obj === \"object\";\n" +
                "\t}\n" +
                "\n" +
                "\t// Get the length of an array\n" +
                "\tstatic getLength(obj) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\treturn Object.keys(obj).length;\n" +
                "\t}\n" +
                "\n" +
                "\t// Get the value of an array at a given index\n" +
                "\tstatic getValue(obj, index) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\treturn obj[index];\n" +
                "\t}\n" +
                "\n" +
                "\t// Set the value of an array at a given index\n" +
                "\tstatic setValue(obj, index, value) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\tobj[index] = value;\n" +
                "\t}\n" +
                "\n" +
                "\t// Remove a value from the end of an array\n" +
                "\tstatic removeValue(obj) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\tobj.pop();\n" +
                "\t}\n" +
                "\n" +
                "\t// Check if array contains an element\n" +
                "\tstatic contains(obj, element) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\treturn obj.includes(element);\n" +
                "\t}\n" +
                "\n" +
                "\t// Check if array contains a index\n" +
                "\tstatic containsIndex(obj, index) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\treturn obj[index] != undefined;\n" +
                "\t}\n" +
                "\n" +
                "\t// Get all array's indexes\n" +
                "\tstatic getIndexes(obj) {\n" +
                    "\t\tif( !Array.isArray(obj) )\n" +
                        "\t\t\treturn undefined;\n" +
                    "\t\treturn Object.keys(obj);\n" +
                "\t}\n" +
                "}\n" +
            "// Declaring TextWindow object, here we will display all of our outputs\n" +
            "TextWindow = document.createElement(\"div\");\n\n" +
            "document.body.appendChild(TextWindow);\n\n" +
            "// Useful elements for printing data into DOM Object\n" +
            "function customPrint( text ) { TextWindow.innerHTML += text; }\n" +
            "function customPrintLine( text ) { TextWindow.innerHTML += text + \"<br>\"; }\n" +
            "function customClear( ) { TextWindow.innerHTML = \"\"; }\n\n" +
            "// Utils for checking array elements existence\n" +
            "function getArrayValue( indexes_sequence, variable,  ) {\n" +
            "\tlet iter_variable = variable;\n" +
            "\tfor( let index of indexes_sequence ) { \n" +
            "\t\tif( !Array.containsIndex( variable, index ) ) {\n" +
                "\t\t\treturn undefined;\n" +
            "\t\t}\n" +
            "\t\titer_variable = iter_variable[index];\n" +
            "\t}\n" +
            "\treturn iter_variable;\n" +
            "}\n\n" +
            "function assignateArray( indexes_sequence, origin, value ){\n" +
            "\t// indexes, array of indexes to assign\n" +
            "\t// origin, target variable actualy, we will check its state\n" +
            "\t// value, value to be assigned to origin[index1]...[indexn]\n\n" +
            "\tlet o = {};\n" +
            "\t// Make a deep copy of a\n" +
            "\tif( typeof a === \"object\" ) o = JSON.parse( JSON.stringify( a ) );\n\n" +
            "\tlet iterator = o;\n" +
            "\tlet lastIndex = indexes_sequence[ indexes_sequence.length - 1 ];\n" +
            "\tfor( index of indexes_sequence ){\n" +
                "\t\tif( index == lastIndex ) {\n" +
                    "\t\t\titerator[ index ] = value;\n" +
                "\t\t} else if ( ! ( index in iterator ) ) {\n" +
                    "\t\t\titerator[ index ] = {};\n" +
                "\t\t}\n" +
                "\t\titerator = iterator[ index ];\n" +
            "\t}\n" +
            "\treturn o;\n" +
            "}\n\n",
                true );


        utilities += getIndentedLine(
            "/* End of utility functions and classes */\n",
            true
        );


        currentOutput += utilities;

        // All variables must be global
        // Place a block for defining all variables
        String variablesBlock = getIndentedLine( "/* Global variables */", true );
        
        for( String key : this.variables.toArray(
            new String[ this.variables.size() ]
        ) ) {
            variablesBlock += getIndentedLine(
                String.format(
                    "var %s;",
                    key
                ), true
            );
        }

        // Add the variables block to the output
        currentOutput += variablesBlock +
                getIndentedLine("/* End of global variables */\n", true);

        // Also all functions are global, lets define some
        // prototypes in case they are used before definition
        // by another function mainly
        String functionsBlock = getIndentedLine( "/* Global functions prototypes */", true );
        functionsBlock += getIndentedLine(
            "/* This JSON Object is intentionally called 'Sub', a trick which will prevent \n"+
                    " Small Basic variables to have crossed names with global functions container*/",
            true
        );

        // Recall all functions are defined within a JSON object in case they have the
        // same name than a variable
        functionsBlock += getIndentedLine(
            "var Sub = {",
            true
        );

        // Lets define things in a beautiful way
        this.indentationLevel++;

        for( Map.Entry<String, String> function : this.functions.entrySet() ) {
            // Yep, this is an empty prototype, in this way we ensure about the
            // function is defined before being used
            functionsBlock += getIndentedLine(
                String.format(
                    // Adding quotes in case function's name is a weird word
                    "\"%s\" : async () => {},",
                    function.getKey()
                    ), true
                );
        }

        this.indentationLevel--;

        functionsBlock += getIndentedLine(
            "};",
            true
        );

        currentOutput = currentOutput +
                functionsBlock +
                getIndentedLine("/* End of global functions prototypes */\n", true);

        // Now include functions body
        functionsBlock = getIndentedLine( "/* Global functions definitions */", true );

        // Define again functions, this time with their actual bodies
        for( Map.Entry<String, String> function : this.functions.entrySet() ) {
            functionsBlock += getIndentedLine(
                String.format(
                    // This time its not due to weird functions...
                    "Sub[\"%s\"] = async () => %s",
                    function.getKey(),
                    function.getValue()
                ), true
            );
        }

        currentOutput = currentOutput +
                functionsBlock +
                getIndentedLine("/* End of global functions definitions */\n", true);

        // Add a cool header to the output
        currentOutput =
                getIndentedLine("// Translation from Small Basic to Javascript", true) +
                getIndentedLine("// 3rd Practice (Semmantic Analyzer)", true) +
                getIndentedLine("// Created by:", true) +
                getIndentedLine("// Miguel Angel Puentes Cespedes", true) +
                getIndentedLine("// Jhonatan Steven Rodriguez Ibañez", true) +
                getIndentedLine("// Edgar Daniel Gonzalez Diaz\n", true) +
                currentOutput;

        // Finally append actual code
        // However, lets embed all inside an async function, so we will be able to
        // use sentences like Delay, for example
        mainOutput = getIndentedLine("// Main Function\nasync function MAIN() {", true ) +
        getIndentedLine(mainOutput + "\n}", true ) +
        getIndentedLine("// Create main div component for output", true) +
        getIndentedLine("\nMAIN();\n", true );

        currentOutput = currentOutput + mainOutput;

        return (T)currentOutput;
    }

    @Override
    public T visitMainstatement(LPGrammarParser.MainstatementContext ctx){
        // Visit a statement
        // Resulting code will be defined as:
        // <statement> ;

        String currentOutput = "";

        // If there is an identifier, then there are some cases to consider
        if( ctx.IDENTIFIER() != null ){

            // Get complement of statement
            LPGrammarParser.MainstatementscompContext statementcomp = ctx.mainstatementscomp();

            // Useful for classifying statement
            if( statementcomp.COLON() != null ){
                // This a label definition case
                String labelName = ctx.IDENTIFIER().getText();

                // Check if label was already defined
                Boolean isDefined = this.labels.contains(labelName);

                if( !isDefined ) {
                    // Add label definition
                    this.labels.add(labelName);
                }

                currentOutput += getIndentedLine(
                        labelName + ":"
                        , false);

                // TODO: Print needed javascript code for simulating labels and Goto instructions

            } else if( statementcomp.LPAREN() != null ){
                // This is a function call case
                // Presented in the form:
                //
                // Sub[<function name>]();

                currentOutput += getIndentedLine(
                        "await Sub[\"" + ctx.IDENTIFIER().getText() + "\"]();"
                        , true);
            } else {
                inAssignation = true;

                // This is an assignation case (Variable definition maybe)
                String varName = ctx.IDENTIFIER().getText();

                // Check if variable was already defined
                Boolean isDefined = this.variables.contains(varName);

                // Since it is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                if( !isDefined ) {
                    // Add variable definition
                    this.variables.add(varName);
                }

                // Add variable name
                currentOutput += getIndentedLine(
                        varName
                        , false);

                // Since indexes could not exist yet, then we must use an Util function
                // for checking their existence (create new objects if they do not exist)
                // and then adding needed value
                currentOutput += " = ";

                if( arrayaccessor != null && arrayaccessor.getChildCount() > 0){
                    // Variable is an array, so we must first define it as an empty object
                    // Get all expressions for array indexes (as arrays)
                    LPGrammarParser.ExpressionContext expressions[] = (LPGrammarParser.ExpressionContext[]) visit(arrayaccessor);

                    // In this case, we must call assignateArray function
                    // This function (which is defined statically at code's start) will
                    // create an object and all nested subobjects needed for accessing
                    // the array index successfully (just as Small Basic does)
                    currentOutput += "assignateArray(";

                    // First parameter is an array of built expressions
                    // Recall all of them are evaluated by javascript
                    currentOutput += expressionArrayAsString(expressions);

                    // Second parameter is the target variable to be assignated
                    currentOutput += ", ";

                    // Add variable name
                    currentOutput += varName;

                    // Third parameter is the value to be assignated (Another expression)
                    currentOutput += ", ";

                    // Get expression
                    currentOutput += visit(statementcomp.expression());

                    // Close function call
                    currentOutput += ")";

                } else {
                    // Assign an expression as usual

                    // Get expression
                    currentOutput += visit(statementcomp.expression());
                }
                // Recall built-in functions won't print a semicolon at the end
                // in case we set inAssignation to true
                currentOutput += ";\n";

                inAssignation = false;
            }
        }
        else if( ctx.functiondeclaration() != null ){
            // This is a function declaration case
            // We can't just add it as variable, instead, we will
            // save its definition and use declare it globally later
            String functionDeclaration = (String)visit(ctx.functiondeclaration());

            // Save its declaration related to its name, so we can allow
            // javascript code to have global references to functions
            String functionName = ctx.functiondeclaration().IDENTIFIER().getText();
            functions.put(
                    functionName,
                    functionDeclaration
            );
        }
        else {
            // Mainstatements and common statements would repeat some lines of code
            currentOutput = getStatementExtension(
                    ctx.ifdeclaration(),
                    ctx.whiledeclaration(),
                    ctx.fordeclaration(),
                    ctx.specialcall()
            );
        }

        // Process further statements
        if( ctx.mainstatement() != null ){
            currentOutput += visit(ctx.mainstatement());
        }
        return (T)currentOutput;
    }

    @Override
    public T visitFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx) {
        // Visit a Sub definition
        // Functions in resulting code will be defined in the form:
        // function <name>() { }
        // Reminder, in theory we do not receive any argument
        // However for further works we could add them by CommonTypes implementation

        String currentOutput = "";


        currentOutput += getIndentedLine("// Function declaration", true) +
                getIndentedLine(String.format(
                        "{",
                        ctx.IDENTIFIER().getText()
                ),true);

        // Indent function contents
        this.indentationLevel++;

        currentOutput += getIndentedLine("/* Function body */", true);

        // Visit statements block only if there is any
        if( ctx.statement() != null ) {
            currentOutput += visit(ctx.statement());
        }

        // Reverse indentation level added before starting function body
        this.indentationLevel--;
        currentOutput += getIndentedLine("}", false);

        return (T)currentOutput;
    }

    @Override
    public T visitIfdeclaration(LPGrammarParser.IfdeclarationContext ctx){
        // Visit an If declaration
        // Resulting code will be defined as:
        // if( <condition> ) { <body> }
        // indentation level will be considered
        String currentOutput = "";

        currentOutput += getIndentedLine("// If declaration", true) +
                getIndentedLine(
                        "if ( "
                , false);

        // Visit child expression (which represents a conditional)
        // Note expressions won't print new lines by default
        currentOutput += visit(ctx.expression());

        currentOutput += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        currentOutput += getIndentedLine(
                "/* If body */", true
        );

        // Print related statement data indented
        if(ctx.statement() != null)
            currentOutput += visit(ctx.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // End if corpus
        currentOutput += getIndentedLine("}", false);

        // Visit if continuation
        // (which at this point has the same indentation level than original if)
        if( ctx.ifcontinuation() != null && ctx.ifcontinuation().getChildCount() > 0 )
            currentOutput += visit(ctx.ifcontinuation());
        else
            currentOutput += "\n";

        return (T)currentOutput;
    }

    @Override
    public T visitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx){
        // Visit an if continuation, could be one out of three posible cases
        // 1. EndIf, just end the conditional body
        // 2. ElseIf, read another conditional
        // 3. Else, final block of conditional
        // Note for this point a new line hasn't been printed yet

        String currentOutput = "";
        if( ctx.ELSE() != null ){
            // This the "else statement endif" case
            currentOutput += " else {\n"; // No indent needed (added in parent rule)

            // Create a block for else statement
            this.indentationLevel++;
            currentOutput += getIndentedLine(
                    "/* Else body */", true
            );

            if(ctx.statement() != null)
                currentOutput += visit(ctx.statement());

            // Rollback indentation since else is finished
            this.indentationLevel--;

            // Close else brackets
            currentOutput += getIndentedLine("}", true);
        } else if(ctx.ELSEIF() != null){
            // This is the "elseif ( expression ) then statement ifcontinuation" case
            currentOutput += " else if ( "; // No indent needed (added in parent rule)

            // Visit child expression (which represents a conditional)
            // Note expressions won't print new lines by default
            currentOutput += visit(ctx.expression());

            currentOutput += ") {\n"; // No indent needed

            // Create a block for else if statement
            this.indentationLevel++;
            currentOutput += getIndentedLine(
                    "/* Else if body */", true
            );

            if(ctx.statement() != null)
                currentOutput += visit(ctx.statement());

            // Rollback indentation since else if is finished
            this.indentationLevel--;

            // Close else if brackets (new line will be decided by next rule)
            currentOutput += getIndentedLine("}", false);

            // Visit if continuation
            // (which at this point has the same indentation level than original if)
            currentOutput += visit(ctx.ifcontinuation());
        }

        return (T)currentOutput;
    }

    @Override
    public T visitWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx){
        // Visit a while declaration
        // Resulting code will be defined as:
        // while( <condition> ) { <body> }
        // indentation level will be considered

        String currentOutput = "";

        currentOutput += getIndentedLine("// While declaration", true) +
                getIndentedLine(
                        "while ( "
                        , false);

        // Visit child expression (which represents a conditional)
        // Note expressions won't print new lines by default
        currentOutput += visit(ctx.expression());

        currentOutput += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        currentOutput += getIndentedLine(
                "/* While body */", true
        );

        // Print related statement data indented
        if(ctx.statement() != null)
            currentOutput += visit(ctx.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // End while corpus
        currentOutput += getIndentedLine("}", true);

        return (T)currentOutput;
    }

    @Override
    public T visitFordeclaration(LPGrammarParser.FordeclarationContext ctx){
        // Visit a for declaration
        // Resulting code will be defined as:
        // for( <assignment> ; <condition> ; <assignment> ) { <body> }
        // indentation level will be considered

        String currentOutput = "";
        currentOutput += getIndentedLine("// For declaration", true) +
                getIndentedLine(
                        "for ( "
                        , false);

        // Check if variable is already defined, if not, define it locally in
        // target code
        String iteratorName = ctx.IDENTIFIER().getText();
        Boolean isIterator = this.variables.contains(iteratorName);

        if( ! isIterator ){
            // Iterator variable hasn't been defined at this point, better to define it
            // locally in target code
            currentOutput += "let ";
        }

        currentOutput += iteratorName + " = ";

        // TODO: Check iterator is an integer variable (not even neccessary)
        // How the heck to do that?
        //
        // Generate first expression value (first assignation for iterator)
        currentOutput += visit(ctx.expression(0));

        currentOutput += "; "; // No indent needed

        // Now generate stop condition, it will always have the form:
        // <iterator> <= <expression>
        currentOutput += iteratorName + " <= ";

        // TODO: Check again if iterator is an integer variable
        // How the heck to do that? x2
        currentOutput += visit(ctx.expression(1));

        currentOutput += "; "; // No indent needed

        // Now, check if there is a STEP keyword or we can deduce its just + 1
        // This will be handled by next rule: fordeclarationcomp

        currentOutput += iteratorName;

        // Get following fordeclarationcomp context (would include both step or direct statements block)
        LPGrammarParser.FordeclarationcompContext forcomp = ctx.fordeclarationcomp();

        // Check if there is a step statement
        if(forcomp.STEP() != null) {
            // There is a step statement, we need to generate a new assignation
            // for iterator variable
            currentOutput += " += ";

            // Get increment expression
            currentOutput += visit(forcomp.expression());
        } else {
            // There is no step statement, we can deduce it is just + 1
            currentOutput += "++";
        }

        // End for declaration
        currentOutput += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        currentOutput += getIndentedLine(
                "/* For body */", true
        );

        // Print related statement data indented
        if(forcomp.statement() != null)
            currentOutput += visit(forcomp.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // End for corpus
        currentOutput += getIndentedLine("}", true);

        return (T)currentOutput;
    }

    @Override
    public T visitStatement(LPGrammarParser.StatementContext ctx){
        // Visit a statement
        // Resulting code will be defined as:
        // <statement> ;
        // If there is an identifier, then there are some cases to consider

        String currentOutput = "";
        if( ctx.IDENTIFIER() != null ){

            // Get complement of statement
            LPGrammarParser.StatementcompContext statementcomp = ctx.statementcomp();

            // Useful for classifying statement
            if( statementcomp.COLON() != null ){
                // This a label definition case
                String labelName = ctx.IDENTIFIER().getText();

                // Check if label was already defined
                Boolean isDefined = this.labels.contains(labelName);

                if( !isDefined ) {
                    // Add label definition
                    this.labels.add(labelName);
                }

                currentOutput += getIndentedLine(
                        labelName + ":"
                        , false);

                // TODO: Print needed javascript code for simulating labels and Goto instructions

            } else if( statementcomp.LPAREN() != null ){
                // This is a function call case
                // Presented in the form:
                //
                // Sub[<function name>]();

                currentOutput += getIndentedLine(
                        "await Sub[\"" + ctx.IDENTIFIER().getText() + "\"]();"
                        , true);
            } else {
                inAssignation = true;

                // This is an assignation case (Variable definition maybe)
                String varName = ctx.IDENTIFIER().getText();

                // Check if variable was already defined
                Boolean isDefined = this.variables.contains(varName);

                // Since it is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                if( !isDefined ) {
                    // Add variable definition
                    this.variables.add(varName);
                }

                // Add variable name
                currentOutput += getIndentedLine(
                        varName
                        , false);

                if( arrayaccessor != null && arrayaccessor.getChildCount() > 0){
                    // Variable is an array, so we must first define it as an empty object
                    if( !this.variables.contains(varName) ){
                        // Add variable definition
                        this.variables.add(varName);

                        // Add variable definition
                        currentOutput += " = {};\n";
                        currentOutput += getIndentedLine(
                                varName
                                , false);

                    }
                    // We need to add array accessor to target code
                    currentOutput += getArrayAccessorsAsString(
                            (LPGrammarParser.ExpressionContext[]) visit(arrayaccessor)
                    );
                }

                // After getting all nested indexes, lets process assignation
                currentOutput += " = ";

                // Get expression
                currentOutput += visit(statementcomp.expression());
                currentOutput += ";\n";

                inAssignation = false;
            }
        }
        else {
            // Mainstatements and common statements would repeat some lines of code
            currentOutput = getStatementExtension(
                    ctx.ifdeclaration(),
                    ctx.whiledeclaration(),
                    ctx.fordeclaration(),
                    ctx.specialcall()
            );
        }

        if( ctx.statement() != null ){
            // Process further statement
            currentOutput += visit(ctx.statement());
        }

        return (T)currentOutput;
    }

    @Override
    public T visitExpression(LPGrammarParser.ExpressionContext ctx){
        // Visit an expression
        // Note indent level is considered by parent levels

        String currentOutput = "";
        if( ctx.MINUS() != null ){
            currentOutput += "-";
        }

        // Continue with actual expression data
        currentOutput += visit(ctx.actualexpression());
        return (T)currentOutput;
    }

    @Override
    public T visitActualexpression(LPGrammarParser.ActualexpressionContext ctx){
        // Visit an actual expression
        // Resulting code will be defined as:
        // <actualexpression> <expressionhelper>
        String currentOutput = "";
        // Get subexpression
        if( ctx.subexpression() != null){
            currentOutput += visit(ctx.subexpression());
        } else if ( ctx.LPAREN() != null ){
            // This is a parenthesis case
            // so we can return safely to parent expression case
            currentOutput += "(";
            currentOutput += visit(ctx.expression());
            currentOutput += ")";
        }

        // Get expression complement
        if( ctx.expressionhelper() != null ){
            currentOutput += visit(ctx.expressionhelper());
        }
        return (T)currentOutput;
    }

    @Override
    public T visitExpressionhelper(LPGrammarParser.ExpressionhelperContext ctx){
        // Check operator case
        String currentOutput = "";
        if( ctx.operator() != null ){
            // This is an operator case
            // so we can return safely to parent expression case
            currentOutput += visit(ctx.operator());
            currentOutput += visit(ctx.expression());
        } else if( ctx.andoroperator() != null ){
            // This is an AndOroperator case
            // so we can return safely to parent expression case
            currentOutput += visit(ctx.andoroperator());
            currentOutput += visit(ctx.expression());
        } else if( ctx.comparator() != null ){
            // This is the weird case
            // we can't allow statements in the form a < b < c, for example
            currentOutput += visit(ctx.comparator());
            currentOutput += visit(ctx.notcomparatorexp());
        }

        return (T)currentOutput;
    }

    @Override
    public T visitNotcomparatorexp(LPGrammarParser.NotcomparatorexpContext ctx){
        // Get subexpression
        String currentOutput = "";
        if( ctx.subexpression() != null){
            currentOutput += visit(ctx.subexpression());
        } else if ( ctx.LPAREN() != null ){
            // This is a parenthesis case
            // so we can return safely to parent expression case
            currentOutput += "(";
            currentOutput += visit(ctx.expression());
            currentOutput += ")";
        }

        // Get expression complement
        if( ctx.notcomparatorexphelper() != null ){
            currentOutput += visit(ctx.notcomparatorexphelper());
        }
        return (T)currentOutput;
    }

    @Override
    public T visitNotcomparatorexphelper(LPGrammarParser.NotcomparatorexphelperContext ctx){
        // Check operator case
        String currentOutput = "";
        if( ctx.operator() != null ){
            // This is an operator case
            // so we can return safely to parent notcomparatorexp case
            currentOutput += visit(ctx.operator());
            currentOutput += visit(ctx.notcomparatorexp());
        } else if( ctx.andoroperator() != null ){
            // This is an AndOroperator case
            // so we can return safely to parent expression case
            currentOutput += visit(ctx.andoroperator());
            currentOutput += visit(ctx.expression());
        }
        // Here is the difference, no comparators are allowed here

        return (T)currentOutput;
    }

    @Override
    public T visitSubexpression(LPGrammarParser.SubexpressionContext ctx){
        // Get subexpression
        String currentOutput = "";
        if( ctx.IDENTIFIER() != null ){
            // We should check if this variable does exist, if not, also add it as
            // a global variable
            String varName = ctx.IDENTIFIER().getText();
            if( !this.variables.contains(varName) ){
                // Add variable definition
                this.variables.add(varName);
            }

            // Check if there is an array accessor
            if( ctx.arrayaccessor() != null && ctx.arrayaccessor().getChildCount() > 0){
                // Array's keys would not exist, so we created
                // a util function which will make sure to prevent our parsed
                // code from throwing an error and instead just return null (undefined)
                currentOutput += "getArrayValue(";

                // First parameter is the array of indexes to be accessed
                currentOutput += expressionArrayAsString(
                        (LPGrammarParser.ExpressionContext[]) visit(ctx.arrayaccessor())
                );
                currentOutput += ", ";

                // Second parameter is the array name (Which could match a variable name)
                currentOutput += ctx.IDENTIFIER().getText();
                currentOutput += ")";
            } else {
                // Print variable name (its not an array so we don't care about its existence)
                currentOutput += ctx.IDENTIFIER().getText();
            }
        } else if( ctx.NUMBER() != null ){
            // Print number
            currentOutput += ctx.NUMBER().getText();
        } else if( ctx.TEXT() != null ){
            // Print string
            currentOutput += ctx.TEXT().getText();
        } else if( ctx.TRUE() != null ){
            // Print function call
            currentOutput += "true";
        } else if( ctx.FALSE() != null ){
            // Print function call
            currentOutput += "false";
        } else if( ctx.specialcall() != null ){
            // Print function call (There is a well known number of special calls)
            currentOutput += visit(ctx.specialcall());
        }

        return (T)currentOutput;
    }

    @Override
    public T visitArrayaccessor(LPGrammarParser.ArrayaccessorContext ctx){
        // Print array accessor
        // Will also print all neded checks for actual object existence
        // Since Small Basic doesn't even care about types, you can write
        // a["3"]["5"] ... without having declared a before
        //
        // variableName is the variable we are trying to access to
        List<LPGrammarParser.ExpressionContext> expressions = new ArrayList<>();

        if( ctx.LBRACKET() != null ){

            // Get expression
            expressions.add(ctx.expression());

            // Go further in accessors
            if( ctx.arrayaccessor() != null ){
                expressions.addAll(
                        Arrays.asList(
                                (LPGrammarParser.ExpressionContext[]) visit(ctx.arrayaccessor()) )
                );
            }
        }

        return (T) expressions.toArray(
                new LPGrammarParser.ExpressionContext[expressions.size()]
        );
    }

    @Override
    public T visitOperator(LPGrammarParser.OperatorContext ctx){
        // Print operator
        return (T) (" " + ctx.getText() + " ");
    }

    @Override
    public T visitAndoroperator(LPGrammarParser.AndoroperatorContext ctx){
        // Print operator
        String operatorString;
        if( ctx.AND() != null ) operatorString = "&&";
        else operatorString = "||";

        return (T) (" " + operatorString + " ");
    }

    @Override
    public T visitComparator(LPGrammarParser.ComparatorContext ctx){
        // Print operator
        String comparatorText = ( ctx.EQUAL() != null ) ? "==" : ctx.getText();
        return (T) (" " + comparatorText + " ");
    }

    @Override
    public T visitSpecialcall(LPGrammarParser.SpecialcallContext ctx){


        String currentOutput = "";

        // Print function call (There is a well known number of special calls)
        LPGrammarParser.SpecialcallkeywordContext specialCall = ctx.specialcallkeyword();
        String methodName = ctx.IDENTIFIER().getText();

        // Process arguments depending on the keyword
        LPGrammarParser.ArgumentsContext arguments = ctx.arguments();
        LPGrammarParser.ExpressionContext[] argumentsData = null;

        if( arguments != null){
            // Read provided arguments
            argumentsData = (LPGrammarParser.ExpressionContext[]) visit(arguments);
        } else {
            // No arguments were provided
            argumentsData = new LPGrammarParser.ExpressionContext[0];
        }

        // Check which keyword have we gotten
        if( specialCall.TEXTWINDOW() != null ){

            switch( methodName ){
                case "WriteLine":
                    // Print with new line in Javascript (DOM Object actually)
                    currentOutput +=
                            "customPrintLine( ";

                    // This instructions expects only one argument
                    if( argumentsData.length == 1 ){
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.WriteLine expects only one argument"
                        );
                    }
                    break;
                case "Write":
                    // Print with new line in Javascript (DOM Object actually)
                    currentOutput +=
                            "customPrint( ";

                    // This instructions expects only one argument
                    if( argumentsData.length == 1 ){
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.WriteLine expects only one argument"
                        );
                    }
                    break;

                case "Read":
                case "Pause":
                    // Read version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += "prompt()";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Read expects no arguments"
                        );
                    }
                    break;

                case "ReadNumber":
                    // Read version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += "prompt() * 1";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Read expects no arguments"
                        );
                    }
                    break;

                case "Show":
                    // Hide version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += "TextWindow.style.display = 'block'";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Hide expects no arguments"
                        );
                    }
                    break;

                case "Clear":
                    // Clear version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += "customClear()";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Clear expects no arguments"
                        );
                    }
                    break;

                case "Hide":
                    // Hide version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += "TextWindow.style.display = 'none'";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Hide expects no arguments"
                        );
                    }
                    break;

                default:
                        System.err.println(
                            "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                    "Error: Unknown TextWindow method " + methodName
                        );
            }

        } else if( specialCall.STACK() != null ){

            switch( methodName ){
                case "GetCount":
                    if( argumentsData.length == 1) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Stack.getStackSize( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Stack.getStackSize expects one argument"
                        );
                    }
                    break;
                case "PushValue":
                    if( argumentsData.length == 2) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Stack.pushStack( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Stack.PushValue expects two arguments"
                        );
                    }
                    break;
                case "PopValue":
                    if( argumentsData.length == 1) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Stack.popStack( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Stack.PopValue expects one argument"
                        );
                    }
                    break;
                default:
                    System.err.println(
                            "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                    "Error: Unknown Stack method " + methodName
                    );
            }

        } else if( specialCall.ARRAY() != null ){

            switch( methodName ){
                case "IsArray":
                    if( argumentsData.length == 1) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.isArray( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.IsArray expects one argument"
                        );
                    }
                    break;
                case "GetItemCount":
                    if( argumentsData.length == 1) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.getLength( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.GetItemCount expects one argument"
                        );
                    }
                    break;
                case "GetKeys":
                    if( argumentsData.length == 1) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.getIndexes( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.GetKeys expects one argument"
                        );
                    }
                    break;
                case "GetValue":
                    if( argumentsData.length == 2) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.getValue( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.GetValue expects two arguments"
                        );
                    }
                    break;
                case "SetValue":
                    if( argumentsData.length == 3) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.setValue( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[2]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.SetValue expects three arguments"
                        );
                    }
                    break;
                case "RemoveValue":
                    if( argumentsData.length == 2) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.removeValue( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.RemoveValue expects two arguments"
                        );
                    }
                    break;
                case "ContainsIndex":
                    if( argumentsData.length == 2) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.containsIndex( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.ContainsIndex expects two arguments"
                        );
                    }
                    break;
                case "ContainsValue":
                    if( argumentsData.length == 2) {
                        // Simulate a pushStack call
                        // most of code is statically generated
                        // by the entry Rule (S)
                        currentOutput += "Array.contains( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += " , ";
                        currentOutput += visit(argumentsData[1]);
                        currentOutput += " )";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Array.ContainsValue expects two arguments"
                        );
                    }
                    break;
                default:
                    System.err.println(
                            "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                    "Error: Unknown Stack method " + methodName
                    );
            }

        } else if( specialCall.PROGRAM() != null ){

            switch( methodName ) {

                case "Delay":
                    // Recall we are inside an async function always
                    if( argumentsData.length == 1){
                        currentOutput += "await sleep( ";
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += ")";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: Program.Delay expects only one argument"
                        );
                    }

                    break;

                case "Pause":
                    // Actually does the same compared with the source code
                    currentOutput += "debugger";
                    break;

                case "End":
                    // Ends the program (we have to do it with an exception)
                    currentOutput += "throw 'Program ended'";
                    break;

                default:
                    System.err.println(
                            "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                    "Error: Unknown Program method " + methodName
                    );
            }

        }

        if( inAssignation ){
            // We are in an assignation
            // so we have to add a comma
            currentOutput += " ";
        } else {
            // We are not in an assignation
            // so we have to add a semicolon and add a line
            currentOutput = getIndentedLine(
                    currentOutput + ";", true
            );
        }

        return (T)currentOutput;
    }

    // Convert arguments into array of Strings
    @Override
    public T visitArguments(LPGrammarParser.ArgumentsContext ctx){
        // Get arguments

        // Array result (will be parsed to array later)
        List<LPGrammarParser.ExpressionContext> argumentsData = new ArrayList<>();

        if( ctx.optionalarg() != null && ctx.optionalarg().getChildCount() > 0){
            // A real expression argument was written

            argumentsData.add(ctx.optionalarg().expression());
        }

        LPGrammarParser.FurtherargumentsContext furtherArguments = ctx.furtherarguments();

        if( furtherArguments != null && furtherArguments.COMMA() != null ){
            // There are more arguments
            // Get them

            LPGrammarParser.ExpressionContext[] additionalArgument = (LPGrammarParser.ExpressionContext[]) visit(
                    furtherArguments.arguments()
            );

            // Add them to the array
            argumentsData.addAll(Arrays.asList(additionalArgument));
        }

        return (T)argumentsData.toArray(
                new LPGrammarParser.ExpressionContext[argumentsData.size()]
        );
    }

    // Useful function for printing with indentation certain block codes
    public String getIndentedLine(String line, Boolean newLine){
        // sometimes we may not add a new line, for example, within If conditions
        return "\t".repeat(this.indentationLevel) + line + (
            newLine ? "\n" : ""
            );
    }
    public String getArrayAccessorsAsString(LPGrammarParser.ExpressionContext[] accessors){
        // Get accessors as string
        String accessorsString = "";

        for( LPGrammarParser.ExpressionContext accessor : accessors ){
            accessorsString += "[";
            accessorsString += visit(accessor);
            accessorsString += "]";
        }

        return accessorsString;
    }
    public String getStatementExtension(
            LPGrammarParser.IfdeclarationContext ifdeclaration,
            LPGrammarParser.WhiledeclarationContext whiledeclaration,
            LPGrammarParser.FordeclarationContext fordeclaration,
            LPGrammarParser.SpecialcallContext specialcall
    ){
        String currentOutput = "";

        if( ifdeclaration != null ){
            // This is an if declaration case
            currentOutput += visit(ifdeclaration);
        }
        else if( whiledeclaration != null ){
            // This is a while declaration case
            currentOutput += visit(whiledeclaration);
        }
        else if( fordeclaration != null ){
            // This is a for declaration case
            currentOutput += visit(fordeclaration);
        }
        else if( specialcall != null ){
            // This is a special call case
            currentOutput += visit(specialcall);
        }

        return currentOutput;
    }

    public String expressionArrayAsString(
            LPGrammarParser.ExpressionContext[] expressions
    ){
        String currentOutput = "[";

        for( LPGrammarParser.ExpressionContext expression : expressions ){
            currentOutput += visit(expression);
            currentOutput += ", ";
        }

        return currentOutput + "]";
    }

}
