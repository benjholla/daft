package daft.sat;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Context context = new Context();
		
		Variable a = new Variable(context, "a");
		context.addVariable(a);
		
		Variable b = new Variable(context, "b");
		context.addVariable(b);
		
		Variable c = new Variable(context, "c");
		context.addVariable(c);
		
		Clause clause1 = new Clause(a, b.clone().negate(), c);
		Clause clause2 = new Clause(a, b, c);
		
		Formula formula = new Formula(clause1, clause2);
		System.out.println(formula);
		
		Map<Integer,Boolean> bindings = new HashMap<Integer,Boolean>();
		bindings.put(a.getId(), true);
		bindings.put(b.getId(), false);
		bindings.put(c.getId(), false);
		System.out.println(formula.evaluate(bindings));
	}
	
}
