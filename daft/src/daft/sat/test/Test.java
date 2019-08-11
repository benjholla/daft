package daft.sat.test;

import java.util.HashMap;
import java.util.Map;

import daft.sat.logic.Clause;
import daft.sat.logic.Formula;
import daft.sat.solver.Context;
import daft.sat.solver.NaiveSolver;
import daft.sat.solver.Variable;

public class Test {

	public static void main(String[] args) {
		Context context = new Context();
		
		Variable a = new Variable(context, "a");
		Variable b = new Variable(context, "b");
		Variable bNeg = b.getNegated();
		Variable c = new Variable(context, "c");
		Variable d = new Variable(context, "d");
		
//		Clause clause1 = new Clause(a, bNeg, c);
//		Clause clause2 = new Clause(b, d);
		
//		Formula formula = new Formula(clause1, clause2);
		Formula formula = new Formula(new Clause(a,b,c), new Clause(bNeg), new Clause(d));
		System.out.println("Formula: " + formula);
		
		Map<Integer,Boolean> bindings = new HashMap<Integer,Boolean>();
		bindings.put(a.getId(), false);
		bindings.put(b.getId(), true);
		bindings.put(c.getId(), false);
//		System.out.println(formula.evaluate(bindings));
		
		System.out.println(new NaiveSolver().solve(formula));
	}
	
}
