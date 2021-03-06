// this file is automatically generated using tool/GenerateAST.java
package jlox;

import java.util.List;

abstract class Expr {
	interface Visitor<R> {
		R visit(Assign expr);
		R visit(Set expr);
		R visit(This expr);
		R visit(Super expr);
		R visit(Ternary expr);
		R visit(Binary expr);
		R visit(Logical expr);
		R visit(Unary expr);
		R visit(Grouping expr);
		R visit(Literal expr);
		R visit(Call expr);
		R visit(Get expr);
		R visit(Function expr);
		R visit(Variable expr);
	}
	static class Assign extends Expr {
		Assign(Token name, Expr value) {
			this.name = name;
			this.value = value;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token name;
		final Expr value;
	}
	static class Set extends Expr {
		Set(Expr object, Token name, Expr value) {
			this.object = object;
			this.name = name;
			this.value = value;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr object;
		final Token name;
		final Expr value;
	}
	static class This extends Expr {
		This(Token keyword) {
			this.keyword = keyword;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token keyword;
	}
	static class Super extends Expr {
		Super(Token keyword, Token method) {
			this.keyword = keyword;
			this.method = method;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token keyword;
		final Token method;
	}
	static class Ternary extends Expr {
		Ternary(Token operator, Expr condition, Expr left, Expr right) {
			this.operator = operator;
			this.condition = condition;
			this.left = left;
			this.right = right;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token operator;
		final Expr condition;
		final Expr left;
		final Expr right;
	}
	static class Binary extends Expr {
		Binary(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr left;
		final Token operator;
		final Expr right;
	}
	static class Logical extends Expr {
		Logical(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr left;
		final Token operator;
		final Expr right;
	}
	static class Unary extends Expr {
		Unary(Token operator, Expr right) {
			this.operator = operator;
			this.right = right;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token operator;
		final Expr right;
	}
	static class Grouping extends Expr {
		Grouping(Expr expression) {
			this.expression = expression;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr expression;
	}
	static class Literal extends Expr {
		Literal(Object value) {
			this.value = value;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Object value;
	}
	static class Call extends Expr {
		Call(Expr callee, Token paren, List<Expr> arguments) {
			this.callee = callee;
			this.paren = paren;
			this.arguments = arguments;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr callee;
		final Token paren;
		final List<Expr> arguments;
	}
	static class Get extends Expr {
		Get(Expr object, Token name) {
			this.object = object;
			this.name = name;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Expr object;
		final Token name;
	}
	static class Function extends Expr {
		Function(List<Token> parameters, List<Stmt> body) {
			this.parameters = parameters;
			this.body = body;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final List<Token> parameters;
		final List<Stmt> body;
	}
	static class Variable extends Expr {
		Variable(Token name) {
			this.name = name;
		}

		<R> R accept(Visitor<R> visitor) {
			return visitor.visit(this);
		}

		final Token name;
	}

	abstract <R> R accept(Visitor<R> visitor);
}
