package daft.sat.solver;

import java.util.Objects;

import daft.sat.logic.Literal;

/**
 * A variable is a named literal within a given context
 */
public class Variable extends Literal {
	
	protected String name;
	
	public Variable(Context context, String name) {
		super(context.getNumVariables());
		this.name = name;
		context.add(this);
	}
	
	public Variable(Context context, String name, boolean negation) {
		super(context.getNumVariables(), negation);
		this.name = name;
		context.add(this);
	}
	
	private Variable(String name, int id, boolean negation) {
		super(id, negation);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Variable copy() {
		return new Variable(name, id, negation);
	}
	
	public void negate() {
		negation = !negation;
	}

	public Variable getNegated() {
		Variable v = this.copy();
		v.negate();
		return v;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Variable)) {
			return false;
		}
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return (isNegated() ? Character.toString(Unicode.NOT) : "") + name;
	}
	
}
