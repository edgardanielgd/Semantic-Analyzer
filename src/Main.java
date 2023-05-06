package src;

import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import gen.*;
import src.LPVisitor;

public class Main {
    public static void main(String[] args) throws IOException{

        // Get drc and dest files from console
        String srcFile = "./input/test.txt";
        if( args.length > 0 )
            srcFile = args[0];

        String destFile = "output/output.txt";
        if( args.length > 1 )
            destFile = args[1];

        // Common class definition
        LPGrammarLexer lexer = new LPGrammarLexer(
            CharStreams.fromFileName(srcFile)
        );
        CommonTokenStream tokens = new CommonTokenStream(
            lexer
        );
        LPGrammarParser parser = new LPGrammarParser(
            tokens
        );

        // Definition of parsers and visitor
        ParseTree tree = parser.s();
        LPVisitor visitor = new LPVisitor();

        // Our custom visitor will generate an string
        // containing the final output, which is in fact
        // a translation from Small Basic to Javascript

        String output = (String)visitor.visit(tree);
        File outputFile = new File(destFile);
        FileOutputStream writer = new FileOutputStream(outputFile);
        writer.write(output.getBytes());
    }
}
