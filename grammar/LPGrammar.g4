grammar LPGrammar;

// SYNTACTIC RULES
s : mainstatement s
  | // Epsilon
  | EOF
  ;

functiondeclaration : SUB IDENTIFIER statement ENDSUB
    ;

ifdeclaration : IF LPAREN expression RPAREN THEN statement ifcontinuation
    ;

ifcontinuation : ENDIF
    | ELSE statement ENDIF
    | ELSEIF LPAREN expression RPAREN THEN statement ifcontinuation
    ;

whiledeclaration : WHILE LPAREN expression RPAREN statement ENDWHILE
    ;

fordeclaration : FOR IDENTIFIER EQUAL expression TO expression fordeclarationcomp
    ;

fordeclarationcomp : STEP expression statement ENDFOR
    | statement ENDFOR
    ;

labelcall : GOTO IDENTIFIER
    ;

statement : IDENTIFIER statementcomp
    | ifdeclaration statement
    | whiledeclaration statement
    | fordeclaration statement
    | labelcall statement
    | specialcall statement
    ;

statementcomp : arrayaccessor EQUAL expression statement
    | LPAREN expression RPAREN statement
    | COLON statement
    ;

arrayaccessor : LBRACKET expression RBRACKET
    | // Epsilon
    ;

mainstatement : IDENTIFIER mainstatementscomp
    | labelcall
    | specialcall
    | ifdeclaration
    | whiledeclaration
    | fordeclaration
    | functiondeclaration
    ;

mainstatementscomp : arrayaccessor EQUAL expression
    | LPAREN expression RPAREN
    | COLON
    ;

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
    | CLOCK
    | ARRAY
    | PROGRAM
    ;

arguments : optionalarg furtherarguments
    | // Epsilon
    ;

optionalarg : expression
    | // Epsilon
    ;

furtherarguments : COMMA arguments furtherarguments
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

// SPECIAL TOKENS
IDENTIFIER : [a-zA-Z][_a-zA-Z0-9]*;
NUMBER : [0-9]+(.[0-9]+){0,1}; // A single point is allowed
TEXT : '"' .*? '"';
COMMENT: '\'' .*? '\n' -> skip; // Single line comments


// True and false tokens must be matched without regard to case
TRUE : '"' T R U E '"';
FALSE : '"' F A L S E '"';

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