

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

//	private static final String GRAMMAR = "=,E=(EOE),E=F,O=+,O=-,F=n,F=-E";
//	private static final String GRAMMAR = "=;S=a";
	private static final String GRAMMAR = "=,B=C,C=c,B=D,D=d,B=E,E=e,B=F,F=f,B=G,G=g,B=H,H=h,B=I,I=i,B=J,J=j,B=K,K=k,B=L,L=l,B=M,M=m,B=N,N=n,B=O,O=o,B=P,P=p,B=Q,Q=q,B=R,R=r,B=S,S=s,B=T,T=t,B=U,U=u,B=V,V=v,B=W,W=w,B=X,X=x,B=Y,Y=y,B=Z,Z=z";
	
	public static void main(final String... args) {
	    System.out.println(new RDParser().generate(GRAMMAR));
	}

	public RDParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String generate(String grammar) {
		// TODO Auto-generated method stub
		Stream<String[]> grammar2;
		String grammmar = grammar;
		char startChar = grammar.charAt(0);
		char startProd = grammar.charAt(2);
		grammar2 = parseGrammar(grammar);
		List<String[]> grammatik = grammar2.collect(Collectors.toList());
		String className = "RDParser" + startProd;	
		
		StringBuffer fertigesFile = new StringBuffer(String.format("import java.util.*;\n\npublic class %s {\n\n\tprivate String input;\n\tprivate int counter = 0;\n\n\tpublic %s() {}\n\n\tpublic Node parse(String input) throws SyntaxErrorException {\n\t\tthis.input = input;\n\t\treturn %c();\n\t}\n\n", className, className, startProd));		
		
		Map<String,List<String>> second = new HashMap<String,List<String>>();
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
				
				if (nonTerminal.equals(gram[0])) {
					if (gram[1].length() >= 1) {
						test.add(gram[1].substring(0, 1));
					} else {
						test.add(gram[1]);
					}
					
					
				}
			}
//			System.out.println(test);
			second.put(nonTerminal, test);
		}
		Map<String,List<String>> first = second;

		boolean flag = true;
		while (flag) {
		flag = false;
//			System.out.println(flag);
		if (first.size() > 1) {
				for (Map.Entry<String, List<String>> entry : first.entrySet()) {
				List <String> prodLeft = new ArrayList<>();
////				System.out.println(entry);
					for (String fir : entry.getValue()) {
						//System.out.println(entry.getValue());
						// Steht ein Terminal oder NonTerminal da?
						if (fir.toLowerCase() != fir) {
							flag = true;
//							// Nichtterminal
							if (first.containsKey(fir) && !entry.getKey().equals(fir)) {
								prodLeft.addAll(first.get(fir));
//							// Terminal	
							} else {
								prodLeft.add(fir);
							}
						} else {
							
							prodLeft.add(fir);
						}
					}
					
					entry.setValue(prodLeft);

				
			}
		}
		}
		// Fuer jeden Eintrag eine Klasse Erstellen
			for (Map.Entry<String, List<String>> entry : first.entrySet()) {
				StringBuffer exceptionMessage = new StringBuffer("\t\t\tdefault: throw new SyntaxErrorException(\"One of the following characters expected: [");
				fertigesFile.append(String.format("\tprivate Node %c() throws SyntaxErrorException {\n\t\tswitch(input.charAt(counter)) {\n", entry.getKey().toCharArray()[0]));
			//System.out.println(first.values());
			for (int i = 0; i < entry.getValue().size();i++) {
				boolean test = true;
				fertigesFile.append(String.format("\t\t\tcase '%c':\n\t\t\t\treturn new Node(\"%c\"" ,entry.getValue().get(i).toCharArray()[0], entry.getKey().toCharArray()[0]));
				for (String[] grammat : grammatik) {
						if (grammat[1].startsWith(entry.getValue().get(i)) && grammat[0].startsWith(entry.getKey())) {
							test = false;
							for (char grammarBustabe : grammat[1].toCharArray())  {
								fertigesFile.append(String.format(first.containsKey(Character.toString(grammarBustabe)) ? ", %c()" : ", terminal('%c')", grammarBustabe));
							}

						} 
											
				}
				if (test) {
					String hochKlettern = entry.getValue().get(i);
					System.out.println(hochKlettern);
					boolean flag2 = true;
					while (flag2) {
						boolean changed = false;


						for (String key : second.keySet()) {

							if (second.get(key).contains(hochKlettern)) {
								if (key == entry.getKey()) {
									flag2 = false;
								} else {
									changed = true;
									hochKlettern = key;
									System.out.println(key);
									System.out.println(hochKlettern);
								}							
							}
						}
						System.out.println("Läuft....");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
						e.printStackTrace();
						}
 
					}
					
					System.out.println(second.keySet());
					fertigesFile.append(String.format(", %c()", hochKlettern.toCharArray()[0]));
				}
				fertigesFile.append(");\n");
				
			}
			exceptionMessage.append(entry.getValue());
			exceptionMessage.append("] but was: \" + input.charAt(counter));\n");
			fertigesFile.append(exceptionMessage);
			fertigesFile.append("\t\t}\n\t}\n\n");
		}

		
		fertigesFile.append("private Node terminal(char c) throws SyntaxErrorException {\n\tif(input.charAt(counter++) != c) {\n\t\tthrow new SyntaxErrorException(String.format(\"Expected Character: %c, but was: %c\", c, input.charAt(counter - 1)));\n\t}\n\treturn new Node(Character.toString(c));\n\t}\n}\n\n");
		fertigesFile.append(getSyntaxErrorExceptionSourcecode());
		fertigesFile.append(getNodeSourcecode());
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(className + ".java"), false))) {
			bw.write(fertigesFile.toString());
		}
		catch(IOException e) {}
//		System.out.println(first.toString());
		return fertigesFile.toString();
		
	}




	public Stream<String[]> parseGrammar(String grammarString) {
		char sep_side = grammarString.charAt(0);
		char sep_prod = grammarString.charAt(1);
		String temp = grammarString.substring(2);
		return LanguageGenerator.prettyprint(Arrays.stream(grammarString.substring(2).split(Character.toString(sep_prod))).map(x -> x.split(Character.toString(sep_side), 2)));

	}
}
