import java.util.*;

public class RDParser; {

	private String input;
	private int counter = 0;

	public RDParser;() {};

	public Node parse(String input) throws SyntaxErrorException {
		this.input = input;
		return ;();
	}

private Node terminal(char c) throws SyntaxErrorException {
if(input.charAt(counter++) != c) {
throw new SyntaxErrorException(String.format("Expected Character: %c, but was: %c", c, input.charAt(counter - 1)));
}
return new Node(Character.toString(c));
}
};

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
