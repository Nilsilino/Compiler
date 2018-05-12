package edu.hm.wimmer.nils.sprachgenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.hm.cs.rs.compiler.lab04generator.LanguageGenerator;

public class Sprachgenerator implements LanguageGenerator{

	public static void main(String... args) {
		LanguageGenerator generator = new Sprachgenerator();
		double start = System.currentTimeMillis();
		System.setProperty("prettyprint", "blaBLABLAJDFKALNKL");
		Stream<String[]> grammar = generator.parseGrammar("=,S=a,S=b");
		grammar = LanguageGenerator.prettyprint(grammar);
		Stream<String> saetze = generator.generate(grammar, 10);
		printStream(saetze);
		grammar.close();
		saetze.close();
		System.out.println("Zeit vergangen: " + ((System.currentTimeMillis()-start)/1000) + " seconds");
	}
	   
	private static void printStream(Stream<String> saetze) {
		// TODO Auto-generated method stub
		for(Object s : saetze.toArray()) {
			System.out.println(s);
		}
	}


	

	/** Generiert alle Woerter der Sprache der Grammatik bis zur gegebenen Laenge.
     * Start ist die linke Seite der ersten Produktion.
     * @param grammar Typ-1-Grammatik.
     * @param uptoLength Maximale Laenge der Woerter der Sprache der Grammatik.
     * @return Alle Woerter der Sprache bis (einschliesslich) zur Laenge uptoLength,
     * sortiert nach steigender Laenge und bei gleicher Laenge alphabetisch.
     */
	@Override
	public Stream<String> generate(Stream<String[]> grammar, int uptoLength) {

		List<String[]> grammatik = grammar.collect(Collectors.toList());
		List<String> ausdruecke = new ArrayList<String>();
		ausdruecke.add(grammatik.get(0)[0]); 
		//System.out.println(grammatik.get(0)[0]);
		//List<String> ausdruecke = getStartZeichen(grammatik);
		List <String> saetze = new ArrayList<String>();

		int oldlength = 0;
		int length = ausdruecke.size();
		String ausdruck;
		while (oldlength < length) {
			for (int i = oldlength; i < length;i++) {
				ausdruck = ausdruecke.get(i);
				if (ausdruck.equals(ausdruck.toLowerCase())) {
					saetze.add(ausdruck);
				} else {
					doProductions(ausdruck,ausdruecke,grammatik, uptoLength);
				}
				
				
			}
		oldlength = length;
		length = ausdruecke.size();
		}
		sortList(saetze);
		return saetze.stream();
	}
	
	
	 private void doProductions(String ausdruck, List<String> ausdruecke, List<String[]> grammatik, int uptoLength) {
		// TODO Auto-generated method stub
			String neueAusdruecke = ausdruck;
				for (String[] s2 : grammatik) {
					neueAusdruecke = ausdruck.replaceFirst(s2[0], s2[1]);
					if (neueAusdruecke.length() <= uptoLength && !ausdruecke.contains(neueAusdruecke)) {
						ausdruecke.add(neueAusdruecke);
					}
					System.out.println(ausdruecke);
		}	
	}

	private void sortList(List<String> saetze) {
		// TODO Auto-generated method stub
			saetze.sort(new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					if(s1.length() != s2.length()) {
						return s1.length() - s2.length();
					}
					else {
						return s1.compareTo(s2);
					}
				}
				
			});
	}

	private List<String> getStartZeichen(List<String[]> grammatik) {
		// TODO Auto-generated method stub
		 List<String> ausdruecke = new ArrayList<String>();
		 ausdruecke.add(grammatik.get(0)[0]);
		 System.out.println(grammatik.get(0)[0]);
		return ausdruecke;
	}

	/** Zerlegt einen String mit einer Typ-1-Grammatik.
     * @param grammarString String mit einer Grammatik.
     * @return Produktionen der Grammatik.
     * Jede Produktion ist ein Array mit 2 Elementen, der linken und der rechten Seite.
     */
	@Override
	public  Stream<String[]> parseGrammar(String grammarString) {
		String sideSplitter = grammarString.substring(0, 1);
		String productionSplitter = grammarString.substring(1, 2);
		return Stream.of(grammarString.split(productionSplitter)).skip(1).map(productionString -> {
			if(productionString.indexOf(sideSplitter) == productionString.length()-1) {
				return new String[] {productionString.substring(0, productionString.length()-1), ""};
			}
			else {
				return productionString.split(sideSplitter);
			}
		});
	}

	

}
