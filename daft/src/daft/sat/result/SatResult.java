package daft.sat.result;

import java.util.Map;

import daft.sat.Formula;
import daft.sat.Literal;

public class SatResult extends Result {
	private Map<Literal,Boolean> assignments;
	
	public SatResult(Formula formula, Map<Literal,Boolean> assignments, long elapsedTime) {
		super(formula, Type.SAT, elapsedTime);
		this.assignments = assignments;
	}
	
	public Map<Literal,Boolean> getAssignments(){
		return assignments;
	}
	
	public boolean isComplete() {
		return formula.getLiteralSet().size() == assignments.size();
	}

	@Override
	public String toString() {
		return "SatResult [elapsedTime=" + getElapsedTime() + ", assignments=" + assignments + "]";
	}
	
}