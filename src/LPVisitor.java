package src;

import java.util.*;

import gen.*;
import src.CommonTypes;

public class LPVisitor<T> extends LPGrammarBaseVisitor<T> {
    // Here we will override each of already defined visitors

    // Our visitor will generate an string containing the final translated code
    // Fortunately javascript doesn't neccesarily need to be indented or even
    // have newlines, so we will just append to the output string

    // We will also write final code with ; delimitors, however new lines
    // are a good practice we will seek to include as posiblee
    String output = "";

    // Show indentation properly (this is also kinda prettier to read :) )
    int indentationLevel = 0;

    // Also we must save already defined variables
    // Note the case of Small Basic does consider all variables as global
    // So one definition anywhere should be enough
    // Also save current variable type
    HashMap<String, CommonTypes> variables = new HashMap<String, CommonTypes>();

    @Override
    public T visitS(LPGrammarParser.SContext ctx) {
        // We will visit each of the children of the root node
        // and append the result to the output string

        // Print a cool code header
        System.out.println("Generating code...");

        for( int i = 0; i < ctx.getChildCount(); i++ ) {

            // Visit each child, each child will alter in any way output
            visit(ctx.getChild(i));
        }

        // All variables must be global
        // Place a block for defining all variables
        String variablesBlock = getIndentedLine("/* Global variables */", true);
        for( String key : this.variables.keySet() ) {
            variablesBlock += getIndentedLine(
                String.format(
                    "var %s;\n",
                    key
                ), true
            );
        }

        // Add the variables block to the output
        this.output = variablesBlock +
                getIndentedLine("/* End of global variables */\n", true) +
                this.output;

        // Add a cool header to the output
        this.output =
                getIndentedLine("// Translation from Small Basic to Javascript", true) +
                getIndentedLine("// 3rd Practice (Semmantic Analyzer)", true) +
                getIndentedLine("// Created by:", true) +
                getIndentedLine("// Miguel Angel Puentes Cespedes", true) +
                getIndentedLine("// Jhonatan Steven Rodriguez Ibañez", true) +
                getIndentedLine("// Edgar Daniel Gonzalez Diaz\n", true) +
                this.output;
        return null;
    }

    @Override
    public T visitFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx) {
        // Visit a Sub definition
        // Functions in resulting code will be defined in the form:
        // function <name>() { }
        // Reminder, in theory we do not receive any argument
        // However for further works we could add them by CommonTypes implementation

        // Print a cool code header
        System.out.println("Reading a function declaration...");
        this.output += getIndentedLine("// Function declaration", true) +
                getIndentedLine(String.format(
                        "function %s () {",
                        ctx.IDENTIFIER().getText()
                ),true);
        // Indent function contents
        this.indentationLevel++;

        this.output += getIndentedLine("/* Function body */", true);

        // Visit statements block only if there is any
        if( ctx.statement() != null )
            visit(ctx.statement());

        // Reverse indentation level added before starting function body
        this.indentationLevel--;
        this.output += getIndentedLine("}", true);

        return null;
    }

    @Override
    public T visitIfdeclaration(LPGrammarParser.IfdeclarationContext ctx){
        // Visit an If declaration
        // Resulting code will be defined as:
        // if( <condition> ) { <body> }
        // indentation level will be considered
        System.out.println("Reading if declaration...");
        this.output += getIndentedLine("// If declaration", true) +
                getIndentedLine(
                        "if ( "
                , false);

        // Visit child expression (which represents a conditional)
        // Note expressions won't print new lines by default
        visit(ctx.expression());

        this.output += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        this.output += getIndentedLine(
                "/* If body */", true
        );

        // Print related statement data indented
        if(ctx.statement() != null)
            visit(ctx.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // Visit if continuation
        // (which at this point has the same indentation level than original if)
        visit(ctx.ifcontinuation());

        // End if corpus
        this.output += getIndentedLine("}", true);

        return null;
    }

    @Override
    public T visitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx){
        // Visit an if continuation, could be one out of three posible cases
        // 1. EndIf, just end the conditional body
        // 2. ElseIf, read another conditional
        // 3. Else, final block of conditional
        System.out.println("Pasa");
        Integer alternative = ctx.getRuleIndex();
        System.out.println(alternative);
        return null;
    }
    // Useful function for printing with indentation certain block codes
    public String getIndentedLine(String line, Boolean newLine){
        // sometimes we may not add a new line, for example, within If conditions
        return "\t".repeat(this.indentationLevel) + line + (
            newLine ? "\n" : ""
            );
    }
}