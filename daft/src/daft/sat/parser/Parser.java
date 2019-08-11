package daft.sat.parser;

import java.io.File;
import java.io.FileNotFoundException;

import daft.sat.logic.Formula;

public interface Parser {

	public Formula parse(File file) throws FileNotFoundException;
	
}
