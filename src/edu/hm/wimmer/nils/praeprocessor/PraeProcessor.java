package edu.hm.wimmer.nils.praeprocessor;

import edu.hm.cs.rs.compiler.toys.base.LexicalError;
import edu.hm.cs.rs.compiler.toys.base.Preprocessor;
import edu.hm.cs.rs.compiler.toys.base.Source;

public class PraeProcessor implements Preprocessor {
	
	private State currentState;
	
	enum State {
		START, SLASH, SLASH_SLASH, SLASH_STAR, SLASH_STAR_STAR, SLASH_STAR_STAR_SLASH
	}
	
	public PraeProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Source process(Source unprocess) throws LexicalError {
		// TODO Auto-generated method stub
		String combined = "";
		Source process = new Source(); 
		currentState = State.START;
		while (unprocess.hasMore()) {
			char character = unprocess.getNextChar();
			switch (character) {
			case '/':
				if (currentState == State.SLASH) {
					currentState = State.SLASH_SLASH;
				} else if (currentState == State.SLASH_SLASH) {
					break;
				} else if (currentState == State.SLASH_STAR_STAR) {
					currentState = State.SLASH_STAR_STAR_SLASH;
				} else {
					currentState = State.SLASH;
				}
				break;
			case '*':
				if (currentState == State.SLASH) {
					currentState = State.SLASH_STAR;
				} else if (currentState == State.SLASH_STAR) {
					currentState = State.SLASH_STAR_STAR;
				}
				break;
				
			}
		}
		return process;
	}

}
