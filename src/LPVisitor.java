package src;

import java.util.*;

import gen.*;
import org.antlr.v4.runtime.ParserRuleContext;
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
    // So one definition anywhere should be enough (we'll do at code's start)
    //
    // Variables will be defined at start as
    // var <variable_name>;
    HashSet<String> variables = new HashSet<>();

    // Functions can even have the same name than some variables (in Small Basic)
    // so we must save them in another place D:, also they must be defined at code start
    // since small basic doesn't actually care about functions position
    //
    // Functions will be defined at start as
    // Sub[<function_name>] = function() { ... };
    HashMap<String, String> functions = new HashMap<>();

    // Finally labels can even have the same than variables and functions
    // so again we must difference them in any way
    //
    // Labels will be defined at start as
    // since javascript doesn't have any goto statement, we will simulate it
    // by means of a while and
    // Goto[<label_name>] = true;
    HashSet<String> labels = new HashSet<>();

    @Override
    public T visitS(LPGrammarParser.SContext ctx) {
        // We will visit each of the children of the root node
        // and append the result to the output string
        //
        // Print a cool code header
        System.out.println("Generating code...");

        // Helps with declaring statements in a custom order
        String currentOutput = "";
        String mainOutput = "";

        if( ctx.mainstatement() != null ){
            mainOutput += (String)visit(ctx.mainstatement());
        }

        // All variables must be global
        // Place a block for defining all variables
        String variablesBlock = getIndentedLine( "/* Global variables */", true );
        
        for( String key : this.variables.toArray( new String[this.variables.size()] ) ) {
            variablesBlock += getIndentedLine(
                String.format(
                    "var %s;",
                    key
                ), true
            );
        }

        // Add the variables block to the output
        currentOutput = variablesBlock +
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
                    "\"%s\" : () => {},",
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
                    "Sub[\"%s\"] = () => %s",
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
            // Check if identifier is a variable
            Boolean isDefined = this.variables.contains(ctx.IDENTIFIER().getText());

            // Get complement of statement
            LPGrammarParser.MainstatementscompContext statementcomp = ctx.mainstatementscomp();

            // Useful for classifying statement
            if( statementcomp.COLON() != null ){
                // This a label definition case
                String labelName = ctx.IDENTIFIER().getText();

                // Check if label was already defined
                isDefined = this.labels.contains(labelName);

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
                        "Sub[\"" + ctx.IDENTIFIER().getText() + "\"]();"
                        , false);
            } else {
                // This is an assignation case (Variable definition maybe)
                String varName = ctx.IDENTIFIER().getText();

                // Check if variable was already defined
                isDefined = this.variables.contains(varName);

                if( !isDefined ) {
                    // Add variable definition
                    this.variables.add(varName);
                }

                // Since it is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                // Add variable name
                currentOutput += getIndentedLine(
                        varName
                        , false);

                if( arrayaccessor != null ){
                    // We need to add array accessor to target code
                    currentOutput += visit(arrayaccessor);
                }

                // After getting all nested indexes, lets process assignation
                currentOutput += " = ";

                // Get expression
                currentOutput += visit(statementcomp.expression());
                currentOutput += ";\n";
            }
        }
        else if( ctx.ifdeclaration() != null ){
            // This is an if declaration case
            currentOutput += visit(ctx.ifdeclaration());
        }
        else if( ctx.whiledeclaration() != null ){
            // This is a while declaration case
            currentOutput += visit(ctx.whiledeclaration());
        }
        else if( ctx.fordeclaration() != null ){
            // This is a for declaration case
            currentOutput += visit(ctx.fordeclaration());
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

        System.out.println("Reading a function declaration...");
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
        System.out.println("Reading if declaration...");
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
        currentOutput += visit(ctx.ifcontinuation());

        return (T)currentOutput;
    }

    @Override
    public T visitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx){
        // Visit an if continuation, could be one out of three posible cases
        // 1. EndIf, just end the conditional body
        // 2. ElseIf, read another conditional
        // 3. Else, final block of conditional
        // Note for this point a new line hasn't been printed yet
        System.out.println("Translates if continuation....");
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
        } else if (ctx.ENDIF() != null){
            // Just print another line
            currentOutput += "\n";

        }

        return (T)currentOutput;
    }

    @Override
    public T visitWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx){
        // Visit a while declaration
        // Resulting code will be defined as:
        // while( <condition> ) { <body> }
        // indentation level will be considered
        System.out.println("Reading while declaration...");
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
        System.out.println("Reading for declaration...");
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
            // Check if identifier is a variable
            Boolean isDefined = this.variables.contains(ctx.IDENTIFIER().getText());

            // Get complement of statement
            LPGrammarParser.StatementcompContext statementcomp = ctx.statementcomp();

            // Useful for classifying statement
            if( statementcomp.COLON() != null ){
                // This a label definition case
                String labelName = ctx.IDENTIFIER().getText();

                // Check if label was already defined
                isDefined = this.labels.contains(labelName);

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
                // Presented in the format:
                //
                // Sub[<function name>]();

                currentOutput += getIndentedLine(
                        "Sub[\"" + ctx.IDENTIFIER().getText() + "\"]();"
                        , false);
            } else {
                // This is an assignation case (Variable definition maybe)
                String varName = ctx.IDENTIFIER().getText();

                // Check if variable was already defined
                isDefined = this.variables.contains(varName);

                if( !isDefined ) {
                    // Add variable definition
                    this.variables.add(varName);
                }

                // Print variable name as an start
                currentOutput += getIndentedLine(
                        varName
                        , false);

                // Since it is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                if( arrayaccessor != null ){
                    // We need to add array accessor to target code
                    currentOutput += visit(arrayaccessor);
                }

                // After getting all nested indexes, lets process assignation
                currentOutput += " = ";

                // Get expression
                currentOutput += visit(statementcomp.expression());
                currentOutput += ";\n";
            }
        }
        else if( ctx.ifdeclaration() != null ){
            // This is an if declaration case
            currentOutput += visit(ctx.ifdeclaration());
        }
        else if( ctx.whiledeclaration() != null ){
            // This is a while declaration case
            currentOutput += visit(ctx.whiledeclaration());
        }
        else if( ctx.fordeclaration() != null ){
            // This is a for declaration case
            currentOutput += visit(ctx.fordeclaration());
        }
        else if( ctx.specialcall() != null ){
            // This is a special call case
            currentOutput += visit(ctx.specialcall());
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
            // Print variable name
            currentOutput += ctx.IDENTIFIER().getText();

            // Check if there is an array accessor
            if( ctx.arrayaccessor() != null ){
                currentOutput += visit(ctx.arrayaccessor());
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
        String currentOutput = "";
        if( ctx.LBRACKET() != null ){
            // Print array accessor
            currentOutput += "[";

            // Print expression
            currentOutput += visit(ctx.expression());

            // Print array accessor
            currentOutput += "]";

            // Go further in accessors
            if( ctx.arrayaccessor() != null ){
                currentOutput += visit(ctx.arrayaccessor());
            }
        }

        return (T)currentOutput;
    }

    @Override
    public T visitOperator(LPGrammarParser.OperatorContext ctx){
        // Print operator
        return (T) (" " + ctx.getText() + " ");
    }

    @Override
    public T visitAndoroperator(LPGrammarParser.AndoroperatorContext ctx){
        // Print operator
        return (T) (" " + ctx.getText() + " ");
    }

    @Override
    public T visitComparator(LPGrammarParser.ComparatorContext ctx){
        // Print operator
        return (T) (" " + ctx.getText() + " ");
    }

    @Override
    public T visitSpecialcall(LPGrammarParser.SpecialcallContext ctx){

        System.out.println("Passes through special call");
        String currentOutput = "";

        // Print function call (There is a well known number of special calls)
        LPGrammarParser.SpecialcallkeywordContext specialCall = ctx.specialcallkeyword();
        String methodName = ctx.IDENTIFIER().getText();

        // Process arguments depending on the keyword
        LPGrammarParser.ArgumentsContext arguments = ctx.arguments();
        LPGrammarParser.ExpressionContext[] argumentsData = null;

        if( arguments != null ){
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
                case "Write":
                    // Print version in Javascript
                    currentOutput += getIndentedLine(
                            "console.log(", false
                    );

                    // This instructions expects only one argument
                    if( argumentsData.length == 1 ){
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += ");\n";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.WriteLine expects only one argument"
                        );
                    }
                    break;

                case "Read":
                    // Read version in Javascript
                    if( argumentsData.length == 0 ){
                        currentOutput += getIndentedLine(
                                "prompt();", true
                        );
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Read expects no arguments"
                        );
                    }
                    break;
                case "Show":
                    // Lets say this is an "Alert" equivalent in Javascript
                    if( argumentsData.length == 1 ){
                        currentOutput += getIndentedLine(
                                "alert(", false
                        );
                        currentOutput += visit(argumentsData[0]);
                        currentOutput += ");\n";
                    } else {
                        System.err.println(
                                "[" + specialCall.getStart().getLine() + ":" + specialCall.getStart().getCharPositionInLine() + "] " +
                                        "Error: TextWindow.Show expects only one argument"
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

        } else if( specialCall.ARRAY() != null ){

        } else if( specialCall.PROGRAM() != null ){

        }

        return (T)currentOutput;
    }

    // Convert arguments into array of Strings
    @Override
    public T visitArguments(LPGrammarParser.ArgumentsContext ctx){
        // Get arguments

        // Array result (will be parsed to array later)
        List<LPGrammarParser.ExpressionContext> argumentsData = new ArrayList<>();

        if( ctx.optionalarg() != null ){
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
}
