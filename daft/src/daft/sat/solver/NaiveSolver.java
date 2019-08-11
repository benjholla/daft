package daft.sat.solver;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import daft.sat.logic.Formula;
import daft.sat.logic.Literal;
import daft.sat.logic.LogicalState;
import daft.sat.result.Result;
import daft.sat.result.SatResult;
import daft.sat.result.UnsatResult;

public class NaiveSolver extends Solver {
	
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

		@Override
		public String toString() {
			return literal + "=" + value;
		}
		
	}
	
	private static final Boolean FIRST = true;
	private static final Boolean SECOND = !FIRST;
	
	protected Result run(Formula formula) {
		long start = System.nanoTime();
//		1) Pick a variable without an assigned truth value. If there are none, return SAT
//		2) Assign it a truth-value (true/false)
//		3) Check if all clauses in our formula are still potentially satisfiable
//			- If they are, go to step 1
//			- If they are not satisfiable, go to 2 and pick the other truth-value
//			- If they are not satisfiable, and both truth-values have been tried, backtrack
//			- If there is nowhere to backtrack, return UNSAT
		
		LinkedList<Literal> unassigned = new LinkedList<Literal>(formula.getLiteralSet());
//		LinkedList<LiteralAssignment> trace = new LinkedList<LiteralAssignment>();
		
		// TODO: decide the search order of unassigned variables
		Collections.sort(unassigned, new Comparator<Literal>() {
			@Override
			public int compare(Literal l1, Literal l2) {
				return Integer.compare(l1.getId(), l2.getId());
			}
		});
//		System.out.println("Literal Search Order: " + unassigned);
		
		Map<Literal,Integer> maxGuesses = new HashMap<Literal,Integer>();
		Map<Literal,Integer> guesses = new HashMap<Literal,Integer>();
		
		// initialize max guess counts and initial guess counts
		int level = 1;
		for(Literal literal : unassigned) {
			// max guesses for a variable is the level * 2
			maxGuesses.put(literal, (level++) * 2);
		}
		
//		// initialize to the first level (zero indexed)
//		level = 0;

		Map<Literal,Boolean> assignments = new HashMap<Literal,Boolean>();
		Literal literal = unassigned.remove();
		
		Stack<LiteralAssignment> search = new Stack<LiteralAssignment>();
		search.add(new LiteralAssignment(literal, SECOND));
		search.add(new LiteralAssignment(literal, FIRST));
		
		// TODO: we could probably calculate when to add the second assignment to the list
		// instead of adding them as we go and letting the stack get big
		
		while(!search.isEmpty()) {
//			System.out.println("Searching: " + search);
			
			// get the next literal assignment to try
			LiteralAssignment assignment = search.pop();
			
			// increment the number of guesses we have completed for the literal
			Integer previousGuesses = guesses.get(assignment.getLiteral());
			if(previousGuesses == null) {
				previousGuesses = 0;
			}
			previousGuesses=previousGuesses+1;
			guesses.put(assignment.getLiteral(), previousGuesses);
			
			// check the guess result
			assignments.put(assignment.getLiteral(), assignment.getValue());
//			System.out.println("Trying: " + assignments);
			
			LogicalState state = formula.evaluate(assignments);
			if(state == LogicalState.TRUE) {
				// sat assignment found
				long stop = System.nanoTime();
				return new SatResult(formula, assignments, (stop-start));
			} else if(state == LogicalState.UNBOUND) {
				// formula is still potentially satisfiable
				literal = unassigned.remove();
				Integer nextGuesses =  guesses.get(literal);
				if(nextGuesses == null) {
					nextGuesses = 0;
				}
				if(nextGuesses + 2 <= maxGuesses.get(literal)) {
					search.add(new LiteralAssignment(literal, SECOND));
					search.add(new LiteralAssignment(literal, FIRST));
				}
			}  else {
				// contradiction found
//				System.out.println("Conflict.");
				assignments.remove(assignment.getLiteral());
				unassigned.add(assignment.getLiteral());
//				if(previousGuesses % 2 == 1){
//					// try the other truth value
//					System.out.println("Trying other truth value for " + assignment.getLiteral());
//				} else {
//					// backtrack
//					System.out.println("Backtracking...");
//				}
			}
		}
		
		long stop = System.nanoTime();
		return new UnsatResult(formula, (stop-start));
	}
	
}
