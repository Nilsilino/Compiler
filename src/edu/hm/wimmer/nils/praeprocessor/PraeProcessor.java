package edu.hm.wimmer.nils.praeprocessor;

import edu.hm.cs.rs.compiler.toys.base.LexicalError;
import edu.hm.cs.rs.compiler.toys.base.Preprocessor;
import edu.hm.cs.rs.compiler.toys.base.Source;

public class PraeProcessor implements Preprocessor {

	public PraeProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Source process(Source unprocess) throws LexicalError {
		// TODO Auto-generated method stub
		Source process = new Source(); //Zeilenkommentar
		while (unprocess.hasMore()) {
			char character = unprocess.getNextChar();
			if (character == ' ') {
				process.append("x");
			} else {
				process.append(character);
			}
		}
		return process;
	}

}
