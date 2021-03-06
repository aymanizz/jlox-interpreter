/*
Expression Class Generator
	the `Expr` class is an abstract class. each rule in the grammer inherits this class.
	to define new behaviour implement the Visitor interface to add
	the behaviour to each type of expression

	abstract class Expr {
		interface Visitor<R> {
			R visitBinaryExpr(Binary expr);
			R visitGroupingExpr(Grouping expr);
			...
		}

		static class Binary {
			Binary(Expr right, Token operator, Expr left) {
				this.right = right;
				this.left = left;
				this.operator = operator;
			}

			<R> R accept(Visitor<R> visitor) {
				return visitor.visitBinary(self);
			}

			private final Expr right;
			private final Expr left;
			private final Token operator;
		}
		...

		abstract <R> R accept(Visitor<R> visitor);
	}
	
	use of Visitor Pattern:
		as we craft our interpreter we will need to add more classes
		and more 'behaviour' (such as interpret) for each class.
		addition of classes requires adding all behaviours to the new class.
		addition of behaviour requires adding the behaviour to each class.
		to solve this problem we use a level of indirection.
		each class implements only one function `accept` which can be called on any
		of the derived classes through polymorphism.
		the function takes a `Visior` class instance and applies
		the visit method to itself, i.e, visitor.visit(self);
		and the visitor class implements the behaviour.
*/

package tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAST {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: generate_ast <output directory>");
			System.exit(1);
		}

		String outputDir = args[0];

		defineAST(outputDir, "Expr", Arrays.asList(
			"Assign   : Token name, Expr value",
			"Set      : Expr object, Token name, Expr value",
			"This     : Token keyword",
			"Super    : Token keyword, Token method",
			"Ternary  : Token operator, Expr condition, Expr left, Expr right",
			"Binary   : Expr left, Token operator, Expr right",
			"Logical  : Expr left, Token operator, Expr right",
			"Unary    : Token operator, Expr right",
			"Grouping : Expr expression",
			"Literal  : Object value",
			"Call     : Expr callee, Token paren, List<Expr> arguments",
			"Get      : Expr object, Token name",
			"Function : List<Token> parameters, List<Stmt> body",
			"Variable : Token name"
		));

		defineAST(outputDir, "Stmt", Arrays.asList(
			"Block      : List<Stmt> statements",
			"Expression : Expr expression",
			"Var        : List<Token> names, List<Expr> initializers",
			"Function   : Token name, List<Token> parameters, List<Stmt> body",
			"Method     : Stmt.Function function, Boolean isStatic",
			"Class      : Token name, Expr.Variable superclass, List<Stmt.Method> methods",
			"Break      : Token keyword",
			"Continue   : Token keyword",
			"Return     : Token keyword, Expr value",
			"If         : Expr condition, Stmt thenBranch, Stmt elseBranch",
			"While      : Expr condition, Stmt body",
			"For        : Stmt initializer, Expr condition, Expr increment, Stmt body"
		));
	}

	private static void defineAST(
		  String outputDir, String baseName, List<String> types)
		  throws IOException {
		String path = outputDir + "/" + baseName + ".java";
		PrintWriter writer = new PrintWriter(path, "UTF-8");

		writer.println("// this file is automatically generated using tool/GenerateAST.java");
		writer.println("package jlox;");
		writer.println("");
		writer.println("import java.util.List;");
		writer.println("");
		writer.println("abstract class " + baseName + " {");

		defineVisitor(writer, baseName, types);

		for (String type : types) {
			String[] t = type.split(":");
			String className = t[0].trim();
			String fields = t.length == 2 ? t[1].trim() : "";
			defineType(writer, baseName, className, fields);
		}

		writer.println();
		writer.println("	abstract <R> R accept(Visitor<R> visitor);");

		writer.println("}");
		writer.close();
	}

	private static void defineVisitor(
		  PrintWriter writer, String baseName, List<String> types) {
		writer.println("	interface Visitor<R> {");
		
		for (String type : types) {
			String typeName = type.split(":")[0].trim();
			writer.println("		R visit" + "(" +
				typeName + " " + baseName.toLowerCase() + ");");
		}

		writer.println("	}");
	}

	private static void defineType(
		  PrintWriter writer, String baseName, String className, String fieldList) {
		writer.println("	static class " + className + " extends " +
			baseName + " {");
		
		writer.println("		" + className + "(" + fieldList + ") {");
		String[] fields = fieldList.split(", ");
		for (String field : fields) {
			if (field.split(" ").length >= 2) {
				String name = field.split(" ")[1];
				writer.println("			this." + name + " = " + name + ";");
			}
		}
		writer.println("		}");

		writer.println();
		writer.println("		<R> R accept(Visitor<R> visitor) {");
		writer.println("			return visitor.visit(this);");
		writer.println("		}");

		writer.println();
		for (String field : fields) {
			if (field.split(" ").length >= 2)
				writer.println("		final " + field + ";");
		}

		writer.println("	}");
	}
}
