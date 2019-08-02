package daft.sat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A conjunction of clauses (c1 and c2 and ... cn) which form a CNF formula
 */
public class Formula implements Conjunction, Iterable<Clause> {

	protected final List<Clause> clauses;
	protected final Set<Literal> literalSet;
	
	public Formula(Clause... clauses) {
		if(clauses.length == 0) {
			throw new IllegalArgumentException("A formula must have at least one clause");
		}
		this.clauses = Arrays.asList(clauses);
		literalSet = new HashSet<Literal>();
		for(Clause clause : clauses) {
			literalSet.addAll(clause.getLiteralSet());
		}
	}
	
	@Override
	public Iterator<Clause> iterator() {
		return this.clauses.iterator();
	}
	
	public Stream<Clause> stream() {
		return this.clauses.stream();
	}
	
	@Override
	public String toString() {
		return "(" + clauses.stream().map(Clause::toString).collect(Collectors.joining(" " + Unicode.AND + " ")) + ")";
	}
	
	@Override
	public Set<Literal> getLiteralSet() {
		return literalSet;
	}
	
	public LogicalState evaluate(Map<Literal,Boolean> assignments) {
		LogicalState state = LogicalState.TRUE;
		for(Clause clause : clauses) {
			state = state.and(clause.evaluate(assignments));
			if(state == LogicalState.FALSE || state == LogicalState.UNBOUND) {
				return state;
			}
		}
		return state;
	}

}
