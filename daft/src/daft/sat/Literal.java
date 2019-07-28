package daft.sat;

import java.util.Objects;

/**
 * A literal has an integer identifier and boolean truth value
 */
public class Literal {

	protected final int id;
	protected boolean value = true;
	
	public Literal(int id) {
		this.id = id;
	}
	
	public Literal(int id, boolean value) {
		this.id = id;
		this.value = value;
	}

	public boolean isTrue() {
		return value;
	}
	
	public boolean isFalse() {
		return !value;
	}
	
	public Literal negate() {
		value = !value;
		return this;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value);
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
		return id == other.id && value == other.value;
	}

	@Override
	public String toString() {
		return (isFalse() ? "\u00AC" : "") + id;
	}
	
}
