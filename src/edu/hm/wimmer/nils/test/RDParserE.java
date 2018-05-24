package edu.hm.wimmer.nils.test;

import java.util.*;

public class RDParserE {

	private String input;
	private int counter = 0;

	public RDParserE() {}

	public Node parse(String input) throws SyntaxErrorException {
		this.input = input;
		return E();
	}

	private Node E() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case '(':
				return new Node("E", terminal('('), E(), O(), E(), terminal(')'));
			case 'n':
				return new Node("E", F());
			case '-':
				return new Node("E", F());
			default: throw new SyntaxErrorException("One of the following characters expected: [[(, n, -]] but was: " + input.charAt(counter));
		}
	}

	private Node F() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case 'n':
				return new Node("F", terminal('n'));
			case '-':
				return new Node("F", terminal('-'), E());
			default: throw new SyntaxErrorException("One of the following characters expected: [[n, -]] but was: " + input.charAt(counter));
		}
	}

	private Node O() throws SyntaxErrorException {
		switch(input.charAt(counter)) {
			case '+':
				return new Node("O", terminal('+'));
			case '-':
				return new Node("O", terminal('-'));
			default: throw new SyntaxErrorException("One of the following characters expected: [[+, -]] but was: " + input.charAt(counter));
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
