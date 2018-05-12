

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.hm.cs.rs.compiler.lab04generator.LanguageGenerator;
import edu.hm.cs.rs.compiler.lab06rdparsergenerator.RDParserGenerator;

public class RDParser implements RDParserGenerator {

	private static final String GRAMMAR = "=,S=(SOS),S=F,O=+,O=-,F=n,F=-S";
//	private static final String GRAMMAR = "=,B=C,C=c,B=D,D=d,B=E,E=e,B=F,F=f,B=G,G=g,B=H,H=h,B=I,I=i,B=J,J=j,B=K,K=k,B=L,L=l,B=M,M=m,B=N,N=n,B=O,O=o,B=P,P=p,B=Q,Q=q,B=R,R=r,B=S,S=s,B=T,T=t,B=U,U=u,B=V,V=v,B=W,W=w,B=X,X=x,B=Y,Y=y,B=Z,Z=z";
	
	public static void main(final String... args) {
	    System.out.println(new RDParser().generate(GRAMMAR));
	}

	public RDParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String generate(String grammar) {
		// TODO Auto-generated method stub
		Stream<String[]> grammar2 = parseGrammar(grammar);
		List<String[]> grammatik = grammar2.collect(Collectors.toList());
		char startChar = grammar.charAt(2);
		String className = "RDParser" + startChar;	
		StringBuffer exceptionMessage = new StringBuffer("\t\t\tdefault: throw new SyntaxErrorException(\"One of the following characters expected: [");
		StringBuffer fertigesFile = new StringBuffer(String.format("import java.util.*;\n\npublic class %s {\n\n\tprivate String input;\n\tprivate int counter = 0;\n\n\tpublic %s() {};\n\n\tpublic Node parse(String input) throws SyntaxErrorException {\n\t\tthis.input = input;\n\t\treturn %c();\n\t}\n\n", className, className, startChar));		
		
		Map<String,List<String>> first = new HashMap<String,List<String>>();
		List <String> nonTerminalList = new ArrayList<String>();
		
		// Linke Terminale herausfinden
		for (String[] produktion : grammatik) {
			if (!nonTerminalList.contains(produktion[0])) {
				nonTerminalList.add(produktion[0]);
			}
		}
		// Beschreiben der Eintraege
		for (String nonTerminal : nonTerminalList) {
			List <String> test = new ArrayList<>();
			for (String[] gram : grammatik) {
		//		System.out.println(nonTerminal.equals(gram[0]));
				if (nonTerminal.equals(gram[0])) {
					test.add(gram[1].substring(0, 1));
				}
			}
		//	System.out.println(test);
			first.put(nonTerminal, test);
		}
		System.out.println(first.entrySet());
		boolean flag = true;
		while (flag) {
			flag = false;
			for (Map.Entry<String, List<String>> entry : first.entrySet()) {
				List <String> prodLeft = new ArrayList<>();
				System.out.println(entry);
					for (String fir : entry.getValue()) {
						//System.out.println(entry.getValue());
						// Steht ein Terminal oder NonTerminal da?
						if (fir.toLowerCase() != fir) {
							flag = true;
							// Nichtterminal
							if (first.containsKey(fir) && !entry.getKey().equals(fir)) {
								prodLeft.addAll(first.get(fir));
							// Terminal	
							} else {
								prodLeft.add(fir);
							}
						} else {
							prodLeft.add(fir);
						}
					}
					
					entry.setValue(prodLeft);
					System.out.println(entry.getValue());
//				}
				
			}
		}
		// Fuer jeden Eintrag eine Klasse Erstellen
		for (Map.Entry<String, List<String>> entry : first.entrySet()) {
			fertigesFile.append(String.format("\tprivate Node %c() throws SyntaxErrorException {\n\t\tswitch(input.charAt(counter)) {\n", entry.getKey().toCharArray()[0]));
			//System.out.println(first.values());
			for (int i = 0; i < entry.getValue().size();i++) {
				
				fertigesFile.append(String.format("\t\t\tcase '%c':\n\t\t\t\treturn new Node(\"%c\"" ,entry.getValue().get(i).toCharArray()[0], entry.getKey().toCharArray()[0]));
				fertigesFile.append(String.format(first.containsKey(entry.getKey()) ? ", %c()" : ", terminal('%c')", entry.getKey().toCharArray()[0]));
				fertigesFile.append(");\n");
				exceptionMessage.append(first);
			}
			exceptionMessage.append("] but was: \" + input.charAt(counter));\n");
			fertigesFile.append(exceptionMessage);
			fertigesFile.append("\t\t}\n\t}\n\n");
		}
		
		fertigesFile.append("private Node terminal(char c) throws SyntaxErrorException {\nif(input.charAt(counter++) != c) {\nthrow new SyntaxErrorException(String.format(\"Expected Character: %c, but was: %c\", c, input.charAt(counter - 1)));\n}\nreturn new Node(Character.toString(c));\n}\n};\n\n");
		fertigesFile.append(getSyntaxErrorExceptionSourcecode());
		fertigesFile.append(getNodeSourcecode());
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(className + ".class"), false))) {
			bw.write(fertigesFile.toString());
		}
		catch(IOException e) {}
		System.out.println(first.toString());
		return fertigesFile.toString();
		
	}




	public Stream<String[]> parseGrammar(String grammarString) {
		char sep_side = grammarString.charAt(0);
		char sep_prod = grammarString.charAt(1);		
		return LanguageGenerator.prettyprint(Arrays.stream(grammarString.substring(2).split(Character.toString(sep_prod))).map(x -> x.split(Character.toString(sep_side), 2)));
	}
}
