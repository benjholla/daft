package daft.sat.test;

import java.io.File;
import java.io.FileNotFoundException;

import daft.sat.logic.Formula;
import daft.sat.parser.DIMACSParser;
import daft.sat.result.Result;
import daft.sat.solver.NaiveSolver;

/**
 * https://www.cs.ubc.ca/~hoos/SATLIB/benchm.html
 */
public class Test2 {

	public static void main(String[] args) throws FileNotFoundException {
		File testDir = new File("." + File.separator + "benchmarks" + File.separator + "sat" + File.separator + "simple");
		for(File file : testDir.listFiles()) {
			if(file.getName().toLowerCase().endsWith(".cnf") || file.getName().toLowerCase().endsWith(".dimacs")) {
//				Formula formula = new DIMACSParser().parse(new File("./benchmarks/sat/2.cnf"));
				try {
					System.out.println(file.getName());
					Formula formula = new DIMACSParser().parse(file);
//					System.out.println(formula);
					Result result = new NaiveSolver().solve(formula);
					System.out.println(result + "\n");
					if(result.getType() == Result.Type.UNSAT){
						throw new RuntimeException("Expected SAT");
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
		
	}

}
