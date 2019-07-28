package daft.sat;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * A clause is a disjunction of a list of literals (a or b or c or ...).
 */
public class Clause implements Iterable<Literal> {

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

}
