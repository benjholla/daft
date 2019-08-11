package daft.sat.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import daft.sat.solver.Unicode;

/**
 * A clause is a disjunction of a list of literals (a or b or c or ...).
 */
public class Clause implements Disjunction, Iterable<Literal> {

	protected final List<Literal> literals;
	protected final Set<Literal> literalSet;
	
	public Clause(Literal... literals) {
		if(literals.length == 0) {
			throw new IllegalArgumentException("A clause must have at least one literal");
		}
		this.literals = Arrays.asList(literals);
		this.literalSet = new HashSet<Literal>(this.literals);
	}
	
	public Clause(List<Literal> literals) {
		if(literals.size() == 0) {
			throw new IllegalArgumentException("A clause must have at least one literal");
		}
		this.literals = literals;
		this.literalSet = new HashSet<Literal>(this.literals);
	}
	
	@Override
	public Iterator<Literal> iterator() {
		return this.literals.iterator();
	}
	
	public String toString() {
		return "(" + literals.stream().map(Literal::toString).collect(Collectors.joining(" " + Unicode.OR + " ")) + ")";
	}

	@Override
	public Set<Literal> getLiteralSet() {
		return literalSet;
	}
	
	public Stream<Literal> stream() {
		return this.literals.stream();
	}

	public LogicalState evaluate(Map<Literal, Boolean> assignments) {
		LogicalState state = LogicalState.FALSE;
		for(Literal literal : literals) {
			state = state.or(literal.evaulate(assignments));
		}
		return state;
	}

}
