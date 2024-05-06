package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a node statement subclass that is a write statement
 */
public class NodeWr extends NodeStmt {

    protected NodeExpr expr;

    /**
     * creates a new instance of node.NodeWr
     * 
     * @param expr - the expr node
     */
    public NodeWr(NodeExpr expr) {
        this.expr = expr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        double result = expr.eval(env);
        System.out.println(result);
        return result;
    }
}
