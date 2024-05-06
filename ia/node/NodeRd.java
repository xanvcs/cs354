package node;

import java.util.Scanner;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * represents a node statement subclass that is a read statement
 */
public class NodeRd extends NodeStmt {

    protected Token id;
    private Scanner scanner;

    /**
     * creates a new instance of node.NodeRd
     * 
     * @param id - the id token
     */
    public NodeRd(Token id, Scanner scanner) {
        this.id = id;
        this.scanner = scanner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        double value = scanner.nextDouble();
        System.out.println(id.getLexeme());
        return env.put(id.getLexeme(), value);
    }
}
