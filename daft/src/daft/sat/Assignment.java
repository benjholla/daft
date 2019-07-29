package daft.sat;

import java.util.Map;

/**
 * An assignment represents a truth value binding at particular point in the search tree
 */
public class Assignment {

	private Formula formula;
	private Map<Integer,Boolean> bindings;
	private int level;

	public Assignment(Formula formula, Map<Integer,Boolean> bindings, int level) {
		this.formula = formula;
		this.bindings = bindings;
		this.level = level;
	}
	
	public Formula getFormula() {
		return formula;
	}

	public Map<Integer, Boolean> getBindings() {
		return bindings;
	}

	public int getLevel() {
		return level;
	}
	
	public boolean isComplete() {
		// TODO: for efficiency the number of literals should be cached instead of computed at this point
		return formula.getLiterals().size() == bindings.size();
	}
	
}
