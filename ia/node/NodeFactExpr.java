package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents the expr subclass of nodefact
 */
public class NodeFactExpr extends NodeFact {

    protected NodeExpr expr;

    /**
     * creates a new instance of node.NodeFactExpr
     * 
     * @param expr the expr to be stored
     */
    public NodeFactExpr(NodeExpr expr) {
        this.expr = expr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return expr.eval(env);
    }
}
