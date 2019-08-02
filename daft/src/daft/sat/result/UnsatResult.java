package daft.sat.result;

import daft.sat.Formula;

public class UnsatResult extends Result {
	
	public UnsatResult(Formula formula, long elapsedTimed) {
		super(formula, Type.UNSAT, elapsedTimed);
	}

	@Override
	public String toString() {
		return "UnsatResult [elapsedTime=" + getElapsedTime() + "]";
	}
	
}
