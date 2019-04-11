package edu.hm.wimmer.nils.scanner;

import edu.hm.cs.rs.compiler.toys.base.BaseScanner;
import edu.hm.cs.rs.compiler.toys.base.LexicalError;
import edu.hm.cs.rs.compiler.toys.base.Source;
import edu.hm.cs.rs.compiler.toys.base.TokenStream;

public class NilsScanner extends BaseScanner {

    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String NO_P_I = "abcdefghjklmnoqrstuvwxyz";
    private static final String NO_I = "abcdefghjklmnopqrstuvwxyz";
    private static final String LETTER_R = "abcdefghijklmnopqstuvwxyz0123456789";
    private static final String LETTER_I = "abcdefghjklmnopqrstuvwxyz0123456789";
    private static final String LETTER_N = "abcdefghijklmopqrstuvwxyz0123456789";
    private static final String LETTER_T = "abcdefghijklmnopqrsuvwxyz0123456789";
    private static final String KLAMMERN_AUF = "(";
    private static final String KLAMMERN_ZU = ")";
    private static final String WHITESPACE = " \n\r\t";
    private static final String LETTER_NUM = "abcdefghijklmnopqrstuvwxyz0123456789";

    public NilsScanner () {
        start("init");

        // States:

        // Print Token Zustände
        accept("p", "identifier");
        transition("init",'p',"p");

        accept("pr", "identifier");
        transition("p",'r',"pr");
        transition("p",LETTER_R,"identifier");

        accept("pri", "identifier");
        transition("pr",'i',"pri");
        transition("pr",LETTER_I,"identifier");

        accept("prin", "identifier");
        transition("pri",'n',"prin");
        transition("pri",LETTER_N,"identifier");

        accept("print");
        transition("prin",'t',"print");
        transition("prin",LETTER_T,"identifier");

        // Int Token Zustände
        accept("i", "identifier");
        transition("init",'i',"i");

        accept("in", "identifier");
        transition("i",'n',"in");
        transition("i", LETTER_N,"identifier");

        accept("int");
        transition("in",'t',"int");
        transition("in", LETTER_T,"identifier");


        // Identifier Token
        accept("identifier", true);
        transition("init",NO_P_I,"identifier");
        transition("identifier", LETTER_NUM,"identifier");

        // Numeral
        accept("numeral", true);
        transition("init",NUM,"numeral");
        transition("numeral",NUM,"numeral");

        // Assign Token
        accept("assign");
        transition("init",':',"dp");
        transition("dp",'=',"assign");

        // add
        accept("add");
        transition("init",'+',"add");

        // sub
        accept("sub");
        transition("init",'-',"sub");

        // mult
        accept("mult");
        transition("init",'*',"mult");

        // pot
        accept("pot");
        transition("mult",'*',"pot");

        // div
        accept("div");
        transition("init",'/',"div");

        // mod
        accept("mod");
        transition("init",'%',"mod");

        // open
        accept("open");
        transition("init",'(',"open");

        // close
        accept("close");
        transition("init",')',"close");

        // semicolon
        accept("semicolon");
        transition("init",';',"semicolon");

        // Whitespace
        acceptAndIgnore("whitespace");
        transition("init",' ', "whitespace");

        // New Line
        acceptAndIgnore("newLine");
        transition("init", "\n", "newLine");
    }


}