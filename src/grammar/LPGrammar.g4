grammar LPGrammar;

// SYNTACTIC RULES
s : mainstatement EOF
  | EOF
  ;

functiondeclaration : SUB IDENTIFIER statement ENDSUB
    ;

ifdeclaration : IF LPAREN expression RPAREN THEN statement ifcontinuation ENDIF
    ;

ifcontinuation : ELSE statement
    | ELSEIF LPAREN expression RPAREN THEN statement ifcontinuation
    | // Epsilon
    ;

whiledeclaration : WHILE LPAREN expression RPAREN statement ENDWHILE
    ;

fordeclaration : FOR IDENTIFIER EQUAL expression TO expression fordeclarationcomp ENDFOR
    ;

fordeclarationcomp
    // Complementary for declaration with STEP has a iteratorvariable expression
    : STEP expression statement
    | statement
    ;

labelcall : GOTO IDENTIFIER
    ;

statement : IDENTIFIER statementcomp statement
    | ifdeclaration statement
    | whiledeclaration statement
    | fordeclaration statement
    | labelcall statement
    | specialcall statement
    | // Epsilon
    ;

statementcomp : arrayaccessor EQUAL expression
    | LPAREN RPAREN
    | COLON
    ;

arrayaccessor : LBRACKET expression RBRACKET arrayaccessor
    | // Epsilon
    ;

mainstatement : IDENTIFIER mainstatementscomp mainstatement
    | labelcall mainstatement
    | specialcall mainstatement
    | ifdeclaration mainstatement
    | whiledeclaration mainstatement
    | fordeclaration mainstatement
    | functiondeclaration mainstatement
    | // Epsilon
    ;

mainstatementscomp : arrayaccessor EQUAL expression
    | LPAREN RPAREN
    | COLON
    ;

// SINTAX CHECKER FOR NUMERICAL EXPRESSIONS AFTER STEP KEYWORD
// ===========================================================
aritmeticexpression : actualaritmeticexpression
    | MINUS actualaritmeticexpression
    ;

actualaritmeticexpression :
    | NUMBER aritmeticexpressionhelper
    | IDENTIFIER aritmeticexpressionhelper
    | LPAREN aritmeticexpression RPAREN aritmeticexpressionhelper
    | specialcall aritmeticexpressionhelper
    ;

aritmeticexpressionhelper : operator aritmeticexpression
    | // Epsilon
    ;
// ===========================================================

expression : actualexpression
    | MINUS actualexpression
    ;

actualexpression : subexpression expressionhelper
    | LPAREN expression RPAREN expressionhelper
    ;

expressionhelper : operator expression
    | andoroperator expression
    | comparator notcomparatorexp
    | // Epsilon
    ;

notcomparatorexp : subexpression notcomparatorexphelper
    | LPAREN expression RPAREN notcomparatorexphelper
    | MINUS notcomparatorexp
    ;

notcomparatorexphelper : operator notcomparatorexp
    | andoroperator expression
    | // Epsilon
    ;

subexpression : IDENTIFIER arrayaccessor
    | NUMBER
    | TEXT
    | TRUE
    | FALSE
    | specialcall
    ;

operator : PLUS
    | MINUS
    | MULTIPLY
    | DIVIDE
    ;

andoroperator : AND
    | OR
    ;

comparator : LEQ
    | GEQ
    | LOWER
    | GREATER
    | EQUAL
    | DIFF
    ;

specialcall : specialcallkeyword PERIOD IDENTIFIER LPAREN arguments RPAREN
    ;

specialcallkeyword : TEXTWINDOW
    | STACK
    | ARRAY
    | PROGRAM
    ;

arguments : optionalarg furtherarguments
    | // Epsilon
    ;

optionalarg : expression
    | // Epsilon
    ;

furtherarguments : COMMA arguments
    | // Epsilon
    ;

// TOKENS

// RESERVED WORDS
IF : 'If';
ELSE : 'Else';
ENDIF : 'EndIf';
END : 'End';
SUB : 'Sub';
TEXTWINDOW: 'TextWindow';
THEN: 'Then';
ENDSUB: 'EndSub';
ARRAY: 'Array';
ELSEIF: 'ElseIf';
FOR: 'For';
WHILE: 'While';
ENDWHILE: 'EndWhile';
TO: 'To';
ENDFOR: 'EndFor';
GOTO: 'Goto';
PROGRAM: 'Program';
STACK: 'Stack';
STEP: 'Step';
CLOCK: 'Clock';
AND: 'And';
OR: 'Or';

// OPERATORS
PLUS : '+';
MINUS : '-';
MULTIPLY : '*';
DIVIDE : '/';
LEQ : '<=';
GEQ : '>=';
LOWER : '<';
GREATER : '>';
EQUAL : '=';
DIFF : '<>';

// COMMON TOKENS
COMMA : ',';
SEMICOLON : ';';
COLON : ':';
LPAREN : '(';
RPAREN : ')';
LBRACKET : '[';
RBRACKET : ']';
PERIOD : '.';

// True and false tokens must be matched without regard to case
TRUE : '"' T R U E '"';
FALSE : '"' F A L S E '"';

// SPECIAL TOKENS
IDENTIFIER : [a-zA-Z][_a-zA-Z0-9\u00E0-\u00FC\u00C0-\u00DC\u00E1\u00E9\u00ED\u00F3\u00FA\u00FD\u00C1\u00C9\u00CD\u00D3\u00DA\u00DD\u00E2\u00EA\u00EE\u00F4\u00FB\u00C2\u00CA\u00CE\u00D4\u00DB\u00C3\u00F1\u00F5\u00D1\u00D5\u00E4\u00EB\u00EF\u00F6\u00FC\u00FF\u00C4\u00CB\u00CF\u00D6\u00DC\u0178\u00E7\u00DF\u00D8\u00F8\u00C5\u00E5\u00C6\u00E6\u0152\u0153]*;
NUMBER : [0-9]+('.'([0-9]*))?; // A single point is allowed
TEXT : '"' ( '\\' . | ~('\\'|'"') )* '"';
COMMENT: '\'' .*? '\n' -> skip; // Single line comments

// FRAGMENTS FOR CASE INSENSITIVITY
fragment T : ('t'|'T');
fragment R : ('r'|'R');
fragment U : ('u'|'U');
fragment E : ('e'|'E');
fragment F : ('f'|'F');
fragment A : ('a'|'A');
fragment L : ('l'|'L');
fragment S : ('s'|'S');


SPACE : [ \t\r\n]+ -> skip;