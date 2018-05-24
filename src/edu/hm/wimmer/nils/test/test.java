package edu.hm.wimmer.nils.test;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(final String... args) {
	    RDParserS parser = new RDParserS();
	    //RDParserE parser = new RDParserE();
	    try {
			parser.parse("a").prettyPrint();
			//parser.parse("(n-n)").prettyPrint(1);
		} catch (SyntaxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	String hochKlettern = entry.getValue().get(i);
//	System.out.println(hochKlettern);
//	boolean flag2 = true;
//	while (flag2) {
//		boolean changed = false;
//		if (changed) {
//			
//
//		for (String key : second.keySet()) {
//			
//			
//			//System.out.println(second.get(key).contains(hochKlettern));
//			if (second.get(key).contains(hochKlettern)) {
//				if (key == entry.getKey()) {
//					flag2 = false;
//				} else {
//					changed = true;
//					hochKlettern = key;
//					System.out.println(key);
//					System.out.println(hochKlettern);
//				}							
//			}
//		}
//		System.out.println("Läuft....");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} else {
//		flag2 = false;
//	} 
//	}
//	
//	System.out.println(second.keySet());
//	fertigesFile.append(String.format(", %c()", hochKlettern.toCharArray()[0]));
}
