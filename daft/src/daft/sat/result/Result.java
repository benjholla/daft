package daft.sat.result;

import java.util.concurrent.TimeUnit;

import daft.sat.logic.Formula;

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

	public String getDisplayTime() {
		if(getTimeHours() > 1) {
			return getTimeHours() + " hours";
		} else if(getTimeMinutes() > 1) {
			return getTimeMinutes() + " minutes";
		} else if(getTimeSeconds() > 1) {
			return getTimeSeconds() + " seconds";
		} else if(getTimeMilliseconds() > 1) {
			return getTimeMilliseconds() + " milliseconds";
		} else if(getTimeMicroseconds() > 1) {
			return getTimeMicroseconds() + " microseconds";
		} else {
			return getTimeNanoseconds() + " nanoseconds";
		}
	}
	
	public Type getType() {
		return type;
	}
	
	public long getTimeHours() {
		return TimeUnit.HOURS.convert(getElapsedTime(), TimeUnit.NANOSECONDS);
	}
	
	public long getTimeMinutes() {
		return TimeUnit.MINUTES.convert(getElapsedTime(), TimeUnit.NANOSECONDS);
	}
	
	public long getTimeSeconds() {
		return TimeUnit.SECONDS.convert(getElapsedTime(), TimeUnit.NANOSECONDS);
	}
	
	public long getTimeMilliseconds() {
		return TimeUnit.MILLISECONDS.convert(getElapsedTime(), TimeUnit.NANOSECONDS);
	}
	
	public long getTimeMicroseconds() {
		return TimeUnit.MICROSECONDS.convert(getElapsedTime(), TimeUnit.NANOSECONDS);
	}
	
	public long getTimeNanoseconds() {
		return getElapsedTime();
	}

}
