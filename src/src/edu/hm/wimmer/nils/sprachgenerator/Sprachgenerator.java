package src.edu.hm.wimmer.nils.sprachgenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.hm.cs.rs.compiler.lab04generator.LanguageGenerator;

public class Sprachgenerator implements LanguageGenerator{

	private String[][] productions;
	private String [] splittedString;
	
	public static void main(String... args) {
		LanguageGenerator generator = new Sprachgenerator();
		double start = System.currentTimeMillis();
		System.setProperty("prettyprint", "blaBLABLAJDFKALNKL");
		Stream grammar = generator.parseGrammar("=;S=(S)S;S=()");
		grammar = LanguageGenerator.prettyprint(grammar);
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
		// TODO Auto-generated method stub
		List <String[]> grammatik = grammar.collect(Collectors.toList());
		String start = "";
		for (String[] s : grammatik) {
			if (start == "") {
				start = s[0];
			} else {
				
			}
		}
		return null;
	}
	
	
	 /** Zerlegt einen String mit einer Typ-1-Grammatik.
     * @param grammarString String mit einer Grammatik.
     * @return Produktionen der Grammatik.
     * Jede Produktion ist ein Array mit 2 Elementen, der linken und der rechten Seite.
     */
	@Override
	public  Stream<String[]> parseGrammar(String grammarString) {
		String zeichenTrenner = grammarString.substring(0, 1);
		String produktionsTrenner = grammarString.substring(1, 2);
		return Stream.of(grammarString.split(produktionsTrenner)).skip(1).map(productionString -> productionString.split(zeichenTrenner));
		
	}
	

}
