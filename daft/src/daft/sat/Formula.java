package daft.sat;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * A conjunction of clauses (c1 and c2 and ... cn) which form a CNF formula
 */
public class Formula implements Iterable<Clause> {

	private final LinkedHashSet<Clause> clauses;
	
	public Formula(Clause... clauses) {
		if(clauses.length == 0) {
			throw new IllegalArgumentException("A formula must have at least one clause");
		}
		this.clauses = new LinkedHashSet<Clause>();
		for(Clause clause : clauses) {
			this.clauses.add(clause);
		}
	}
	
	@Override
	public Iterator<Clause> iterator() {
		return this.clauses.iterator();
	}
	
	@Override
	public String toString() {
		return "(" + clauses.stream().map(Clause::toString).collect(Collectors.joining(" " + Unicode.AND + " ")) + ")";
	}

}
