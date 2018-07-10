# JLox Interpreter

This is an implementation of the JLox interpreter (A walk-tree
interpreter) implemented in the first part of the book
[crafting interpreters](craftinginterpreters.com/).

In addition to the original implementation, this one includes solved
challenges, as well as other features.

## Differences From Lox Implemented in The Book

- String concatenation without explicit + operator
- Arrow style functions, `(function () => "Hello World")()`
- `function` instead of `fun`.
- `inherits` instead of `<`.
- `print` is a function not a statement.
- Two additional global functions: `input` and `println`.
- The function keyword is required before classes' methods.
- Most of the challenges are solved in this implementation. Some of the challenges not solved are:
  - Comma operator: although I implemented the comma
    operator at first, it caused grammar ambiguity later on. Resolving
    the grammar ambiguity for a little gain is just not worth it so I
    simply removed it.
  - Displaying results in the interactive interpreter
    without needing a print statement.
  - Getters and setters in classes.

## How to Build The Interpreter

Clone this repo then run the command `./tools/build.sh` from the project's
root folder.

## How to Run The interpreter

After building the interpreter, run `cd build && java jlox.Lox`.

## Learn It in 2 Minutes

See [test file](/tests/test.lox).