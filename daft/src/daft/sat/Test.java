package daft.sat;

public class Test {

	public static void main(String[] args) {
		Context context = new Context();
		
		Variable a = new Variable(context, "a");
		context.addVariable(a);
		
		Variable b = new Variable(context, "b");
		context.addVariable(b);
		
		Variable c = new Variable(context, "c");
		context.addVariable(c);
		
		Clause clause1 = new Clause(a, b.negate(), c);
		Clause clause2 = new Clause(a, b, c);
		
		Formula formula = new Formula(clause1, clause2);
		
		System.out.println(formula);
	}
	
}
