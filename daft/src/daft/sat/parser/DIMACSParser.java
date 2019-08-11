package daft.sat.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import daft.sat.logic.Clause;
import daft.sat.logic.Formula;
import daft.sat.logic.Literal;
import daft.sat.solver.Context;
import daft.sat.solver.Variable;

/**
 * Simple parser for DIMACS format https://people.sc.fsu.edu/~jburkardt/data/cnf/cnf.html
 */
public class DIMACSParser implements Parser {

	@Override
	public Formula parse(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		Context context = new Context();
		Map<String,Variable> variables = new HashMap<String,Variable>();
		int numVariables = -1;
		int numClauses = -1;
		int numClausesFound = 0;
		List<Literal> clauseVariables = new ArrayList<Literal>();
		List<Clause> clauses = new ArrayList<Clause>();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			if(line.startsWith("c")) {
				continue;
			} else if(line.startsWith("p")) {
				if(!line.contains("cnf")) {
					scanner.close();
					throw new RuntimeException("Currently only CNF form is accepted");
				} else {
					String[] parts = normalize(line);
					numVariables = Integer.parseInt(parts[2]);
					numClauses = Integer.parseInt(parts[3]);
				}
				continue;
			} else if(line.startsWith("%")) {
				// end of file
				if(!clauseVariables.isEmpty()) {
					clauses.add(new Clause(clauseVariables));
					numClausesFound++;
				}
				break;
			} else if(!line.trim().isEmpty()){
				String[] parts = normalize(line);
				for(int i=0; i<parts.length; i++) {
					if(parts[i].equals("0")) {
						// clauses may span multiple lines, but end with a 0
						clauses.add(new Clause(clauseVariables));
						clauseVariables = new ArrayList<Literal>();
						numClausesFound++;
					} else {
						String variableName = parts[i];
						Variable variable = variables.get(variableName);
						if(variable == null) {
							String strippedVariableName = variableName.replace("-", "");
							Variable v = new Variable(context, strippedVariableName);
							variables.put(strippedVariableName, v);
							variables.put("-" + strippedVariableName, v.clone().negate());
							variable = variables.get(variableName);
						}
						clauseVariables.add(variable);
					}
				}
			}
		}
		scanner.close();
		
		if(numClausesFound != numClauses) {
			throw new RuntimeException("Expected " + numClauses + " clauses, but found " + numClausesFound);
		}
		
		if((variables.size()/2) != numVariables) {
			throw new RuntimeException("Expected " + numVariables + " variables, but found " + (variables.size()/2));
		}
		
		return new Formula(clauses);
	}

	private String[] normalize(String line) {
		return line.trim().replaceAll("\\s"," ").replace("  "," ").replace("  "," ").split(" ");
	}

}
