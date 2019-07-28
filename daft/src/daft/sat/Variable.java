package daft.sat;

import java.util.Objects;

/**
 * A variable is a named literal within a given context
 */
public class Variable extends Literal {
	
	protected String name;
	
	public Variable(Context context, String name) {
		super(context.getNumVariables());
		this.name = name;
	}
	
	public Variable(Context context, String name, boolean value) {
		super(context.getNumVariables());
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, name, value);
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
		return id == other.id && Objects.equals(name, other.name) && value == other.value;
	}
	
	@Override
	public String toString() {
		return (isFalse() ? "\u00AC" : "") + name;
	}
	
}
