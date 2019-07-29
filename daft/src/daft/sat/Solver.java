package daft.sat;

import java.util.ArrayList;

public class Solver {

	public boolean solve(Formula formula) {
		boolean sat = false;
		
		ArrayList<Literal> worklist = new ArrayList<Literal>(formula.getLiterals());
		ArrayList<Assignment> searchTree = new ArrayList<Assignment>();
		ArrayList<Clause> learnedClauses = new ArrayList<Clause>();
		
		
		return sat;
	}
	
}
