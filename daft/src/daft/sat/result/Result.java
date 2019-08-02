package daft.sat.result;

import daft.sat.Formula;

public abstract class Result {
	
	public static enum Type {
		SAT, UNSAT, TIMEOUT
	}
	
	protected Formula formula;
	protected long elapsedTime;
	protected Type type;
	
	public Result(Formula formula, Type type, long elapsedTime) {
		this.formula = formula;
		this.type = type;
		this.elapsedTime = elapsedTime;
	}
	
	public Formula getFormula() {
		return formula;
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}

	public Type getType() {
		return type;
	}

}
