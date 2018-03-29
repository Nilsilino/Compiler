package edu.hm.wimmer.nils.praeprocessor;

import edu.hm.cs.rs.compiler.toys.base.LexicalError;
import edu.hm.cs.rs.compiler.toys.base.Preprocessor;
import edu.hm.cs.rs.compiler.toys.base.Source;

public class PraeProcessor implements Preprocessor {
	
	
	private int zeilen = 0;
	
	enum State {
		START, SLASH, SLASH_SLASH, SLASH_STAR, SLASH_STAR_STAR, SLASH_STAR_STAR_SLASH,ZEILEN_KOMMENTAR,BLOCK_KOMMENTAR,KEIN_KOMMENTAR
	}
	
	public PraeProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Source process(Source unprocess) throws LexicalError {
		// TODO Auto-generated method stub
		State currentState;
		String combined = "";
		String beforBlock = "";
		Source process = new Source(); 
		currentState = State.START;
		while (unprocess.hasMore()) {
			char character = unprocess.getNextChar();
			switch (character) {
			case '/':
				if (currentState == State.SLASH) {
					currentState = State.SLASH_SLASH;
					beforBlock = combined;
					break;
				} else if (currentState == State.SLASH_SLASH) {
					combined = combined + character;
					break;
				} else if (currentState == State.SLASH_STAR_STAR) {
					currentState = State.START;
					combined = beforBlock + " ";
					break;
				} else {
					beforBlock = combined;
					currentState = State.SLASH;
				}
				combined = combined + character;
				break;
			case '*':
				if (currentState == State.SLASH) {
					currentState = State.SLASH_STAR;
				} else if (currentState == State.SLASH_STAR) {
					currentState = State.SLASH_STAR_STAR;
				}
				combined = combined + character;
				break;
			case '\n':
				if (currentState == State.START) {
					//System.out.println(combined);
					combined += character;
					process.append(combined);
					combined = "";
					break;
				} else if ( currentState == State.SLASH_SLASH) {
					// Hier fehlt noch Zeilenübersetzung
					zeilen++;
					combined = "\n";
					process.append(combined);
					currentState = State.START;
					break;
				} else {
					// Hier fehlt noch Zeilenübersetzung
					combined = "\n";
					process.append(combined);
					zeilen++;
					break;
				}	
			default:
				if (currentState == State.SLASH) {
					currentState = State.START;
				}
				combined = combined + character;
			}
			
		}
		return process;
	}

}
