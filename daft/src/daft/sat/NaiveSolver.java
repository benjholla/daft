package daft.sat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class NaiveSolver {

	public static enum Result {
		SAT, UNSAT
	}
	
	public static class LiteralAssignment {

		private Literal literal;
		private boolean value;
		
		public LiteralAssignment(Literal literal, boolean value) {
			this.literal = literal;
			this.value = value;
		}

		public Literal getLiteral() {
			return literal;
		}

		public boolean getValue() {
			return value;
		}
		
	}
	
	private static final Boolean FIRST = false;
	private static final Boolean SECOND = true;
	
	public static Result solve(Formula formula) {
//		1) Pick a variable without an assigned truth value. If there are none, return SAT
//		2) Assign it a truth-value (true/false)
//		3) Check if all clauses in our formula are still potentially satisfiable
//			- If they are, go to step 1
//			- If they are not satisfiable, go to 2 and pick the other truth-value
//			- If they are not satisfiable, and both truth-values have been tried, backtrack
//			- If there is nowhere to backtrack, return UNSAT
		Queue<Literal> unassigned = new LinkedList<Literal>(formula.getLiteralSet());
		Map<Integer,Boolean> assignments = new HashMap<Integer,Boolean>();
		Stack<LiteralAssignment> search = new Stack<LiteralAssignment>();
//		List<BoundLiteral> trace = new LinkedList<BoundLiteral>();

		Literal literal = unassigned.remove();
		search.add(new LiteralAssignment(literal, FIRST));
		search.add(new LiteralAssignment(literal, SECOND));
		
		while(!search.isEmpty()) {
			LiteralAssignment assignment = search.pop();
			assignments.put(assignment.getLiteral().getId(), assignment.getValue());
			LogicalState state = formula.evaluate(assignments);
			if(state != LogicalState.FALSE) {
				// formula is potentially satisfiable
				if(unassigned.isEmpty()) {
					return Result.SAT;
				} else {
					literal = unassigned.remove();
					search.add(new LiteralAssignment(literal, FIRST));
					search.add(new LiteralAssignment(literal, SECOND));
				}
			}
		}
		
		return Result.UNSAT;
	}
	
}
