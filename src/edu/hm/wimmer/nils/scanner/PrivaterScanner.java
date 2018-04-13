package edu.hm.wimmer.nils.scanner;

import edu.hm.cs.rs.compiler.toys.base.BaseScanner;
import edu.hm.cs.rs.compiler.toys.base.LexicalError;
import edu.hm.cs.rs.compiler.toys.base.Source;
import edu.hm.cs.rs.compiler.toys.base.TokenStream;

public class PrivaterScanner extends BaseScanner {
	
	private static final String letter = "abcdefghijklmnopqrstuvwxyz";
	private static final String num = "0123456789";	
	private static final String letterR = "abcdefghijklmnopqstuvwxyz0123456789";
	private static final String letterI = "abcdefghjklmnopqrstuvwxyz0123456789";
	private static final String letterN = "abcdefghijklmopqrstuvwxyz0123456789";
	private static final String letterT = "abcdefghijklmnopqrsuvwxyz0123456789";
	private static final String letterP = "abcdefghijklmnoqrstuvwxyz";
	private static final String klammernAuf = "(";
	private static final String klammernZu = ")";
	private static final String whitespace = " \n\r\t";
	private static final String letterNum = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	public PrivaterScanner() {
		// TODO Auto-generated constructor stub
		start("init");
		accept("p","identifier");
		accept("pr","identifier");
		accept("pri","identifier");
		accept("prin","identifier");
		accept("print");
		accept("sub");
		accept("semicolon");
		accept("mod");
		accept("div");
		accept("numeral",true);
		accept("assign");
		accept("identifier",true);
		accept("add");
		accept("mult");
		accept("open");
		accept("close");
		acceptAndIgnore("ws");
		
		// Direct Transitions
		transition("init",klammernAuf, "open");
		transition("init",klammernZu, "close");
		transition("init",'*', "mult");
		transition("init",'+', "add");
		transition("init",';', "semicolon");
		transition("init",'%', "mod");
		transition("init",'/', "div");
		transition("init",'-', "sub");
		// Loops
		transition("init",num, "numeral");
		transition("numeral",num, "numeral");
		transition("init",letterP, "identifier");
		transition("identifier",letterNum, "identifier");
		//transition("identifier",num, "identifier");
		transition("init",whitespace,"ws");
		transition("ws",whitespace,"ws");
		// Longer Paths
		transition("init",':', "dp");
		transition("dp",'=', "assign");
		transition("init",'p', "p");
		transition("p",'r', "pr");
		transition("pr",'i', "pri");
		transition("pri",'n', "prin");
		transition("prin",'t', "print");
		transition("p",letterR,"identifier");
		transition("pr",letterI,"identifier");
		transition("pri",letterN,"identifier");
		transition("prin",letterT,"identifier");
		transition("print",letterNum,"identifier");
	}

	/*@Override
	public TokenStream scan(Source arg0) throws LexicalError {
		// TODO Auto-generated method stub
		return null;
	}*/

}
