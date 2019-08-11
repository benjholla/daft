package daft.sat.solver;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import daft.sat.logic.Formula;
import daft.sat.result.Result;
import daft.sat.result.TimeoutResult;

public abstract class Solver {
	
	private static final long NO_TIMEOUT = 0;
	
	public Result solve(final Formula formula) {
		return solve(formula, NO_TIMEOUT);
	}
	
	public Result solve(final Formula formula, final long timeout) {
		if(timeout <= 0) {
			return run(formula);
		} else {
			final ExecutorService executor = Executors.newSingleThreadExecutor();
			final Future<Result> future = executor.submit(new Callable<Result>() {
				@Override
				public Result call() throws Exception {
					return run(formula);
				}
			});
			// prevent new tasks from being added
			executor.shutdown();
			try {
				return future.get(timeout, TimeUnit.NANOSECONDS);
			} catch (InterruptedException ie) {
				throw new RuntimeException(ie);
			} catch (ExecutionException ee) {
				throw new RuntimeException(ee);
			} catch (TimeoutException te) {
				if (!executor.isTerminated()) {
					executor.shutdownNow();
				}
				return new TimeoutResult(formula, timeout);
			}
		}
	}
	
	protected abstract Result run(Formula formula);

}
