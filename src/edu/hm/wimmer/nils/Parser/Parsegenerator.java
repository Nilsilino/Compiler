package edu.hm.wimmer.nils.Parser;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.hm.cs.rs.compiler.lab06rdparsergenerator.RDParserGenerator;

public class Parsegenerator implements RDParserGenerator {
	
	private static final String GRAMMAR = "=,E=(EOE),E=F,O=+,O=-,F=n,F=-E";
//	private static final String GRAMMAR = "=,B=C,C=c,B=D,D=d,B=E,E=e,B=F,F=f,B=G,G=g,B=H,H=h,B=I,I=i,B=J,J=j,B=K,K=k,B=L,L=l,B=M,M=m,B=N,N=n,B=O,O=o,B=P,P=p,B=Q,Q=q,B=R,R=r,B=S,S=s,B=T,T=t,B=U,U=u,B=V,V=v,B=W,W=w,B=X,X=x,B=Y,Y=y,B=Z,Z=z";
	
	public static void main(final String... args) {
	    System.out.println(new Parsegenerator().generate(GRAMMAR));
	}

	@Override
	public String generate(String grammar) {
		char sep_side = grammar.charAt(0);
		char sep_prod = grammar.charAt(1);	
		char startChar = grammar.charAt(2);
		
		Map<Character, Map<String, List<Character>>> map = Arrays.stream(grammar.substring(2).split(Character.toString(sep_prod))).
				map(x -> x.split(Character.toString(sep_side), 2)).
				collect(Collectors.groupingBy(x -> x[0].charAt(0), Collectors.mapping(x -> x[1], Collectors.toMap(x -> x, x -> new ArrayList<Character>()))));		
		
		while(map.values().stream().
				anyMatch(e -> e.keySet().stream().
						filter(key -> !e.get(key).isEmpty() ? false :  !map.containsKey(key.charAt(0)) ? e.get(key).add(key.charAt(0)) : map.get(key.charAt(0)).values().stream().filter(l -> !l.isEmpty()).
								peek(l -> e.get(key).addAll(l)).count() != 0)
						.count() != 0)) {}
		
//		boolean changes = true;
//		while(changes == true) {
//			changes = false;
			// Produktion 
//			for(Map<String, List<Character>> e : map.values()) {
//				
//				for(String key : e.keySet()) {
//					List<Character> list = e.get(key);
//					if(list.isEmpty()) {
//						char first = key.charAt(0);
//						if(!map.containsKey(key.charAt(0))) {							
//							list.add(first);
//							changes = true;
//						}
//						else {
//							for(List<Character> l : map.get(first).values()) {
//								if(!l.isEmpty()) {
//									list.addAll(l);
//									changes = true;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
		
		String className = "RDParser" + startChar;		
		StringBuffer sb = new StringBuffer(String.format("import java.util.*;\n\npublic class %s {\n\n\tprivate String input;\n\tprivate int counter = 0;\n\n\tpublic %s() {};\n\n\tpublic Node parse(String input) throws SyntaxErrorException {\n\t\tthis.input = input;\n\t\treturn %c();\n\t}\n\n", className, className, startChar));		
		
		map.entrySet().stream().forEach(entry -> {
			Character left = entry.getKey();
			
			sb.append(String.format("\tprivate Node %c() throws SyntaxErrorException {\n\t\tswitch(input.charAt(counter)) {\n", left));
			
			StringBuffer exceptionMessage = new StringBuffer("\t\t\tdefault: throw new SyntaxErrorException(\"One of the following characters expected: [");
			
			entry.getValue().entrySet().stream().forEach(
					innerEntry -> innerEntry.getValue().stream().forEach(
							first -> {
				sb.append(String.format("\t\t\tcase '%c':\n\t\t\t\treturn new Node(\"%c\"", first, left));
				innerEntry.getKey().chars().forEach(c -> sb.append(String.format(map.containsKey((char) c) ? ", %c()" : ", terminal('%c')", c)));
				sb.append(");\n");
				exceptionMessage.append(first);
			}));
			exceptionMessage.append("] but was: \" + input.charAt(counter));\n");
			sb.append(exceptionMessage);
			sb.append("\t\t}\n\t}\n\n");
		});
		
		sb.append("private Node terminal(char c) throws SyntaxErrorException {\nif(input.charAt(counter++) != c) {\nthrow new SyntaxErrorException(String.format(\"Expected Character: %c, but was: %c\", c, input.charAt(counter - 1)));\n}\nreturn new Node(Character.toString(c));\n}\n};\n\n");
		sb.append(getSyntaxErrorExceptionSourcecode());
		sb.append(getNodeSourcecode());
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(className + ".java"), false))) {
			bw.write(sb.toString());
		}
		catch(IOException e) {}
		System.out.println(map.toString());
		return sb.toString();
	}
}
