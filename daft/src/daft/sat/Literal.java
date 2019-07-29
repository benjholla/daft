package daft.sat;

import java.util.Map;
import java.util.Objects;

/**
 * A literal has an integer identifier and boolean truth value
 */
public class Literal {

	protected final int id;
	protected boolean negation = false;
	
	public Literal(int id) {
		this.id = id;
	}
	
	public Literal(int id, boolean negation) {
		this.id = id;
		this.negation = negation;
	}

	public boolean isNegated() {
		return negation;
	}
	
	public Literal negate() {
		negation = !negation;
		return this;
	}

	public int getId() {
		return id;
	}
	
	public Literal clone() {
		return new Literal(id, negation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, negation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Literal)) {
			return false;
		}
		Literal other = (Literal) obj;
		return id == other.id && negation == other.negation;
	}

	@Override
	public String toString() {
		return (isNegated() ? Character.toString(Unicode.NOT) : "") + id;
	}

	public LogicalState evaulate(Map<Integer, Boolean> bindings) {
		Boolean binding = bindings.get(id);
		if(binding != null) {
			if(binding) {
				if(negation) {
					return LogicalState.FALSE;
				} else {
					return LogicalState.TRUE;
				}
			} else {
				if(negation) {
					return LogicalState.TRUE;
				} else {
					return LogicalState.FALSE;
				}
			}
		} else {
			return LogicalState.UNBOUND;
		}
	}
	
}
