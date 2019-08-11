package daft.sat.logic;

import java.util.Map;
import java.util.Objects;

import daft.sat.solver.Unicode;

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
	
	public void negate() {
		negation = !negation;
	}
	
	public Literal getNegated() {
		Literal l = this.copy();
		l.negate();
		return l;
	}

	public int getId() {
		return id;
	}
	
	protected Literal copy() {
		return new Literal(id, negation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	@Override
	public String toString() {
		return (isNegated() ? Character.toString(Unicode.NOT) : "") + id;
	}

	public LogicalState evaulate(Map<Literal, Boolean> assignments) {
		Boolean assignment = assignments.get(this);
		if(assignment != null) {
			if(assignment) {
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
