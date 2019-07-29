package daft.sat;

import java.util.HashSet;
import java.util.Set;

public class Context {

	private Set<Variable> variables = new HashSet<Variable>();
	
	public int getNumVariables() {
		return variables.size();
	}
	
	void add(Variable variable) {
		if(variables.contains(variable)) {
			throw new IllegalArgumentException("Context already contains the given variable");
		} else {
			variables.add(variable);
		}
	}
	
}
