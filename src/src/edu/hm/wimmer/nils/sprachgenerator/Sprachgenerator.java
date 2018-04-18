package src.edu.hm.wimmer.nils.sprachgenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import edu.hm.cs.rs.compiler.lab04generator.LanguageGenerator;

public class Sprachgenerator implements LanguageGenerator{

	private String[][] productions;
	private String [] splittedString;
	
	public static void main(String... args) {
		parseGrammar1("=;S=(S)S;S=()").map(Arrays::deepToString).forEach(System.out::println);;
	}
	
	@Override
	public Stream<String> generate(Stream<String[]> grammar, int uptoLength) {
		// TODO Auto-generated method stub
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
	
	public static Stream<String[]> parseGrammar1(String grammarString) {
		String zeichenTrenner = grammarString.substring(0, 1);
		String produktionsTrenner = grammarString.substring(1, 2);
		return Stream.of(grammarString.split(produktionsTrenner)).skip(1).map(productionString -> productionString.split(zeichenTrenner));
		
	}
}
