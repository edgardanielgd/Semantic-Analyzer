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
    public T visitMainstatement(LPGrammarParser.MainstatementContext ctx){
        // Visit a statement
        // Resulting code will be defined as:
        // <statement> ;

        // If there is an identifier, then there are some cases to consider
        if( ctx.IDENTIFIER() != null ){
            // Check if identifier is a variable
            CommonTypes variable = this.variables.get(ctx.IDENTIFIER().getText());

            // Get complement of statement
            LPGrammarParser.MainstatementscompContext statementcomp = ctx.mainstatementscomp();

            // Useful for classifying statement
            int type = 0;
            if( statementcomp.COLON() != null ){
                // This a label definition case
                type = 1;
            } else if( statementcomp.LPAREN() != null ){
                // This is a function call case
                type = 2;
            } else {
                // This is an assignation case
                type = 3;
            }

            if( variable == null && (type == 1 || type == 3)) {
                // Add variable definition to target code
                this.variables.put(ctx.IDENTIFIER().getText(), new CommonTypes(
                        ctx.IDENTIFIER().getText(), 1
                ));
                // TODO: Save variable type
            }

            // it is a good idea to always print target variable name
            this.output += getIndentedLine(
                    ctx.IDENTIFIER().getText()
                    , false);

            // Start translating rule depending on type
            // It is a good idea
            if( type == 2 ){
                // This is a function call case
                // Recall functions does not receive arguments in our source lang
                this.output += "( );\n";
            } else if( type == 3 ){
                // This is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                if( arrayaccessor != null ){
                    // We need to add array accessor to target code
                    visit(arrayaccessor);
                }

                // After getting all nested indexes, lets process assignation
                this.output += " = ";

                // Get expression
                visit(statementcomp.expression());

                this.output += ";\n";
            } else {
                // This is a label definition case
            }

            // Process further statement
            if( ctx.mainstatement() != null ){
                visit(ctx.mainstatement());
            }
        }
        else if( ctx.ifdeclaration() != null ){
            // This is an if declaration case
            visit(ctx.ifdeclaration());
        }
        else if( ctx.whiledeclaration() != null ){
            // This is a while declaration case
            visit(ctx.whiledeclaration());
        }
        else if( ctx.fordeclaration() != null ){
            // This is a for declaration case
            visit(ctx.fordeclaration());
        }
        else if( ctx.functiondeclaration() != null ){
            // This is a function declaration case
            visit(ctx.functiondeclaration());
        }
        return null;
    }

    @Override
    public T visitFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx) {
        // Visit a Sub definition
        // Functions in resulting code will be defined in the form:
        // function <name>() { }
        // Reminder, in theory we do not receive any argument
        // However for further works we could add them by CommonTypes implementation

        // TODO: Define function as a variable

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

        // End if corpus
        this.output += getIndentedLine("}", false);

        // Visit if continuation
        // (which at this point has the same indentation level than original if)
        visit(ctx.ifcontinuation());

        return null;
    }

    @Override
    public T visitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx){
        // Visit an if continuation, could be one out of three posible cases
        // 1. EndIf, just end the conditional body
        // 2. ElseIf, read another conditional
        // 3. Else, final block of conditional
        // Note for this point a new line hasn't been printed yet
        System.out.println("Translates if continuation....");
        System.out.println(ctx.getText());
        if( ctx.ELSE() != null ){
            // This the "else statement endif" case
            this.output += " else {\n"; // No indent needed (added in parent rule)

            // Create a block for else statement
            this.indentationLevel++;
            this.output += getIndentedLine(
                    "/* Else body */", true
            );

            if(ctx.statement() != null)
                visit(ctx.statement());

            // Rollback indentation since else is finished
            this.indentationLevel--;

            // Close else brackets
            this.output += getIndentedLine("}", true);
        } else if(ctx.ELSEIF() != null){
            // This is the "elseif ( expression ) then statement ifcontinuation" case
            this.output += " else if ( "; // No indent needed (added in parent rule)

            // Visit child expression (which represents a conditional)
            // Note expressions won't print new lines by default
            visit(ctx.expression());

            this.output += ") {\n"; // No indent needed

            // Create a block for else if statement
            this.indentationLevel++;
            this.output += getIndentedLine(
                    "/* Else if body */", true
            );

            if(ctx.statement() != null)
                visit(ctx.statement());

            // Rollback indentation since else if is finished
            this.indentationLevel--;

            // Close else if brackets (new line will be decided by next rule)
            this.output += getIndentedLine("}", false);

            // Visit if continuation
            // (which at this point has the same indentation level than original if)
            System.out.println("ola");
            visit(ctx.ifcontinuation());
        } else if (ctx.ENDIF() != null){
            // This is the "endif" case
            // Note there is a pending new line to be printed (Next to last { token)
            this.output += getIndentedLine(
                    "\n/* End of if */", true
            );
        }

        return null;
    }

    @Override
    public T visitWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx){
        // Visit a while declaration
        // Resulting code will be defined as:
        // while( <condition> ) { <body> }
        // indentation level will be considered
        System.out.println("Reading while declaration...");
        this.output += getIndentedLine("// While declaration", true) +
                getIndentedLine(
                        "while ( "
                        , false);

        // Visit child expression (which represents a conditional)
        // Note expressions won't print new lines by default
        visit(ctx.expression());

        this.output += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        this.output += getIndentedLine(
                "/* While body */", true
        );

        // Print related statement data indented
        if(ctx.statement() != null)
            visit(ctx.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // End while corpus
        this.output += getIndentedLine("}", true);

        return null;
    }

    @Override
    public T visitFordeclaration(LPGrammarParser.FordeclarationContext ctx){
        // Visit a for declaration
        // Resulting code will be defined as:
        // for( <assignment> ; <condition> ; <assignment> ) { <body> }
        // indentation level will be considered
        System.out.println("Reading for declaration...");
        this.output += getIndentedLine("// For declaration", true) +
                getIndentedLine(
                        "for ( "
                        , false);

        // Check if variable is already defined, if not, define it locally in
        // target code
        String iteratorName = ctx.IDENTIFIER().getText();
        CommonTypes iterator = this.variables.get(iteratorName);


        if( iterator == null ){
            // Iterator variable hasn't been defined at this point, better to define it
            // locally in target code
            this.output += "let ";
        }

        this.output += iteratorName + " = ";

        // TODO: Check iterator is an integer variable
        // How the heck to do that?

        // Generate first expression value (first assignation for iterator)
        visit(ctx.expression(0));

        this.output += "; "; // No indent needed

        // Now generate stop condition, it will always have the form:
        // <iterator> <= <expression>
        this.output += iteratorName + " <= ";

        // TODO: Check again if iterator is an integer variable
        // How the heck to do that? x2
        visit(ctx.expression(1));

        this.output += "; "; // No indent needed

        // Now, check if there is a STEP keyword or we can deduce its just + 1
        // This will be handled by next rule: fordeclarationcomp

        this.output += iteratorName;

        // Get following fordeclarationcomp context (would include both step or direct statements block)
        LPGrammarParser.FordeclarationcompContext forcomp = ctx.fordeclarationcomp();

        // Check if there is a step statement
        if(forcomp.STEP() != null) {
            // There is a step statement, we need to generate a new assignation
            // for iterator variable
            this.output += " += ";

            // Get increment expression
            visit(forcomp.expression());
        } else {
            // There is no step statement, we can deduce it is just + 1
            this.output += "++";
        }

        // End for declaration
        this.output += ") {\n"; // No indent needed

        // Increase indentation level for embeded code
        this.indentationLevel++;

        this.output += getIndentedLine(
                "/* For body */", true
        );

        // Print related statement data indented
        if(forcomp.statement() != null)
            visit(forcomp.statement());

        // Decrease indentation since embeded code has already been parsed
        this.indentationLevel--;

        // End for corpus
        this.output += getIndentedLine("}", true);

        return null;
    }

    @Override
    public T visitStatement(LPGrammarParser.StatementContext ctx){
        // Visit a statement
        // Resulting code will be defined as:
        // <statement> ;

        // If there is an identifier, then there are some cases to consider
        if( ctx.IDENTIFIER() != null ){
            // Check if identifier is a variable
            CommonTypes variable = this.variables.get(ctx.IDENTIFIER().getText());

            // Get complement of statement
            LPGrammarParser.StatementcompContext statementcomp = ctx.statementcomp();

            // Useful for classifying statement
            int type = 0;
            if( statementcomp.COLON() != null ){
                // This a label definition case
                type = 1;
            } else if( statementcomp.LPAREN() != null ){
                // This is a function call case
                type = 2;
            } else {
                // This is an assignation case
                type = 3;
            }

            if( variable == null && (type == 1 || type == 3)) {
                // Add variable definition to target code
                this.variables.put(ctx.IDENTIFIER().getText(), new CommonTypes(
                        ctx.IDENTIFIER().getText(), 1
                ));
                // TODO: Save variable type
            }

            // it is a good idea to always print target variable name
            this.output += getIndentedLine(
                    ctx.IDENTIFIER().getText()
                    , false);

            // Start translating rule depending on type
            // It is a good idea
            if( type == 2 ){
                // This is a function call case
                // Recall functions does not receive arguments in our source lang
                this.output += "( );\n";
            } else if( type == 3 ){
                // This is an assignation case
                LPGrammarParser.ArrayaccessorContext arrayaccessor = statementcomp.arrayaccessor();

                if( arrayaccessor != null ){
                    // We need to add array accessor to target code
                    visit(arrayaccessor);
                }

                // After getting all nested indexes, lets process assignation
                this.output += " = ";

                // Get expression
                visit(statementcomp.expression());

                this.output += ";\n";
            } else {
                // This is a label definition case
            }

            // Process further statement
            if( ctx.statement() != null ){
                visit(ctx.statement());
            }
        }
        else if( ctx.ifdeclaration() != null ){
            // This is an if declaration case
            visit(ctx.ifdeclaration());
        }
        else if( ctx.whiledeclaration() != null ){
            // This is a while declaration case
            visit(ctx.whiledeclaration());
        }
        else if( ctx.fordeclaration() != null ){
            // This is a for declaration case
            visit(ctx.fordeclaration());
        }
        return null;
    }

    @Override
    public T visitExpression(LPGrammarParser.ExpressionContext ctx){
        // Visit an expression
        // Note indent level is considered by parent levels

        if( ctx.MINUS() != null ){
            this.output += "-";
        }

        // Continue with actual expression data
        visit(ctx.actualexpression());
        return null;
    }

    @Override
    public T visitActualexpression(LPGrammarParser.ActualexpressionContext ctx){
        // Visit an actual expression
        // Resulting code will be defined as:
        // <actualexpression> <expressionhelper>

        // Get subexpression
        if( ctx.subexpression() != null){
            visit(ctx.subexpression());
        } else if ( ctx.LPAREN() != null ){
            // This is a parenthesis case
            // so we can return safely to parent expression case
            this.output += "(";
            visit(ctx.expression());
            this.output += ")";
        }

        // Get expression complement
        if( ctx.expressionhelper() != null ){
            visit(ctx.expressionhelper());
        }
        return null;
    }

    @Override
    public T visitExpressionhelper(LPGrammarParser.ExpressionhelperContext ctx){
        // Check operator case
        if( ctx.operator() != null ){
            // This is an operator case
            // so we can return safely to parent expression case
            visit(ctx.operator());
            visit(ctx.expression());
        } else if( ctx.andoroperator() != null ){
            // This is an AndOroperator case
            // so we can return safely to parent expression case
            visit(ctx.andoroperator());
            visit(ctx.expression());
        } else if( ctx.comparator() != null ){
            // This is the weird case
            // we can't allow statements in the form a < b < c, for example
            visit(ctx.comparator());
            visit(ctx.notcomparatorexp());
        }

        return null;
    }

    @Override
    public T visitNotcomparatorexp(LPGrammarParser.NotcomparatorexpContext ctx){
        // Get subexpression
        if( ctx.subexpression() != null){
            visit(ctx.subexpression());
        } else if ( ctx.LPAREN() != null ){
            // This is a parenthesis case
            // so we can return safely to parent expression case
            this.output += "(";
            visit(ctx.expression());
            this.output += ")";
        }

        // Get expression complement
        if( ctx.notcomparatorexphelper() != null ){
            visit(ctx.notcomparatorexphelper());
        }
        return null;
    }

    @Override
    public T visitNotcomparatorexphelper(LPGrammarParser.NotcomparatorexphelperContext ctx){
        // Check operator case
        if( ctx.operator() != null ){
            // This is an operator case
            // so we can return safely to parent notcomparatorexp case
            visit(ctx.operator());
            visit(ctx.notcomparatorexp());
        } else if( ctx.andoroperator() != null ){
            // This is an AndOroperator case
            // so we can return safely to parent expression case
            visit(ctx.andoroperator());
            visit(ctx.expression());
        }
        // Here is the difference, no comparators are allowed here

        return null;
    }

    @Override
    public T visitSubexpression(LPGrammarParser.SubexpressionContext ctx){

        if( ctx.IDENTIFIER() != null ){
            // Print variable name
            this.output += ctx.IDENTIFIER().getText();

            // Check if there is an array accessor
            if( ctx.arrayaccessor() != null ){
                System.out.println(ctx.arrayaccessor().getText());
                visit(ctx.arrayaccessor());
            }
        } else if( ctx.NUMBER() != null ){
            // Print number
            this.output += ctx.NUMBER().getText();
        } else if( ctx.TEXT() != null ){
            // Print string
            this.output += ctx.TEXT().getText();
        } else if( ctx.TRUE() != null ){
            // Print function call
            this.output += "true";
        } else if( ctx.FALSE() != null ){
            // Print function call
            this.output += "false";
        } else if( ctx.specialcall() != null ){
            // Print function call (There is a well known number of special calls)
            visit(ctx.specialcall());
        }

        return null;
    }

    @Override
    public T visitArrayaccessor(LPGrammarParser.ArrayaccessorContext ctx){

        // Print array accessor
        if( ctx.LBRACKET() != null ){
            this.output += "[";

            // Print expression
            visit(ctx.expression());

            // Print array accessor
            this.output += "]";
        }

        return null;
    }

    @Override
    public T visitOperator(LPGrammarParser.OperatorContext ctx){
        // Print operator
        this.output += " " + ctx.getText() + " ";
        return null;
    }

    @Override
    public T visitAndoroperator(LPGrammarParser.AndoroperatorContext ctx){
        // Print operator
        this.output += ctx.getText();
        return null;
    }

    @Override
    public T visitComparator(LPGrammarParser.ComparatorContext ctx){
        // Print operator
        String operator = ctx.getText();
        this.output += operator == "<>" ? "!=" : operator;
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
