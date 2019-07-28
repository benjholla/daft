package daft.sat;

public class UnitClause extends Clause {

	public UnitClause(Literal... literals) {
		super(literals);
		if(literals.length == 1) {
			throw new IllegalArgumentException("A unit clause must have exactle one literal");
		}
	}
	
}
