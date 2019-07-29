package daft.sat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A clause is a disjunction of a list of literals (a or b or c or ...).
 */
public class Clause implements Disjunction, Iterable<Literal> {

	protected final LinkedHashSet<Literal> literals;
	
	public Clause(Literal... literals) {
		if(literals.length == 0) {
			throw new IllegalArgumentException("A clause must have at least one literal");
		}
		this.literals = new LinkedHashSet<Literal>();
		for(Literal literal : literals) {
			this.literals.add(literal);
		}
	}
	
	@Override
	public Iterator<Literal> iterator() {
		return this.literals.iterator();
	}
	
	public String toString() {
		return "(" + literals.stream().map(Literal::toString).collect(Collectors.joining(" " + Unicode.OR + " ")) + ")";
	}

	@Override
	public Set<Literal> getLiterals() {
		return new HashSet<Literal>(literals);
	}
	
	 public Stream<Literal> stream() {
	        return this.literals.stream();
	    }

	public LogicalState evaluate(Map<Integer, Boolean> bindings) {
		LogicalState state = LogicalState.FALSE;
		for(Literal literal : literals) {
			state = state.or(literal.evaulate(bindings));
		}
		return state;
	}

}
