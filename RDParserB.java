import java.util.*;

public class RDParserB {

	private String input;
	private int counter = 0;

	public RDParserB() {}

	public Node parse(String input) throws SyntaxErrorException {
		this.input = input;
		return B();
	}

	private Node B() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'c':
				return new Node("B", C());
			case 'd':
				return new Node("B", D());
			case 'e':
				return new Node("B", E());
			case 'f':
				return new Node("B", F());
			case 'g':
				return new Node("B", G());
			case 'h':
				return new Node("B", H());
			case 'i':
				return new Node("B", I());
			case 'j':
				return new Node("B", J());
			case 'k':
				return new Node("B", K());
			case 'l':
				return new Node("B", L());
			case 'm':
				return new Node("B", M());
			case 'n':
				return new Node("B", N());
			case 'o':
				return new Node("B", O());
			case 'p':
				return new Node("B", P());
			case 'q':
				return new Node("B", Q());
			case 'r':
				return new Node("B", R());
			case 's':
				return new Node("B", S());
			case 't':
				return new Node("B", T());
			case 'u':
				return new Node("B", U());
			case 'v':
				return new Node("B", V());
			case 'w':
				return new Node("B", W());
			case 'x':
				return new Node("B", X());
			case 'y':
				return new Node("B", Y());
			case 'z':
				return new Node("B", Z());
			default: throw new SyntaxErrorException("One of the following characters expected: [[c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]] but was: " + input.charAt(counter));
		}
	}

	private Node C() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'c':
				return new Node("C", terminal('c'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[c]] but was: " + input.charAt(counter));
		}
	}

	private Node D() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'd':
				return new Node("D", terminal('d'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[d]] but was: " + input.charAt(counter));
		}
	}

	private Node E() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'e':
				return new Node("E", terminal('e'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[e]] but was: " + input.charAt(counter));
		}
	}

	private Node F() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'f':
				return new Node("F", terminal('f'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[f]] but was: " + input.charAt(counter));
		}
	}

	private Node G() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'g':
				return new Node("G", terminal('g'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[g]] but was: " + input.charAt(counter));
		}
	}

	private Node H() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'h':
				return new Node("H", terminal('h'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[h]] but was: " + input.charAt(counter));
		}
	}

	private Node I() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'i':
				return new Node("I", terminal('i'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[i]] but was: " + input.charAt(counter));
		}
	}

	private Node J() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'j':
				return new Node("J", terminal('j'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[j]] but was: " + input.charAt(counter));
		}
	}

	private Node K() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'k':
				return new Node("K", terminal('k'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[k]] but was: " + input.charAt(counter));
		}
	}

	private Node L() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'l':
				return new Node("L", terminal('l'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[l]] but was: " + input.charAt(counter));
		}
	}

	private Node M() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'm':
				return new Node("M", terminal('m'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[m]] but was: " + input.charAt(counter));
		}
	}

	private Node N() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'n':
				return new Node("N", terminal('n'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[n]] but was: " + input.charAt(counter));
		}
	}

	private Node O() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'o':
				return new Node("O", terminal('o'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[o]] but was: " + input.charAt(counter));
		}
	}

	private Node P() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'p':
				return new Node("P", terminal('p'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[p]] but was: " + input.charAt(counter));
		}
	}

	private Node Q() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'q':
				return new Node("Q", terminal('q'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[q]] but was: " + input.charAt(counter));
		}
	}

	private Node R() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'r':
				return new Node("R", terminal('r'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[r]] but was: " + input.charAt(counter));
		}
	}

	private Node S() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 's':
				return new Node("S", terminal('s'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[s]] but was: " + input.charAt(counter));
		}
	}

	private Node T() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 't':
				return new Node("T", terminal('t'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[t]] but was: " + input.charAt(counter));
		}
	}

	private Node U() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'u':
				return new Node("U", terminal('u'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[u]] but was: " + input.charAt(counter));
		}
	}

	private Node V() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'v':
				return new Node("V", terminal('v'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[v]] but was: " + input.charAt(counter));
		}
	}

	private Node W() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'w':
				return new Node("W", terminal('w'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[w]] but was: " + input.charAt(counter));
		}
	}

	private Node X() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'x':
				return new Node("X", terminal('x'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[x]] but was: " + input.charAt(counter));
		}
	}

	private Node Y() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'y':
				return new Node("Y", terminal('y'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[y]] but was: " + input.charAt(counter));
		}
	}

	private Node Z() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'z':
				return new Node("Z", terminal('z'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[z]] but was: " + input.charAt(counter));
		}
	}

private Node terminal(char c) throws SyntaxErrorException {
	if(input.charAt(counter++) != c) {
		throw new SyntaxErrorException(String.format("Expected Character: %c, but was: %c", c, input.charAt(counter - 1)));
	}
	return new Node(Character.toString(c));
	}
}

class SyntaxErrorException extends Exception {
    SyntaxErrorException(String message) {
        super(message);
    }
}
class Node extends ArrayList<Node> {
    private final String name;
    public Node(String name, Node... children) {
        this.name = Objects.requireNonNull(name);
        addAll(Arrays.asList(children));
    }
    @Override public String toString() {
        return isEmpty()? name: name + super.toString();
    }
    void prettyPrint() {
        prettyPrint(1);
    }
    void prettyPrint(int depth) {
        System.out.printf("%" + (depth * 4) + "s%s%n", "", name);
        forEach(child -> child.prettyPrint(depth + 1));
    }
}
