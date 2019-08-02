package daft.sat.result;

import daft.sat.Formula;

public class TimeoutResult extends Result {

	public TimeoutResult(Formula formula, long elapsedTime) {
		super(formula, Type.TIMEOUT, elapsedTime);
	}

	@Override
	public String toString() {
		return "TimeoutResult [elapsedTime=" + getElapsedTime() + "]";
	}

}
