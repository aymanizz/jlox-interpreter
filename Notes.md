# Notes

## The Language Grammer

### Expressions Grammar

```
  expression     := assignment;
  assignment     := (call ".")? IDENTIFIER "=" assignment | ternary;
  ternary        := logic_or ("?" ternary ":" ternary);
  logic_or       := logic_and ("||" logic_and)*;
  logic_and      := equality ("&&" equality)*;
  equality       := comparison (("==" | "!=") comparison)*;
  comparison     := addition ((">" | ">=" | "<" | "<=") addition)*;
  addition       := multiplication (("+" | "-") multiplication)*;
  multiplication := unary (("*" | "/") unary)*;
  unary          := ("-" | "!") unary |  call;
  call           := primary ("(" arguments? ")" | "." IDENTIFIER)*;
  arguments      := expression ("," expression)*;
  primary        := NUMBER | STRING | "true" | "false" | "nil" | "input" | "this" |
            "(" expression ")" | IDENTIFIER | function | "super" "." IDENTIFIER;
```

### Statements Grammar

```
  program     := decleration* EOF;
  declaration := classDecl | funcDecl | varDecl | statement;

  classDecl   := "class" IDENTIFIER ( ":" IDENTIFIER )? "{" "static?" funcDecl* "}";

  funcDecl    := "function" function;
  function    := IDENTIFIER "(" parameters? ")" block;
  parameters  := IDENTIFIER ("," IDENTIFIER)*;
  
  varDecl     := "var" varDeclList ";";
  varDeclList := IDENTIFIER ("=" expression)? ("," IDENTIFIER ("=" expression)?)*;
  
  statement   := exprStmt | printStmt | ifStmt | returnStmt | whileStmt | forStmt | block;
  block       := "{" declaration* "}"
  exprStmt    := expression ";";
  ifStmt      := "if" "(" expression ")" statement ("else" statement)?;
  returnStmt  := "return" expression? ";";
  whileStmt   := "while" "(" expression ")" statement;
  forStmt     := "for" "(" varDecl | exprStmt | ";" expression? ";" expression? ";" ")" statement;
```

## Optional Semicolons

Optional semicolons can be implemented by making semicolons optional
everywhere, the interpreter expects a semicolon at certain positions
in the code but if it did not find a semicolon it can silently pretend
that there is a semicolon where it is expected. this means that code
like this: `for (var x = 10 x < 10 x += 1)` is valid, which is bad (but
interesting).

However this is the wrong way to implement this feature, consider
`"Hello"h` using the above implementation this is interpreted
as `"Hello"; h;` which is a correct statement! what we want instead
is for the interpreter to try to insert the semicolons before a newline
only and not in the middle of a line.

## constructors and inheritance

In the current implementation, the super class constructer must be called
from the subclass constructor explicitly even when there is no constructer
defined in the subclass. In other words, the subclass does not inherit the
constructor.

Since properties are defined in the constructer, it would be
better if the subclass does not have to call the super class constructer
explicitly when there is no constructor defined in the subclass.